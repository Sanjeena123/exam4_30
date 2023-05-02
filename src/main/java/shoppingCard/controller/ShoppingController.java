package shoppingCard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import shoppingCard.model.CustomerModel;
import shoppingCard.repository.CustomerRepository;
import shoppingCard.service.CustomerItemService;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = "/shoppingCart")
public class ShoppingController {
    @Autowired
    private CustomerItemService customerItemService;

    @PostMapping(value = "/saveRecord")
    public String saveRecord(@RequestBody List<CustomerModel> customerModels){
        String msg = customerItemService.saveRecord(customerModels);
        return msg;

    }
    @GetMapping(value = "/getUserData/{id}",produces = MediaType.APPLICATION_ATOM_XML_VALUE)
    public CustomerModel getCustomerData(@PathVariable("id")Integer id){
        CustomerModel customerModel = customerItemService.getCustomer(id);
        System.out.println(customerModel);
        return customerModel;
    }
}
