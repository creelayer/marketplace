package com.home.kit.service;

import com.home.kit.entity.Category;
import com.home.kit.entity.Vocabulary;
import com.home.kit.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Page<Category> search(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }


    public Category save(Category category) {

        if(category.getVocabulary().getId() != Vocabulary.CATEGORY_ID){
            throw new IllegalStateException("Category can be only in category vocabulary");
        }

        return categoryRepository.save(category);
    }

    public void delete(Category category) {
        if (!category.getChildren().isEmpty()) {
            throw new IllegalStateException("Cant delete tag with children");
        }
        categoryRepository.delete(category);
    }
}
