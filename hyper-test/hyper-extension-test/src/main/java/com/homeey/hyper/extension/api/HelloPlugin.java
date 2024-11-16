package com.homeey.hyper.extension.api;

import com.homeey.hyper.extension.Spi;

/**
 * 明天的你会因今天的努力而幸运
 *
 * @author jt4mrg@qq.com
 * @since 21:49 2024-11-16
 **/
@Spi("hello")
public interface HelloPlugin {
    void hello();
}
