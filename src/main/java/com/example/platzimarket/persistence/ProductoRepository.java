package com.example.platzimarket.persistence;

import com.example.platzimarket.domain.Product;
import com.example.platzimarket.domain.repository.ProductRepository;
import com.example.platzimarket.persistence.crud.ProductoCrudRepository;
import com.example.platzimarket.persistence.entity.Producto;
import com.example.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Le indica a Spring esta clase es la que interactua con la base de datos.
public class ProductoRepository implements ProductRepository
{
    @Autowired //Le  da el control para que genere instancias de estas clases cuando sea necesario.
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAll()
    {   List<Producto> productos= (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);}

    @Override
    public Optional<List<Product>> getByCategory(Long categoryId)
    {   List<Producto> productos= productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos)); }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity)
    {   Optional<List<Producto>> productos= productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> productMapper.toProducts(prods));}

    @Override
    public Optional<Product> getProduct(Long productId)
    { Optional<Producto> producto = productoCrudRepository.findByIdProducto(productId);
        return producto.map(prod -> productMapper.toProduct(prod)) ;}

    @Override
    public Product save(Product product)
    {   Producto producto= productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));}

    @Override
    public void delete(Long productId)
    {productoCrudRepository.removeByIdProducto(productId);}


}
