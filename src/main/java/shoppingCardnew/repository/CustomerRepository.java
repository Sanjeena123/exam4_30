package shoppingCardnew.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shoppingCardnew.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}

