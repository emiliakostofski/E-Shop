package mk.ukim.finki.vpaud1.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import mk.ukim.finki.vpaud1.model.*;
import mk.ukim.finki.vpaud1.model.enumeration.Role;
import mk.ukim.finki.vpaud1.repository.jpa.CategoryRepository;
import mk.ukim.finki.vpaud1.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.vpaud1.repository.jpa.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //za da se povika
@Getter //za getteri setteri
public class DataHolder {

    public static List<Category> categories = null;
    public static List<Manufacturer> manufacturers = null;
    public static List<Product> products = null;
    public static List<User> users = null;
    public static List<ShoppingCart> shoppingCarts = null;



    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ManufacturerRepository manufacturerRepository;

    private final PasswordEncoder passwordEncoder;

    public DataHolder(CategoryRepository categoryRepository, UserRepository userRepository,
                      ManufacturerRepository manufacturerRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        if (this.categoryRepository.count() == 0) {
            categories.add(new Category("Sports", "Sports category"));
            categories.add(new Category("Food", "Food category"));
            categories.add(new Category("Books", "Books category"));
            this.categoryRepository.saveAll(categories);
        }

        users = new ArrayList<>();
        if (this.userRepository.count() == 0) {
            users.add(new User("elena.atanasoska", passwordEncoder.encode("ea"), "Elena", "Atanasoska", Role.ROLE_USER));
            users.add(new User("darko.sasanski", passwordEncoder.encode("ds"), "Darko", "Sasanski", Role.ROLE_USER));
            users.add(new User("ana.todorovska", passwordEncoder.encode("at"), "Ana", "Todorovska", Role.ROLE_USER));
            users.add(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN));
            this.userRepository.saveAll(users);
        }

        manufacturers = new ArrayList<>();
        if (this.manufacturerRepository.count() == 0) {
            manufacturers.add(new Manufacturer("Nike", "USA"));
            manufacturers.add(new Manufacturer("Coca Cola", "USA"));
            manufacturers.add(new Manufacturer("Literatura", "MK"));
            this.manufacturerRepository.saveAll(manufacturers);
        }

        shoppingCarts = new ArrayList<>();
    }


}



//so cel da se popolnat nekolku instanci nekolku informacii
// ko kje ja vklucime aplikacijata za da vidime dali se e vored
//   @PostConstruct
//    public void init()
//    {
//        categories.add(new Category("Softver", "Software category"));
//        categories.add(new Category("Books", "Books category"));
//
//        users.add(new User("kostadin", "km", "kostadin.misev", "misev"));
//        users.add(new User("emilia", "ek", "emilia.kostovski", "kostofski"));
//
//        Manufacturer manufacturer = new Manufacturer("Nike", "NY");
//        manufacturers.add(manufacturer);
//
//        Category category = new Category("Sport", "Sport category");
//        categories.add(category);
//
//        products.add(new Product("Ball 1", 235.9, 7, category, manufacturer));
//        products.add(new Product("Ball 2", 235.9, 7, category, manufacturer));
//        products.add(new Product("Ball 3", 235.9, 7, category, manufacturer));
//
//    }