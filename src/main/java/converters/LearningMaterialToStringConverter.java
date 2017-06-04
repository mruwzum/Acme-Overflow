/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package converters;


import domain.LearningMaterial;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mruwzum on 22/12/16.
 */
public class LearningMaterialToStringConverter implements Converter<LearningMaterial, String> {


    @Override
    public String convert(LearningMaterial source) {
        String result;

        if (source == null) {
            result = null;
        } else {
            result = source.getTitle();
        }

        return result;
    }
}
