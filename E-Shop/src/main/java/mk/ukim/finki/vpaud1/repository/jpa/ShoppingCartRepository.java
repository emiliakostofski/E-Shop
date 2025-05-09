package mk.ukim.finki.vpaud1.repository.jpa;

import mk.ukim.finki.vpaud1.model.Product;
import mk.ukim.finki.vpaud1.model.ShoppingCart;
import mk.ukim.finki.vpaud1.model.User;
import mk.ukim.finki.vpaud1.model.enumeration.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUserUsernameAndStatus(String username, ShoppingCartStatus status);
    //tuka treba da e findByUserUsernameAndStatus
   // List<Product> getProducts();
}
