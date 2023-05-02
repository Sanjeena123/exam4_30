package shoppingCardnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import shoppingCardnew.entity.Customer;
import shoppingCardnew.entity.FoodItem;
import shoppingCardnew.model.CustomerModel;
import shoppingCardnew.model.FoodItemModel;
import shoppingCardnew.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    public String saveCustomerInfo(List<CustomerModel> customerModels) {
        for (CustomerModel customerModel : customerModels) {
            Customer customer = new Customer();
            if (customerModel.getCustName() != "" && customerModel.getCountry() != "") {
                customer.setCustName(customerModel.getCustName());
                customer.setCountry(customerModel.getCountry());
            } else {
                return "Please provide valid country and name.";
            }

            if (mobileNumberValidation(customerModel.getMobileNumber())) {
                customer.setMobileNumber(customerModel.getMobileNumber());
            } else {
                return "Please provide valid mobile number";
            }

            List<FoodItemModel> foodItems = customerModel.getFoods();
            if (!foodItems.isEmpty()) {
                for (FoodItemModel foodItemModel : foodItems) {
                    FoodItem foodItem = new FoodItem();
                    if (foodItemModel.getItemName() != "" && foodItemModel.getItemQuantity() != 0) {
                        foodItem.setItemName(foodItemModel.getItemName());
                        foodItem.setItemQuantity(foodItemModel.getItemQuantity());
                        customer.addFoodItems(foodItem);
                    } else {
                        return "Please enter valid item name or product quantity";
                    }
                }
            } else {
                return "Please provide at least one items";
            }

            try {

                this.customerRepository.save(customer);

            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }

        }
        return "success";
    }

    public boolean mobileNumberValidation(long mobileNumber) {
        String mobNumber = Long.toString(mobileNumber);
        if (mobNumber != "" && mobNumber.length() >= 10) {
            return true;
        }
        return false;
    }

    public CustomerModel getCustomerData(Integer id) {
        CustomerModel customerModel = new CustomerModel();
        try {
            Optional<Customer> optionalUser = this.customerRepository.findById(id);

            Customer customer = optionalUser.get();
            if (customer != null) {

                customerModel.setCustName(customer.getCustName());
                customerModel.setCountry(customer.getCountry());
                customerModel.setMobileNumber(customer.getMobileNumber());

                List<FoodItem> foodItemList = customer.getFoodItems();
                List<FoodItemModel> foodItemModelList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(foodItemList)) {
                    for (FoodItem foodItem : foodItemList) {
                        FoodItemModel foodItemModel = new FoodItemModel();
                        foodItemModel.setItemName(foodItem.getItemName());
                        foodItemModel.setItemQuantity(foodItem.getItemQuantity());
                        foodItemModelList.add(foodItemModel);
                    }
                    customerModel.setFoods(foodItemModelList);
                }

            } else {
                return new CustomerModel();
            }

        } catch (Exception e) {
            System.err.println("Error Details ::" + e.getMessage());
            return new CustomerModel();
        }
        return customerModel;
    }

    public String deleteCustomerById(Integer id) {
        try {
            this.customerRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error Details ::" + e.getMessage());
            return e.getMessage();
        }
        return "Successfully deleted";
    }
}
