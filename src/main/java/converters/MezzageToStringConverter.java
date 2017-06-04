/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package converters;

import domain.Mezzage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MezzageToStringConverter implements Converter<Mezzage, String> {

    @Override
    public String convert(Mezzage mezzage) {
        String result;

        if (mezzage == null)
            result = null;
        else
            result = String.valueOf(mezzage.getId());

        return result;
    }
}