package com.godcoder.myhome.validator;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.model.Board3;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class Board3Validator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board3.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Board3 b = (Board3) obj;
        if(StringUtils.isEmpty(b.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }
    }
}
