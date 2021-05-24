package org.springframework.stereotype;


import java.lang.annotation.*;

/**
 * component 注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
