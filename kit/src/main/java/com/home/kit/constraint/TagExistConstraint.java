package com.home.kit.constraint;

import com.home.kit.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@AllArgsConstructor
@Component
public class TagExistConstraint implements ConstraintValidator {

    private TagRepository tagRepository;

    @Override
    public boolean isValid(Object o) {
        if (o.getClass().isArray()) {
            return Arrays.stream((Integer[]) o).allMatch(id -> tagRepository.existsById(id));
        }
        return tagRepository.existsById((Integer) o);
    }
}
