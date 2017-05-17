package converters;

import domain.EvaluationQuestion;
import domain.Search;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.EvaluationQuestionRepository;
import repositories.SearchRepository;

/**
 * Created by daviddelatorre on 29/3/17.
 */
@Component
@Transactional
public class StringToEvaluationQuestionConverter implements Converter<String, EvaluationQuestion> {

    @Autowired
    EvaluationQuestionRepository searchRepository;

    @Override
    public EvaluationQuestion convert(String text) {
        EvaluationQuestion result;
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
