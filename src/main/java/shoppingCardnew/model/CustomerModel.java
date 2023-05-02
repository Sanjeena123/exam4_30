package shoppingCardnew.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerModel {
    private Integer custId;
    private String custName;
    private Long mobileNumber;
    private String country;
    private List<FoodItemModel> foods;
}
