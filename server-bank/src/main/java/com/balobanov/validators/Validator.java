package com.balobanov.validators;

public interface Validator<T> {

    boolean validate(T t);
}
