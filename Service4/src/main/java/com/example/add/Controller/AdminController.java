package com.example.add.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.repository.BuyerRepository;
import com.example.repository.ProductRepository;
import com.example.repository.SellerRepository;
import com.example.entity.Buyer;
import com.example.entity.Product;
import com.example.entity.Seller;
import com.example.add.service.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @RequestMapping("/admin")
    public String adminDashboard(Model model) {
        List<Buyer> buyers = buyerRepository.findAllByOrderByIdAsc();
        List<Seller> sellers = sellerRepository.findAllByOrderByIdAsc();
        List<Product> products = productRepository.findAllByOrderByIdAsc();
        
        // Check if the lists are populated
        System.out.println("Buyers List: " + buyers);  // Log buyers list to check if it's populated
        System.out.println("Sellers List: " + sellers);  // Log sellers list to check if it's populated
        System.out.println("Products List: " + products);
        
        model.addAttribute("buyers", buyers);
        model.addAttribute("sellers", sellers);
        model.addAttribute("products", products);
        return "adminDashboard"; // This will render the admin dashboard page
    }

    // Update buyer status (Activate/Deactivate)
    @PostMapping("/admin/updateBuyerStatus")
    public String updateBuyerStatus(@RequestParam int id, @RequestParam boolean accountStatus) {
        Optional<Buyer> buyer = buyerRepository.findById(id);
        System.out.println("Buyers List: " + buyer);
        
        if (buyer.isPresent()) {
            // Update the account status to true or false based on the boolean value
            buyer.get().setAccountStatus(accountStatus); 
            buyerRepository.save(buyer.get());
        }
        
        return "redirect:/admin"; // Redirect to the admin dashboard after update
    }


    // Update seller status (Activate/Deactivate)
    @PostMapping("/admin/updateSellerStatus")
    public String updateSellerStatus(@RequestParam int id, @RequestParam boolean accountStatus) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isPresent()) {
            seller.get().setAccountStatus(accountStatus); // Convert 1/0 to boolean
            sellerRepository.save(seller.get());
        }
        return "redirect:/admin";
    }
    // Remove product
    @PostMapping("/admin/removeProduct")
    public String removeProduct(@RequestParam int productId) {
        productRepository.deleteById(productId); // Delete product by ID
        return "redirect:/admin"; // Redirect to admin dashboard after deletion
    }
    @GetMapping("/admin/viewProduct/{id}")
    public String viewProduct(@PathVariable("id") int id, Model model) {
        // Fetch product by id from the database
        Optional<Product> productOptional = productService.getProductById(id);
        
        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());  // Unwrap the Optional and add the Product object to the model
            return "viewProduct"; // This should be the name of your JSP page to display the product
        }
        
        return "errorPage"; // Return an error page if the product is not found
    }
    
    // Display products by category
    @GetMapping("/admin/productsByCategory")
    public String viewProductsByCategory(@RequestParam("category") String category, Model model) {
        model.addAttribute("products", productService.getProductsByCategory(category));
        return "productsByCategory"; // View for displaying products by category
    }

    // Show form to add a new category
    @GetMapping("/admin/addCategory")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("categories", productService.getAllCategories());  // List all existing categories
        return "addCategory"; // JSP page for adding a category
    }

    // Add a new category to products
    @PostMapping("/addCategory")
    public String addCategory(@RequestParam("category") String category) {
        productService.addCategory(category);
        return "redirect:/productsByCategory?category=" + category;  // Redirect to that category's product list
    }

    // Remove a category from products
    @PostMapping("/removeCategory")
    public String removeCategory(@RequestParam("category") String category) {
        productService.removeCategory(category);
        return "redirect:/productsByCategory?category=" + category;  // Redirect back to category list
    }
}

