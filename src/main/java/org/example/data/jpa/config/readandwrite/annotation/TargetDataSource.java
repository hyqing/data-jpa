package org.example.data.jpa.config.readandwrite.annotation;

import java.lang.annotation.*;

/**
 * Created by hyq on 2020/12/13 14:32.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String dataSource() default "";//数据源
}
