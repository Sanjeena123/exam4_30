package shoppingCardnew.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Integer custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "mobile_number")
    private Long mobileNumber;
    private String country;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FoodItem> foodItems = new ArrayList<>();

    public void addFoodItems(FoodItem foodItem) {
        foodItems.add(foodItem);
        foodItem.setCustomer(this);
    }
}
