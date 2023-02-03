package spring.project.fullstack.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.fullstack.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer , Long> {

}
