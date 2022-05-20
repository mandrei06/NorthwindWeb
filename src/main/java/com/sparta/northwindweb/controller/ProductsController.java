package com.sparta.northwindweb.controller;

import com.sparta.northwindweb.entities.Customer;
import com.sparta.northwindweb.entities.Product;
import com.sparta.northwindweb.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductsController {
    Logger logger = LoggerFactory.getLogger(ProductsController.class);
    @Autowired
    public ProductRepository productRepo;

    @GetMapping("/product/{id}")
    public String getSupplierById(@PathVariable int id, Model model) {
        Product productObj = productRepo.getById(id);
        System.out.println(productObj);
        model.addAttribute("productAttr", productObj);
        logger.info("Product returned with ID " + id);
        return "productView";
    }
    @GetMapping("/product")
    public String getProduct(Model model){
        List<Product> productList =productRepo.findAll();
        model.addAttribute("productList",productList);
        logger.info("All products returned");
        return "product";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id, Model model){
        productRepo.deleteById(id);
        model.addAttribute("id_deleted", id);
        logger.info("Product deleted with ID " + id);
        return "productDelete";

    }
    @GetMapping("/product/add/")
    public String addProduct( Model model){
        Product thisProduct=new Product();
        model.addAttribute("productToAdd",thisProduct);
        logger.info("Redirecting to add product form");
        return "addProductForm";

    }
    @PostMapping("/addProduct")
    public String addedProduct(@ModelAttribute("productToAdd") Product theProduct){
        Product newProduct=new Product();
        newProduct.setId(theProduct.getId());
        newProduct.setProductName(theProduct.getProductName());
        newProduct.setUnitPrice(theProduct.getUnitPrice());
        newProduct.setDiscontinued(theProduct.getDiscontinued());
        productRepo.save(newProduct);
        logger.info("Supplier added with ID " + newProduct.getId());
        return "addProductSuccess";
    }



    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable int id, Model model){
        Product thisProduct=productRepo.getById(id);
        model.addAttribute("productToEdit",thisProduct);
        logger.info("Redirecting to edit product form");
        return "editProductForm";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("productToEdit") Product theProduct){
        Product oldState=productRepo.getById(theProduct.getId());
        oldState.setProductName(theProduct.getProductName());
        oldState.setUnitPrice(theProduct.getUnitPrice());
        oldState.setDiscontinued(theProduct.getDiscontinued());
        productRepo.save(oldState);
        logger.info("Product edited with ID " + oldState.getId());
        return "editProductSuccess";
    }








    @GetMapping("/index")
    public String home(){

        return "index";

    }
    @GetMapping("/about")
    public String about(){

        return "about";

    }
}
