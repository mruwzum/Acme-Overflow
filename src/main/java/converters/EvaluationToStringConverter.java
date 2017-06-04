/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package converters;

import domain.Evaluation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by daviddelatorre on 29/3/17.
 */
@Component
@Transactional
public class EvaluationToStringConverter implements Converter<Evaluation, String> {


    @Override
    public String convert(Evaluation actor) {
        Assert.notNull(actor);
        String result;
        result = String.valueOf(actor.getId());
        return result;
    }
}
