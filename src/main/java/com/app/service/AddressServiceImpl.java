
package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_excpetions.ResourceNotFoundException;
import com.app.dao.AddressRepository;
import com.app.dto.AddressDTO;
import com.app.pojos.Address;

@Service

@Transactional
public class AddressServiceImpl implements IAddressService {
	// dep : emp repo.

	@Autowired
	private AddressRepository addRepo;
	@Autowired
	private ModelMapper mapper;
	@Override
	public List<Address> getAllUserAddresses() {
		
		return addRepo.findAll();
	}
	@Override
	public AddressDTO saveAddressDetails(AddressDTO address) {
		Address addr = mapper.map(address, Address.class);//EmployeeDto to Employee received from frontEnd\
		
		Address persistentAddr = addRepo.save(addr);// method rets PERSISTENT emp ref
		//map entity --> dto
		return mapper.map(persistentAddr, AddressDTO.class);
	}
	@Override
	public Address getAddressDetails(int addressId) {
		
		return addRepo.findById(addressId).orElseThrow(()->new ResourceNotFoundException("Invalid Address id !!!!!! Id is : " + addressId));
	}
	@Override
	public Address updateAddressDetails(Address updatedAddress) {
		
		return addRepo.save(updatedAddress);
	}

	

}
