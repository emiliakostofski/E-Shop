package mk.ukim.finki.vpaud1.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.vpaud1.model.Category;
import mk.ukim.finki.vpaud1.model.Manufacturer;
import mk.ukim.finki.vpaud1.model.Product;
import mk.ukim.finki.vpaud1.model.exeptions.CategoryNotFoundException;
import mk.ukim.finki.vpaud1.model.exeptions.ManufecturerNotFoundException;
import mk.ukim.finki.vpaud1.model.exeptions.ProductNotFoundException;
import mk.ukim.finki.vpaud1.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.vpaud1.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.vpaud1.repository.impl.InMemoryProductRepository;
import mk.ukim.finki.vpaud1.repository.jpa.CategoryRepository;
import mk.ukim.finki.vpaud1.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.vpaud1.repository.jpa.ProductRepository;
import mk.ukim.finki.vpaud1.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price,
                                  Integer quantity, Long categoryId,
                                  Long manufacturerId) {

        Category category = this.categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        Manufacturer manufacturer = this.manufacturerRepository
                .findById(manufacturerId)
                .orElseThrow(()->new ManufecturerNotFoundException(manufacturerId));

        //this.productRepository.deleteByName(name);

        return Optional.of(this.productRepository.save(new Product(name,price,quantity,category,manufacturer)));
    }

    //@Override  mozebi i treba override kje vidime
    public Optional<Product> update(
            Long id,
            String name,
            Double price,
            Integer quantity,
            Long categoryId,
            Long manufacturerId
    ) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufecturerNotFoundException(manufacturerId));

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setManufacturer(manufacturer);

        return Optional.of(this.productRepository.save(product));
    }


    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
