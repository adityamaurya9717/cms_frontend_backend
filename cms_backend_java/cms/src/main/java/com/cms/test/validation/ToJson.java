package com.cms.test.validation;


import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ToJson {
}
