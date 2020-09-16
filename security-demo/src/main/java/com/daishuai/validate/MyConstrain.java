package com.daishuai.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 自定义校验器
 * @Author: daishuai
 * @CreateDate: 2018/12/17 15:14
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstrain {
    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
