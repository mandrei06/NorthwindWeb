package com.sparta.northwindweb.controller;

import com.sparta.northwindweb.entities.Supplier;
import com.sparta.northwindweb.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SupplierController {

    @Autowired
    private SupplierRepository repository;

    @GetMapping("/supplier/{id}")
    public String getSupplierById(@PathVariable Integer id, Model model) {
        model.addAttribute("supplierAttribute", repository.getById(id));
        return "supplier";
    }

    @GetMapping("/supplier/all")
    public String getSupplierById(Model model) {
        List<Supplier> supplierList = repository.findAll();
        model.addAttribute("supplierAttributes", supplierList);
        return "allSuppliers";
    }

    @GetMapping("/supplier/delete/{id}")
    public String deleteSupplierById(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        repository.deleteById(id);
        return "allSuppliers";
    }

    @GetMapping("/supplier/edit/{id}")
    public String editSupplier(@PathVariable int id, Model model) {
        Supplier supplier = repository.getById(id);
        model.addAttribute("supplierToEdit", supplier);
        return "editSupplier";
    }

    @PostMapping("/supplier/update")
    public String updateSupplier(@ModelAttribute("supplierToEdit") Supplier supplier) {
        Supplier temp = repository.getById(supplier.getId());
        temp.setCompanyName(supplier.getCompanyName());
        temp.setAddress(supplier.getAddress());
        temp.setCountry(supplier.getCountry());
        repository.save(temp);
        return "editSuccess";
    }
}
