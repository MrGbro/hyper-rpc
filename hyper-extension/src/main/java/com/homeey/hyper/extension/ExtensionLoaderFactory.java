package com.homeey.hyper.extension;

/**
 * 明天的你会因今天的努力而幸运
 *
 * @author jt4mrg@qq.com
 * @since 21:02 2024-11-16
 **/
public class ExtensionLoaderFactory {
    /**
     * 获取扩展实现
     *
     * @param clazz         spi接口抽象
     * @param extensionName 扩展名
     * @param <T>           参数化类型
     * @return 扩展实现实例
     */
    public static <T> T getExtension(Class<T> clazz, String extensionName) {
        return ExtensionLoader.getExtensionLoader(clazz).getExtension(extensionName);
    }
}
