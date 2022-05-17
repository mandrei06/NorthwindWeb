package com.sparta.northwindweb.controller;

import com.sparta.northwindweb.entities.Supplier;
import com.sparta.northwindweb.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SupplierController {

    @Autowired
    private SupplierRepository repository;

    @GetMapping("/supplier/welcome")
    public String sayHello(){
        return "welcome";
    }

    @GetMapping("/supplier/{id}")
    public String getSupplierById(@PathVariable Integer id, Model model) {
        //Supplier supplierObject = new Supplier();
        Supplier supplierObject = repository.getById(id);
        model.addAttribute("supplierAttribute", supplierObject);
        return "supplier";
    }

    @GetMapping("/supplier/all")
    public String getSupplierById(Model model) {
        List<Supplier> supplierList = repository.findAll();
        model.addAttribute("supplierAttributes", supplierList);
        return "allsuppliers";
    }

    @DeleteMapping("/supplier/delete/{id}")
    public void deleteSupplierById(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
