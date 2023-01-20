package com.web3.notification.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.web3.notification.exception.BadRequestException;
import com.web3.notification.exception.CustomException;
import com.web3.notification.exception.NotFoundException;
import com.web3.notification.impl.CategoryImpl;
import com.web3.notification.model.CategoryModel;
import com.web3.notification.service.CategoryService;
import com.web3.notification.utility.Utility;

@RestController
public class CategoryController {

  CategoryService service = new CategoryImpl();
  CustomException customException = new CustomException();

  @GetMapping("/category")
  public ResponseEntity<?> getAllCategory() {
    try {
      return new ResponseEntity<List<CategoryModel>>(service.getAllCategory(), HttpStatus.CREATED);
    }catch(Exception error) {
      return new ResponseEntity<String>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/category")
  public ResponseEntity<?> createNewCategory(@RequestBody CategoryModel categoryModel) {
    try {
      return new ResponseEntity<String>(service.createNewCategory(categoryModel), HttpStatus.CREATED);
    }catch(BadRequestException badRequest){
      return new ResponseEntity<String>(Utility.errorObject(badRequest.getMessage()), HttpStatus.BAD_REQUEST);
    }catch (Exception error) {
      return new ResponseEntity<String>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/category/{id}")
  public  ResponseEntity<?> getCategoryDetailsById(@PathVariable("id") int id) {
    try { 
    return new ResponseEntity<CategoryModel>(service.getCategoryById(id),HttpStatus.OK);
    }catch (NotFoundException notFoundException) {
      return new ResponseEntity<String>(Utility.errorObject(notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    } catch (Exception error) {
      return new ResponseEntity<String>(Utility.errorObject(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/category/{id}")
  public ResponseEntity<?> updateAnExistingCategory(@PathVariable int id, @RequestBody CategoryModel categoryModel) {
    try {
      return new ResponseEntity<CategoryModel>(service.updateAnExistingCategory(categoryModel, id), HttpStatus.OK);
    } catch (NotFoundException notFoundException) {
      return new ResponseEntity<String>(Utility.errorObject(notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    } catch (Exception error) {
      return new ResponseEntity<String>(Utility.errorObject(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @DeleteMapping("/category/{id}")
  public ResponseEntity<?> deleteCategoryDetailsById(@PathVariable("id") int id) {
      try {
          return new ResponseEntity<String>(service.deleteCategoryDetailsById(id),
                  HttpStatus.OK);
      } catch (NotFoundException notFoundException) {
          return new ResponseEntity<String>(Utility.errorObject(notFoundException.getMessage()), HttpStatus.NOT_FOUND);
      } catch (Exception e) {
          return new ResponseEntity<String>(Utility.errorObject(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
}
