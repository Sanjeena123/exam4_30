package shoppingCard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shoppingCard.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
}
