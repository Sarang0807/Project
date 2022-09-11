
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

import com.app.dto.OrderDTO;
import com.app.pojos.Order;
import com.app.service.IOrderService;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class OrderController {
	@Autowired
	private IOrderService orderService;


	public OrderController() {
		System.out.println("in ctor of " + getClass());
	}


	@GetMapping
	public ResponseEntity<?> listAllOrders() {
		System.out.println("in list Orders");
		List<Order> list = orderService.getAllOrderDetails();
		if (list.isEmpty())
			return new ResponseEntity<>("Empty Order List !!!!", HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}


	@PostMapping("/{userId}")
	public ResponseEntity<OrderDTO> saveUserDetails(@PathVariable int userId, @RequestBody @Valid OrderDTO order)
	{
		System.out.println(userId+" in save Order " + order);// id : null...
		return new ResponseEntity<>(orderService.saveOrderDetails(userId,order), HttpStatus.CREATED);
	}

	

	@DeleteMapping("/{orderId}")
	public String deleteUserDetails(@PathVariable @Range(min = 1, message = "Invalid user id!!!") int orderId) {
		System.out.println("in del Order " + orderId);
		return orderService.deleteOrderDetails(orderId);
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> getUserDetails(@PathVariable int id) {
		System.out.println("in get Order " + id);
		Order order = orderService.getOrderDetails(id);
		System.out.println("Order class " + order.getClass());
		return ResponseEntity.ok(order);

	}


	/*
	 * @PutMapping public Order updateOrderDetails(@RequestBody @Valid Order order)
	 * { System.out.println("in update Order " + order);// id not null return
	 * orderService.updateOrderDetails(order); }
	 */


}