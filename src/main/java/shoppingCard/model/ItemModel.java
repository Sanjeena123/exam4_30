package shoppingCard.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Setter
@Getter
public class ItemModel {
    private String name;
    private Integer quantity;

}
