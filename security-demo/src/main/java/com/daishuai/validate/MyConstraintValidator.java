package com.daishuai.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/17 15:20
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstrain, Object> {
    @Override
    public void initialize(MyConstrain constraintAnnotation) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}
