package com.sparta.northwindweb.controller;

import com.sparta.northwindweb.entities.Customer;
import com.sparta.northwindweb.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

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



    @GetMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable String id, Model model){
        try{
        customerRepository.deleteById(id);}
        catch (EmptyResultDataAccessException e){
            id="00000";
        }
        model.addAttribute("customer_deleted",id);
        return "customerDeleted";
    }

    @GetMapping("/customer/edit/{id}")
    public String editCustomer(@PathVariable String id, Model model){
        Customer thisCustomer=customerRepository.getById(id);
        model.addAttribute("customerToEdit",thisCustomer);
        return "editCustomerForm";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute("customerToEdit") Customer theCustomer){
        Customer oldState=customerRepository.getById(theCustomer.getId());
        oldState.setContactName(theCustomer.getContactName());
        oldState.setPhone(theCustomer.getPhone());
        oldState.setCompanyName(theCustomer.getCompanyName());
        customerRepository.save(oldState);
        return "editSuccess";
    }

    @GetMapping("customer/add/")
    public String addCustomer(Model model){
        Customer thisCustomer=new Customer();
        model.addAttribute("customerToAdd",thisCustomer);
        return "addCustomerForm";
    }
    @PostMapping("/addCustomer")
    public String addedCustomer(@ModelAttribute("customerToAdd") Customer theCustomer){
        Customer newCustomer=new Customer();
        newCustomer.setId(theCustomer.getId());
        newCustomer.setContactName(theCustomer.getContactName());
        newCustomer.setPhone(theCustomer.getPhone());
        newCustomer.setCompanyName(theCustomer.getCompanyName());
        customerRepository.save(newCustomer);
        return "addSuccess";
    }


}
