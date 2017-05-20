package converters;

import domain.Mezzage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.MezzageRepository;

@Component
@Transactional
public class StringToMezzageConverter implements Converter<String, Mezzage> {

    @Autowired
    MezzageRepository mezzageRepository;

    @Override
    public Mezzage convert(String text) {
        Mezzage result;
        int id;
        try {
            if (StringUtils.isEmpty(text))
                result = null;
            else {
                id = Integer.valueOf(text);
                result = mezzageRepository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
