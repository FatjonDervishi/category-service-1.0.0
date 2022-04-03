package al.fatjon.categoryservice.manager;

import al.fatjon.categoryservice.model.BookCategoryEntity;
import al.fatjon.categoryservice.model.CategoryEntity;
import al.fatjon.categoryservice.repository.BookCategoryLocalRepository;
import al.fatjon.categoryservice.repository.CategoryLocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryEntityManager {
    @Autowired
    CategoryLocalRepository localRepository;
    @Autowired
    BookCategoryLocalRepository bookCategoryLocalRepository;
    public CategoryEntity findById(Integer id){
        CategoryEntity categoryEntity = localRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Categories found with that ID"));
        return categoryEntity;
    }
    public List<CategoryEntity> findAll(){return localRepository.findAll();}//jpa
    public void create(CategoryEntity categoryEntity){localRepository.save(categoryEntity);}//jpa

    public List<CategoryEntity> findAllByBook(int id) {
        return bookCategoryLocalRepository.findAllByBookId(id).stream().map(i -> localRepository.findById(i.getCategoryId()).get()).collect(Collectors.toList());
    }

    public void createBookCategoryRelation(BookCategoryEntity bookCategoryEntity) {
        bookCategoryLocalRepository.save(bookCategoryEntity);
    }
}
//DONE
