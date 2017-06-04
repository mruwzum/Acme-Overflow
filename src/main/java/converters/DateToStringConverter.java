/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package converters;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Created by daviddelatorre on 22/12/16.
 */
public class DateToStringConverter implements Converter<Date, String> {

    @Override
    public String convert(Date date) {
        String result;

        if (date == null)
            result = null;
        else
            result = date.toString().replace("-", "/");

        return result;
    }
}
