package spring.project.fullstack.dto;

import java.util.Set;

import lombok.Data;
import spring.project.fullstack.entity.Address;
import spring.project.fullstack.entity.Customer;
import spring.project.fullstack.entity.Order;
import spring.project.fullstack.entity.OrderItem;

@Data
public class Purchase {
	
	private Customer customer ;
	private Address shippingAddress ;
	private Address billingAddress ;
	private Order order ;
	private Set<OrderItem> orderItems ;

}
