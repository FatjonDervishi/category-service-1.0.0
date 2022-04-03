package al.fatjon.categoryservice.controllers;

import al.fatjon.categoryservice.model.CategoryPojo;
import al.fatjon.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public/library/category-service/")
@Component
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById (@PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok(categoryService.getById(id));
        } catch(IllegalArgumentException ie){
            return ResponseEntity.badRequest().body(ie);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping()
    public ResponseEntity<List<CategoryPojo>> getCategories(){ return ResponseEntity.ok(categoryService.getAll());}

    @PostMapping()
    public ResponseEntity<Object> createCategory (@RequestBody CategoryPojo category){
        categoryService.setNewCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully");
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<List<CategoryPojo>> getCategoriesByBook (@PathVariable("id") Integer id){
            return ResponseEntity.ok(categoryService.getAllByBook(id));

    }

    @PostMapping("/book/{id}")
    public ResponseEntity<Object> createBookCategoryRelation (@PathVariable("id") Integer id, @RequestParam("categoryId") Integer categoryId){
        categoryService.setBookCategoryRelation(id, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category Book relation created successfully");
    }
}
