package mk.ukim.finki.vpaud1.service;

import mk.ukim.finki.vpaud1.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> create(String name, String description);

    Optional<Category> update(Long id, String name, String description);

    void delete(String name);

    void deleteById(Long id);

    List<Category> listCategories();

    List<Category> searchCategories(String searchText);

    Optional<Category> findById(Long id);

}
