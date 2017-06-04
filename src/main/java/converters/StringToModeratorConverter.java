/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package converters;

import domain.Moderator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ModeratorRepository;

/**
 * Created by daviddelatorre on 29/3/17.
 */
@Component
@Transactional
public class StringToModeratorConverter implements Converter<String, Moderator> {

    @Autowired
    ModeratorRepository searchRepository;

    @Override
    public Moderator convert(String text) {
        Moderator result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = searchRepository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;

    }

}
