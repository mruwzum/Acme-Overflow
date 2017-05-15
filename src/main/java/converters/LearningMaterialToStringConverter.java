package converters;


import domain.LearningMaterial;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mruwzum on 22/12/16.
 */
public class LearningMaterialToStringConverter implements Converter<LearningMaterial,String>{


    @Override
    public String convert(LearningMaterial source) {
        String result;

        if(source == null){
            result = null;
        }else{
            result = source.getTitle();
        }

        return result;
    }
}
