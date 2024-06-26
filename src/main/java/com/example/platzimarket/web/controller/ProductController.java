package com.example.platzimarket.web.controller;

import com.example.platzimarket.domain.Product;
import com.example.platzimarket.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequestMapping("/products")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code= 200, message = "OK")
    private ResponseEntity<List<Product>> getAll()
    {return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);}

    @GetMapping("{id}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found"),
    })
    private ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7")
                                                   @PathVariable("id") Long productId)
    {return productService.getProduct(productId)
            .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{id}")
    private ResponseEntity<List<Product>> getByCategory(@PathVariable("id") Long categoryId)
    {return productService.getByCategory(categoryId).filter(Predicate.not(List::isEmpty))
            .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    private ResponseEntity<Product> save(@RequestBody Product product)
    {return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);}

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable("id") Long productId)
    {
        /*if(productService.delete(productId)) {
            return new ResponseEntity(HttpStatus.OK);
        }  else { return new ResponseEntity(HttpStatus.NOT_FOUND);} */
        return new ResponseEntity(this.productService.delete(productId)
                ? HttpStatus.OK
                : HttpStatus.NOT_FOUND);
    }
}
