package converters;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import repositories.UserRepository;

/**
 * Created by daviddelatorre on 13/12/16.
 */
public class StringToUserConverter implements Converter<String, User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User convert(String text) {
        User result;
        int id;
        try {
            if (StringUtils.isEmpty(text)) {
                result = null;
            } else {
                id = Integer.valueOf(text);
                result = userRepository.findOne(id);
            }
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
