package mk.ukim.finki.vpaud1.model.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCart extends RuntimeException{
    public ProductAlreadyInShoppingCart(Long id,String username) {
        super(String.format("Product with id: %d already exists in shopping cart for user with username %s", id, username));
    }
}
