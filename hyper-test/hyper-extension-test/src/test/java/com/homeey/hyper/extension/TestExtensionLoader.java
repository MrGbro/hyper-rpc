package com.homeey.hyper.extension;

import com.homeey.hyper.extension.api.HelloPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 明天的你会因今天的努力而幸运
 *
 * @author jt4mrg@qq.com
 * @since 21:54 2024-11-16
 **/
class TestExtensionLoader {

    @Test
    void testSpi() {
        HelloPlugin hello = ExtensionLoaderFactory.getExtension(HelloPlugin.class, "hello");
        Assertions.assertNotNull(hello);
        hello.hello();
    }
}
