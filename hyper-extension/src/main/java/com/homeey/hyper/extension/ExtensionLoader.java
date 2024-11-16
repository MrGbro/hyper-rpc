package com.homeey.hyper.extension;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 明天的你会因今天的努力而幸运
 *
 * @author jt4mrg@qq.com
 * @since 16:50 2024-11-16
 **/
public class ExtensionLoader<T> {
    private static final String EXTENSION_LOADER_CLASS_PATH = "META-INF/extensions/";
    private static final ConcurrentHashMap<Class<?>, ExtensionLoader<?>> EXTENSION_LOADER_MAP =
            new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, Class<T>> extensionClassMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Class<T>, T> extensionInstanceMap = new ConcurrentHashMap<>();

    /**
     * 扩展接口
     */
    private final Class<T> clazz;
    /**
     * 扩展名称
     */
    private volatile String extensionName;

    public ExtensionLoader(Class<T> clazz) {
        this.clazz = clazz;
    }

    private ExtensionLoader(Class<T> clazz, String extensionName) {
        this.clazz = clazz;
        this.extensionName = extensionName;
    }

    @SuppressWarnings("unchecked")
    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> clazz) {
        return (ExtensionLoader<T>) EXTENSION_LOADER_MAP.computeIfAbsent(clazz, ExtensionLoader::new);
    }

    public T getExtension(String name) {
        return createExtensionInstanceIfAbsent(name);
    }

    private T createExtensionInstanceIfAbsent(String extensionName) {
        Class<T> instanceClass = extensionClassMap.get(extensionName);
        if (instanceClass == null) {
            synchronized (clazz) {
                instanceClass = extensionClassMap.get(extensionName);
                if (instanceClass == null) {
                    Spi annotation = clazz.getAnnotation(Spi.class);
                    if (Objects.isNull(annotation)) {
                        throw new IllegalArgumentException("class " + clazz.getName() + " is not annotated @Spi");
                    }
                    extensionName = annotation.value();
                    instanceClass = loadExtensionClass(extensionName);
                }
            }
        }
        return extensionInstanceMap.computeIfAbsent(instanceClass, this::createInstance);
    }

    private Class<T> loadExtensionClass(String extensionName) {
        Map<String, Class<T>> allExtensionClass = loadAllExtensionClass();
        Class<T> target = allExtensionClass.get(extensionName);
        return Optional.ofNullable(target)
                .orElseThrow(() -> new IllegalArgumentException("extension " + extensionName + " not found"));
    }

    private Map<String, Class<T>> loadAllExtensionClass() {
        String pathName = EXTENSION_LOADER_CLASS_PATH + clazz.getName();
        try {
            Enumeration<URL> resources = this.getClass().getClassLoader().getResources(pathName);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                Properties properties = new Properties();
                properties.load(url.openStream());
                for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                    String extensionName = (String) entry.getKey();
                    String extensionClassName = (String) entry.getValue();
                    Class<T> extensionClass = (Class<T>) Class.forName(extensionClassName);
                    extensionClassMap.put(extensionName, extensionClass);
                    if (extensionName.equals(this.extensionName)) {
                        extensionInstanceMap.computeIfAbsent(extensionClass,
                                this::createInstance);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return extensionClassMap;
    }

    private T createInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("initialize extension instance fail", e);
        }
    }
}
