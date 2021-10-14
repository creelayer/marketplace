package com.home.kit.constraint;

import com.home.kit.repository.VocabularyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class VocabularyExistConstraint implements  ConstraintValidator {

    private VocabularyRepository vocabularyRepository;

    @Override
    public boolean isValid(Object o) {
        return vocabularyRepository.existsById((Integer) o);
    }
}
