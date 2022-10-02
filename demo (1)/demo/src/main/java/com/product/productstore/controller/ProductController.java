package com.product.productstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.product.productstore.exception.ResourceNotFoundException;
import com.product.productstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.productstore.model.Product;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // get all products
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    // create products rest api
    @PostMapping("/products")
    public Product createEmployee(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // get product by id rest api
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id :" + id));
        product.calculate(product.getProductPrice());
        return ResponseEntity.ok(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));

        product.setProductName(productDetails.getProductName());
        product.setProductType(productDetails.getProductType());
        product.setProductCategory(productDetails.getProductCategory());
        product.setProductPrice(productDetails.getProductPrice());
        product.setTotalPrice(product.calculate(product.getProductPrice()));

        Product updatedProduct = productRepository.save(product);

        return ResponseEntity.ok(updatedProduct);
    }
    // delete product rest api
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id){
        Product employee = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));

        productRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
