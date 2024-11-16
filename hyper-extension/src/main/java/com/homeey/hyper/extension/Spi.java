package com.homeey.hyper.extension;

import java.lang.annotation.*;

/**
 * 明天的你会因今天的努力而幸运
 *
 * @author jt4mrg@qq.com
 * @since 0:06 2024-11-15
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Spi {
    /**
     * 默认实现
     *
     * @return
     */
    String value();
}
