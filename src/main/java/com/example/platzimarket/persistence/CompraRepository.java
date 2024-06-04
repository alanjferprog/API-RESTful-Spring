package com.example.platzimarket.persistence;

import com.example.platzimarket.domain.Purchase;
import com.example.platzimarket.domain.repository.PurchaseRepository;
import com.example.platzimarket.persistence.crud.CompraCrudRepository;
import com.example.platzimarket.persistence.entity.Compra;
import com.example.platzimarket.persistence.entity.Producto;
import com.example.platzimarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository
{
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll()
    {return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());}

    @Override
    public Optional<List<Purchase>> getByClient(String clientId)
    {
        Optional<List<Compra>> compras= compraCrudRepository.findByIdCliente(clientId);
        return compras.map(c -> purchaseMapper.toPurchases(c));
    }

    @Override
    public Purchase save(Purchase purchase)
    {
        Compra compra= purchaseMapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        Compra compraGuardada= compraCrudRepository.save(compra);
        Purchase purchase1= purchaseMapper.toPurchase(compraGuardada);
        return purchase1;
    }
}
