package com.homeey.hyper.extension.api.impl;

import com.homeey.hyper.extension.SpiIml;
import com.homeey.hyper.extension.api.HelloPlugin;

/**
 * 明天的你会因今天的努力而幸运
 *
 * @author jt4mrg@qq.com
 * @since 21:50 2024-11-16
 **/
@SpiIml
public class HelloPluginImpl implements HelloPlugin {
    @Override
    public void hello() {
        System.out.println("hello world");
    }
}
