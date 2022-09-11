
package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PaymentDTO;
import com.app.pojos.Payment;
import com.app.service.IPaymentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class PaymentController {
	@Autowired
	private IPaymentService paymentService;


	public PaymentController() {
		System.out.println("in ctor of " + getClass());
	}


	@GetMapping
	public ResponseEntity<?> listAllPayments() {
		System.out.println("in list Payments");
		List<Payment> list = paymentService.getAllPaymentDetails();
		if (list.isEmpty())
			return new ResponseEntity<>("Empty Payment List !!!!", HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}


	@PostMapping("/{orderId}")
	public ResponseEntity<PaymentDTO> saveUserDetails(@PathVariable int orderId, @RequestBody @Valid PaymentDTO payment)
	{
		System.out.println(orderId+" in save Payment " + payment);// id : null...
		return new ResponseEntity<>(paymentService.savePaymentDetails(orderId,payment), HttpStatus.CREATED);
	}

	

	@DeleteMapping("/{paymentId}")
	public String deleteUserDetails(@PathVariable @Range(min = 1, message = "Invalid user id!!!") int paymentId) {
		System.out.println("in del Payment " + paymentId);
		return paymentService.deletePaymentDetails(paymentId);
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> getPaymentDetails(@PathVariable int id) {
		System.out.println("in get Order " + id);
		Payment payment = paymentService.getPaymentDetails(id);
		System.out.println("Payment class " + payment.getClass());
		return ResponseEntity.ok(payment);

	}


	/*
	 * @PutMapping public Payment updatePaymentDetails(@RequestBody @Valid Payment payment)
	 * { System.out.println("in update Payment " + payment);// id not null return
	 * paymentService.updatePaymentDetails(payment); }
	 */


}