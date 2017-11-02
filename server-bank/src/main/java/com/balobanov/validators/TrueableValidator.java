package com.balobanov.validators;

import com.balobanov.models.base.BaseModel;
import org.springframework.stereotype.Component;

@Component
public class TrueableValidator implements Validator<BaseModel>{

    @Override
    public boolean validate(BaseModel baseModel) {
        return true;
    }
}
