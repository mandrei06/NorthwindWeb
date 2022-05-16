package com.sparta.northwindweb.controller;

import com.sparta.northwindweb.entities.Customer;
import com.sparta.northwindweb.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class NorthwindController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customer/{id}")
    public String getCustomerById(@PathVariable String id,
                                   Model model){
        Customer customerObj=customerRepository.getById(id);
        System.out.println(customerObj);
        model.addAttribute("customerAttr",customerObj);//store the customer in model
        return "customerView";

    }
    @GetMapping("/customer")
    public String getCustomers(Model model){
        List<Customer> customersList=customerRepository.findAll();
        model.addAttribute("customerList",customersList);
        return "customer";
    }

}
