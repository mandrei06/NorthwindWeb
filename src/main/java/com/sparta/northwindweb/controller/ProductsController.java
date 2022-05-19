package com.sparta.northwindweb.controller;

import com.sparta.northwindweb.entities.Customer;
import com.sparta.northwindweb.entities.Product;
import com.sparta.northwindweb.repositories.ProductRepository;
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

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id, Model model){
        productRepo.deleteById(id);
        model.addAttribute("id_deleted", id);
        return "productDelete";

    }
    @GetMapping("/product/add/")
    public String addProduct( Model model){
        Product thisProduct=new Product();
        model.addAttribute("productToAdd",thisProduct);
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
        return "addProductSuccess";
    }



    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable int id, Model model){
        Product thisProduct=productRepo.getById(id);
        model.addAttribute("productToEdit",thisProduct);
        return "editProductForm";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("productToEdit") Product theProduct){
        Product oldState=productRepo.getById(theProduct.getId());
        oldState.setProductName(theProduct.getProductName());
        oldState.setUnitPrice(theProduct.getUnitPrice());
        oldState.setDiscontinued(theProduct.getDiscontinued());
        productRepo.save(oldState);
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
