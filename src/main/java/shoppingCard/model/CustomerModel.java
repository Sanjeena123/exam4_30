package shoppingCard.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class CustomerModel  {
    private String custName;
    private Long mobileNumber;
    private String country;
    private boolean isValidUser;
    private List<ItemModel> item;
}
