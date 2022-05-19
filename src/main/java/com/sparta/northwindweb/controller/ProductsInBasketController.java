package com.sparta.northwindweb.controller;

import com.sparta.northwindweb.entities.Product;
import com.sparta.northwindweb.entities.ProductItem;
import com.sparta.northwindweb.entities.ProductList;
import com.sparta.northwindweb.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;

@Controller
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(readOnly=true)
public class ProductsInBasketController {
    ProductList productList=new ProductList();
    @Autowired
    HttpServletRequest request;

    @Autowired
    public ProductRepository productRepo;

    @GetMapping("/product/basket/{id}")

    public String addProductToBasket(@PathVariable int id){
        Product thisProduct=productRepo.getById(id);
        ProductItem myItem=new ProductItem();
        myItem.setProduct(thisProduct);
        myItem.setCreateDate(LocalDateTime.now());
        productList.add(myItem);
        request.getSession(true).setAttribute("productList",productList);
        return "productAddedToBasket";
    }
    @GetMapping("/product/basket/")
    public String seeBasket(Model model){
        try{
        ProductList productBasket= (ProductList) request.getSession().getAttribute("productList");
        model.addAttribute("productBasket",productBasket);

        return "seeBasket";}
        catch (NullPointerException e){
            return "emptyBasket";
        }
    }


}
