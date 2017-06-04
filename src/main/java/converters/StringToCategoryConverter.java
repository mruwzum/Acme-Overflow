/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package converters;

import domain.Category;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.CategoryRepository;

/**
 * Created by daviddelatorre on 29/3/17.
 */
@Component
@Transactional
public class StringToCategoryConverter implements Converter<String, Category> {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category convert(String text) {
        Category result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = categoryRepository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;

    }

}
