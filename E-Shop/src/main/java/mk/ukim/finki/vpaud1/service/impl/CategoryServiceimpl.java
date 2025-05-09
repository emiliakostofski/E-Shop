package mk.ukim.finki.vpaud1.service.impl;

import mk.ukim.finki.vpaud1.model.Category;
import mk.ukim.finki.vpaud1.model.exeptions.CategoryNotFoundException;
import mk.ukim.finki.vpaud1.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.vpaud1.repository.jpa.CategoryRepository;
import mk.ukim.finki.vpaud1.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //so cel deka ovaa klasa ni pretstavuva implementacija na odreden servis vo nasiot biznis sloj
public class CategoryServiceimpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceimpl (CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> create(String name, String description) {
        if(name == null || name.isEmpty() ||
                description == null || description.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        Category category = new Category(name, description);
        return Optional.of(categoryRepository.save(category));
    }



    @Override
    public Optional<Category> update(Long id, String name, String description) {
        if(id==null || name == null || name.isEmpty() || description == null || description.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        category.setName(name);
        category.setDescription(description);

        return Optional.of(categoryRepository.save(category));

    }

    @Override//i tuka mi vika so id deka a jas imam so name
    public void delete(String name) {
        if(name==null || name.isEmpty())
        {
            throw new IllegalArgumentException("Name is null or empty");
        }
        categoryRepository.deleteByName(name);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }


    @Override
    public List<Category> listCategories() {

        return categoryRepository.findAll();
    }


    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }



    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }
}
