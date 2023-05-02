package shoppingCardnew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shoppingCardnew.model.CustomerModel;
import shoppingCardnew.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerRepository {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/saveCustomerData")
    public String saveCustomerData(@RequestBody List<CustomerModel> customers) {
        String response = customerService.saveCustomerInfo(customers);
        return response;
    }

    @GetMapping(value = "/getCustomerDataById/{id}")
    public CustomerModel getCustomerDataById(@PathVariable("id") Integer id){
        CustomerModel customerModel = customerService.getCustomerData(id);
        return customerModel;
    }

    @DeleteMapping(value = "/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id){
        String response = customerService.deleteCustomerById(id);
        return response;
    }
}

