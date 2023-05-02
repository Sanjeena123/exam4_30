package shoppingCard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer_info")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_Id")
    private Integer custID;
    @Column
    private String custName;
    @Column
    private Long mobileNumber;
    @Column
    private String country;
@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Items> items = new ArrayList<>();
public void additem(Items items){
    this.items.add(items);
    items.setCustomer(this);
}
}
