package al.fatjon.categoryservice.repository;

import al.fatjon.categoryservice.model.BookCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCategoryLocalRepository extends JpaRepository<BookCategoryEntity, Integer> {
    List<BookCategoryEntity> findAllByBookId(Integer id);
}
