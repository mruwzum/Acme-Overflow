/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package converters;

import domain.Answer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.AnswerRepository;

/**
 * Created by daviddelatorre on 29/3/17.
 */
@Component
@Transactional
public class StringToAnswerConverter implements Converter<String, Answer> {

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public Answer convert(String text) {
        Answer result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = answerRepository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;

    }

}
