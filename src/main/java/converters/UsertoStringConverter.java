package converters;

import domain.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by daviddelatorre on 13/12/16.
 */
public class UserToStringConverter implements Converter<User, String> {


    @Override
    public String convert(User userAccount) {
        String result;

        if (userAccount == null) {
            result = null;
        } else {
            result = String.valueOf(userAccount.getId());
        }
        return result;
    }
}
