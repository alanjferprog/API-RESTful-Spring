package com.example.platzimarket.domain.service;

import com.example.platzimarket.domain.Product;
import com.example.platzimarket.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll()
    {return productRepository.getAll();}

    public Optional<Product> getProduct(Long productId)
    {return productRepository.getProduct(productId);}

    public Optional<List<Product>> getByCategory(Long categoryId)
    {return productRepository.getByCategory(categoryId);}

    public Product save(Product product)
    {return productRepository.save(product);}

    @Transactional
    public boolean delete(Long productId)
    {
        /*return getProduct(productId).map(prod -> {
        productRepository.delete(productId);
        return true;
        }).orElse(false);*/

        //El metodo de arriba es mucho mas costoso en termino de rendimiento. Este es mas optimo.
        try {
            productRepository.delete(productId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
