
package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_excpetions.ResourceNotFoundException;
import com.app.dao.OrderRepository;
import com.app.dao.PaymentRepository;
import com.app.dao.UserRepository;
import com.app.dto.PaymentDTO;
import com.app.pojos.Order;
import com.app.pojos.Payment;
import com.app.pojos.User;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Payment> getAllPaymentDetails() {

		return paymentRepo.findAll();
	}

	@Override
	public Payment getPaymentDetails(int paymentId) {

		return paymentRepo.findById(paymentId).orElseThrow(()->new ResourceNotFoundException("Invalid Order id !!!!!! Id is : " + paymentId));
	}

	@Override
	public PaymentDTO savePaymentDetails(int orderId, PaymentDTO payment) {

		Order order = orderRepo.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Invalid Order id !!!!!! Id is : " + orderId));
		payment.setOrderId(order);
		User user = order.getUserId();
		payment.setUserId(user);
		Payment pay = mapper.map(payment, Payment.class);
		Payment persistentPayment = paymentRepo.save(pay);
		return mapper.map(persistentPayment, PaymentDTO.class);
	}

	@Override
	public String deletePaymentDetails(int paymentId) {
		
		String mesg = "Deletion of Payment details failed Invalid Id!!!!!!!!!!!";

		if (paymentRepo.existsById(paymentId)) {
			paymentRepo.deleteById(paymentId);
			mesg = "Payment details deleted successfully , for Payment id :" + paymentId;
		}

		return mesg;
	}

	
	/*
	 * @Override public Payment updatePaymentDetails(Order updatedPayment) {
	 * 
	 * return paymentRepo.save(updatedPayment); }
	 */

	

}