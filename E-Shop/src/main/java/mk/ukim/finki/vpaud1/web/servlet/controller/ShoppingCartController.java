package mk.ukim.finki.vpaud1.web.servlet.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.vpaud1.model.ShoppingCart;
import mk.ukim.finki.vpaud1.model.User;
import mk.ukim.finki.vpaud1.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false)String error, HttpServletRequest req, Model model) {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String username = req.getRemoteUser();
        //User user = (User)req.getSession().getAttribute("user");
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("products", this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req)
    {
        try{
            String username = req.getRemoteUser();
            //User user = (User)req.getSession().getAttribute("user");
            this.shoppingCartService.addProductToShoppingCart(username, id);
            return "redirect:/shopping-cart";
        }catch (RuntimeException exception)
        {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
}
