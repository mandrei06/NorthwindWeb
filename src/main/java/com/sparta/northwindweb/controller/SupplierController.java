package com.sparta.northwindweb.controller;

import com.sparta.northwindweb.entities.Supplier;
import com.sparta.northwindweb.repositories.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class SupplierController {
    Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    private SupplierRepository repository;

    @GetMapping("/supplier/{id}")
    public String getSupplierById(@PathVariable Integer id, Model model) {
        model.addAttribute("supplierAttribute", repository.getById(id));
        logger.info("Supplier returned with ID " + id);
        return "supplier";
    }

    @GetMapping("/supplier")
    public String getSupplierById(Model model) {
        List<Supplier> supplierList = repository.findAll();
        model.addAttribute("supplierAttributes", supplierList);
        logger.info("All suppliers returned");
        return "allSuppliers";
    }

    @GetMapping("/supplier/delete/{id}")
    public String deleteSupplierById(@PathVariable Integer id) {
        repository.deleteById(id);
        logger.info("Supplier deleted with ID " + id);
        return "deleteSupplierSuccess";
    }

    @GetMapping("/supplier/edit/{id}")
    public String editSupplier(@PathVariable int id, Model model) {
        Supplier supplier = repository.getById(id);
        model.addAttribute("supplierToEdit", supplier);
        logger.info("Redirecting to edit supplier form");
        return "editSupplier";
    }

    @GetMapping("/supplier/add")
    public String addSupplier(Model model) {
        Supplier temp = new Supplier();
        model.addAttribute("supplierToAdd", temp);
        logger.info("Redirecting to add supplier form");
        return "addSupplier";
    }

    @PostMapping("/supplier/add")
    public String addSupplier(@ModelAttribute("supplierToAdd") Supplier supplier) {
        Supplier temp = new Supplier();
        temp.setId(supplier.getId());
        temp.setCompanyName(supplier.getCompanyName());
        temp.setAddress(supplier.getAddress());
        temp.setCountry(supplier.getCountry());
        repository.save(temp);
        logger.info("Supplier added with ID " + temp.getId());
        return "addSuccess";
    }

    @PostMapping("/supplier/update")
    public String updateSupplier(@ModelAttribute("supplierToEdit") Supplier supplier) {
        Supplier temp = repository.getById(supplier.getId());
        temp.setCompanyName(supplier.getCompanyName());
        temp.setAddress(supplier.getAddress());
        temp.setCountry(supplier.getCountry());
        repository.save(temp);
        logger.info("Supplier edited with ID " + temp.getId());
        return "editSupplierSuccess";
    }
}
