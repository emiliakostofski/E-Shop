package mk.ukim.finki.vpaud1.repository.impl;

import mk.ukim.finki.vpaud1.bootstrap.DataHolder;
import mk.ukim.finki.vpaud1.model.Category;
import mk.ukim.finki.vpaud1.model.Manufacturer;
import mk.ukim.finki.vpaud1.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {

    public List<Product> findAll() {
        return DataHolder.products;
    }

    public Optional<Product> findById(Long id) {
        return DataHolder.products
                .stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public Optional<Product> findByName(String name) {
        return DataHolder.products
                .stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();
    }

    public Optional<Product> save(String name,
                                  Double price,
                                  Integer quantity,
                                  Category category,
                                  Manufacturer manufacturer) {

        if (category == null || manufacturer == null) {
            throw new IllegalArgumentException();
        }

        Product product = new Product(name, price, quantity, category, manufacturer);
        DataHolder.products.removeIf(i -> i.getName().equals(product.getName()));
        DataHolder.products.add(product);
        return Optional.of(product);
    }
    public void deleteById(Long id) {
        DataHolder
                .products.removeIf(i -> i.getId().equals(id));
    }


}
