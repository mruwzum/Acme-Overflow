/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package converters;

import domain.Answer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by daviddelatorre on 29/3/17.
 */
@Component
@Transactional
public class AnswerToStringConverter implements Converter<Answer, String> {


    @Override
    public String convert(Answer actor) {
        Assert.notNull(actor);
        String result;
        result = String.valueOf(actor.getId());
        return result;
    }
}
