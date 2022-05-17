package com.sparta.northwindweb.controller;

import com.sparta.northwindweb.entities.Customer;
import com.sparta.northwindweb.entities.Product;
import com.sparta.northwindweb.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductsController {
    @Autowired
    public ProductRepository productRepo;

    @GetMapping("/product/{id}")
    public String getSupplierById(@PathVariable int id, Model model) {
        Product productObj = productRepo.getById(id);
        System.out.println(productObj);
        model.addAttribute("productAttr", productObj);
        return "productView";
    }
    @GetMapping("/product")
    public String getProduct(Model model){
        List<Product> productList =productRepo.findAll();
        model.addAttribute("productList",productList);
        return "product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id, Model model){
        productRepo.deleteById(id);
        model.addAttribute("id_deleted", id);
        return "productDelete";

    }
    @GetMapping("/index")
    public String home(){

        return "index";

    }
}
