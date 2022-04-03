package al.fatjon.categoryservice.repository;

import al.fatjon.categoryservice.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryLocalRepository extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findById(Integer id);

}
//DONE
