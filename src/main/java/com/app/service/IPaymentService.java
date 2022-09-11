package com.app.service;

import java.util.List;

import com.app.dto.PaymentDTO;
import com.app.pojos.Payment;



public interface IPaymentService {

	//get all details
	  List<Payment> getAllPaymentDetails();
	//get user details by specified id
	  Payment getPaymentDetails(int paymentId);
	//save new user details
	  PaymentDTO savePaymentDetails(int orderId,PaymentDTO payment); 
	//delete user details
	  String deletePaymentDetails(int paymentId);
	
	 // Payment updatePaymentDetails(Payment updatedPayment);
	 
}
