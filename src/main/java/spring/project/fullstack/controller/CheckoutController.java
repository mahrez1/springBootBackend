package spring.project.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.project.fullstack.dto.Purchase;
import spring.project.fullstack.dto.PurchaseResponse;
import spring.project.fullstack.service.CheckoutService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	private CheckoutService checkoutService ;
	
	@Autowired
	public CheckoutController(CheckoutService checkoutService)
	{
		this.checkoutService = checkoutService ;
	}
	
	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) 
	{
		PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase) ;
		return purchaseResponse ;
	}
	
	
	

}
