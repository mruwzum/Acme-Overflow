package converters;

import domain.LearningMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import services.LearningMaterialService;

/**
 * Created by mruwzum on 22/12/16.
 */
public class StringToLearningMaterialConverter implements Converter<String, LearningMaterial> {

    @Autowired
    LearningMaterialService learningMaterialService;

    @Override
    public LearningMaterial convert(String text) {
        LearningMaterial result;
        int id;

        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = learningMaterialService.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}