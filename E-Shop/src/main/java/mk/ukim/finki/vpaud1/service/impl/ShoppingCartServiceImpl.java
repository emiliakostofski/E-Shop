package mk.ukim.finki.vpaud1.service.impl;

import mk.ukim.finki.vpaud1.model.Product;
import mk.ukim.finki.vpaud1.model.ShoppingCart;
import mk.ukim.finki.vpaud1.model.User;
import mk.ukim.finki.vpaud1.model.enumeration.ShoppingCartStatus;
import mk.ukim.finki.vpaud1.model.exeptions.ProductAlreadyInShoppingCart;
import mk.ukim.finki.vpaud1.model.exeptions.ProductNotFoundException;
import mk.ukim.finki.vpaud1.model.exeptions.ShoppingCartNotFoundException;
import mk.ukim.finki.vpaud1.model.exeptions.UserNotFoundException;
import mk.ukim.finki.vpaud1.repository.impl.InMemoryShoppingCartRepository;
import mk.ukim.finki.vpaud1.repository.impl.InMemoryUserRepository;
import mk.ukim.finki.vpaud1.repository.jpa.ShoppingCartRepository;
import mk.ukim.finki.vpaud1.repository.jpa.UserRepository;
import mk.ukim.finki.vpaud1.service.ProductService;
import mk.ukim.finki.vpaud1.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
        {
            throw new ShoppingCartNotFoundException(cartId);
        }
        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

       return this.shoppingCartRepository
                .findByUserUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });

    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.findById(productId)
                .orElseThrow(()->new ProductNotFoundException(productId));

        if(shoppingCart.getProducts()
                .stream().filter(i->i.getId()
                .equals(productId))
                .collect(Collectors.toList()).size()>0) throw new ProductAlreadyInShoppingCart(productId, username);
        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);

    }
}
