
package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_excpetions.ResourceNotFoundException;
import com.app.dao.CategoryRepository;
import com.app.dto.CategoryDTO;
import com.app.pojos.Category;

@Service

@Transactional
public class CatServiceImpl implements ICatService {
	// dep : emp repo.

	@Autowired
	private CategoryRepository catRepo;

	@Value("${file.upload.location}")
	private String baseFolder;

	private ModelMapper mapper;

	@Override
	public List<Category> getAllCategoryDetails() {

		return catRepo.findAll();
	}

	@Override
	public CategoryDTO saveCategoryDetails(CategoryDTO cat) {

		System.out.println("1"+cat);
		Category cate = mapper.map(cat, Category.class);// EmployeeDto to Employee received from frontEnd
		System.out.println("2");
		Category persistentCat = catRepo.save(cate);// method rets PERSISTENT emp ref
		// map entity --> dto
		System.out.println("3");
		return mapper.map(persistentCat, CategoryDTO.class);
	}

	@Override
	public String deleteCategoryDetails(int catId) {
		String mesg = "Deletion of Category details failed Invalid Id!!!!!!!!!!!";

		if (catRepo.existsById(catId)) {
			catRepo.deleteById(catId);
			mesg = "Category details deleted successfully , for Category id :" + catId;
		}

		return mesg;
	}

	@Override
	public Category getCategoryDetails(int catId) {

		return catRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Category id !!!!!! Id is : " + catId));
	}

	@Override
	public Category updateCategoryDetails(Category updatedCategory) {
		return catRepo.save(updatedCategory);
	}

	@Override
	public CategoryDTO storeImage(int catId, MultipartFile imageFile) throws IOException {
		Category cat = catRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Err in service of Image adding!!!!"));
		String completePath = baseFolder + File.separator + imageFile.getOriginalFilename();
		System.out.println("Complete Path : " + completePath);

		System.out.println("Copying no of bytes : "
				+ Files.copy(imageFile.getInputStream(), Paths.get(completePath), StandardCopyOption.REPLACE_EXISTING));

		// save complete path to the image in db
		cat.setCatImage(completePath);// save complete path to the file in db

		return mapper.map(cat, CategoryDTO.class);
	}

	@Override
	public byte[] restoreImage(int catId) throws IOException {
		// get cat details by cat id
		Category cat = catRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Err in service of Image adding!!!!"));

		// get complete image path from db ->> extract image contents n send to the
		// caller
		String path = cat.getCatImage();
		System.out.println("Image Path : " + path);
		// API java.nio.file.Files class : Files.readAllBytes(Paths.get(path))
		return Files.readAllBytes(Paths.get(path));
	}

}