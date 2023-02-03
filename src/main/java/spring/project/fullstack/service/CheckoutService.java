package spring.project.fullstack.service;

import spring.project.fullstack.dto.Purchase;
import spring.project.fullstack.dto.PurchaseResponse;

public interface CheckoutService {

	PurchaseResponse placeOrder(Purchase purchase) ;
}
