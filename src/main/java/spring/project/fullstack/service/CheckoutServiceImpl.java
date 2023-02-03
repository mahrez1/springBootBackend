package spring.project.fullstack.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.project.fullstack.doa.CustomerRepository;
import spring.project.fullstack.dto.Purchase;
import spring.project.fullstack.dto.PurchaseResponse;
import spring.project.fullstack.entity.Customer;
import spring.project.fullstack.entity.Order;
import spring.project.fullstack.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private CustomerRepository customerRepository ;
	
	@Autowired
	public CheckoutServiceImpl (CustomerRepository customerRepository)
	{
		this.customerRepository = customerRepository ;
	}
	
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		Order order = purchase.getOrder();
		
		String orderTrackingNumber  = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		Set<OrderItem> orderItems = purchase.getOrderItems() ;
		orderItems.forEach(item->order.add(item));
		order.setBillingAdress(purchase.getBillingAddress());
		order.setShippingAdress(purchase.getShippingAddress());
		Customer customer = purchase.getCustomer() ;
		customer.add(order);
		customerRepository.save(customer) ;
		
		
		return new PurchaseResponse(orderTrackingNumber) ;
	}

	private String generateOrderTrackingNumber() {
		return UUID.randomUUID().toString() ;
	}

	
	
	
	
}
