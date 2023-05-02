package shoppingCard.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingCard.entity.Customer;
import shoppingCard.entity.Items;
import shoppingCard.model.CustomerModel;
import shoppingCard.model.ItemModel;
import shoppingCard.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerItemService {
    public CustomerRepository customerRepository;

    public String saveRecord(List<CustomerModel> customerModels) {
        for (CustomerModel customerModel : customerModels) {
            Customer customer = new Customer();
            customer.setCustName(customerModel.getCustName());
            customer.setMobileNumber(customerModel.getMobileNumber());
            customer.setCountry(customerModel.getCountry());
            List<ItemModel> itemModelList = customerModel.getItem();
            for (ItemModel itemModel : itemModelList) {
                Items items = new Items();
                items.setName(itemModel.getName());
                items.setQuantity(itemModel.getQuantity());
                customer.additem(items);
            }
            try {
                if (customerModel.isValidUser()) {
                    this.customerRepository.save(customer);
                } else {
                    return "Not a valid User!";
                }
            } catch (Exception e) {
                System.err.println("Error Detail::" + e.getMessage());
                return e.getMessage();
            }
        }
        return "success";
    }
public CustomerModel getCustomer(Integer id){
        CustomerModel customerModel = new CustomerModel();
        try {
            Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
            Customer customer = optionalCustomer.get();
            if(customer!=null) {
                customerModel.setCustName(customer.getCustName());
                customerModel.setMobileNumber(customer.getMobileNumber());
                customerModel.setCountry(customer.getCountry());
                customerModel.setValidUser(true);
            }else {
                customerModel.setValidUser(false);
            }
        }catch (Exception e){
            System.err.println("Error Detail::"+e.getMessage());
            return new CustomerModel();
        }
        return customerModel;
}
}
