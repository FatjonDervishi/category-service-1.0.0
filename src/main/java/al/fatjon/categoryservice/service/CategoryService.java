package al.fatjon.categoryservice.service;


import al.fatjon.categoryservice.manager.CategoryEntityManager;
import al.fatjon.categoryservice.model.BookCategoryEntity;
import al.fatjon.categoryservice.model.CategoryEntity;
import al.fatjon.categoryservice.model.CategoryPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    CategoryEntityManager categoryEntityManager;

    public CategoryPojo getById (Integer id){return entityToPojo (categoryEntityManager.findById(id));}

    public List<CategoryPojo> getAll (){
        return categoryEntityManager.findAll().stream().map(e ->entityToPojo(e)).collect(Collectors.toList());
    }

    public List<CategoryPojo> getAllByBook(int id) {
        return categoryEntityManager.findAllByBook(id).stream().map(e -> entityToPojo(e)).collect(Collectors.toList());
    }

    public void setBookCategoryRelation(Integer bookId, Integer categoryId) {
        BookCategoryEntity bookCategoryEntity = new BookCategoryEntity();
        bookCategoryEntity.setBookId(bookId);
        bookCategoryEntity.setCategoryId(categoryId);
        categoryEntityManager.createBookCategoryRelation(bookCategoryEntity);
    }
    public void setNewCategory (CategoryPojo category){
        categoryEntityManager.create(pojoToEntity(category));
    }

    private CategoryEntity pojoToEntity (CategoryPojo category){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(category.getName());
        return categoryEntity;
    }

    private CategoryPojo entityToPojo (CategoryEntity categoryEntity){
        CategoryPojo category = new CategoryPojo();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        return category;
    }
}
//DONE
