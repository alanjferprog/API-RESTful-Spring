package com.example.platzimarket.persistence.crud;

import com.example.platzimarket.domain.Purchase;
import com.example.platzimarket.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Long>
{
    //Querys methods
    Optional<List<Compra>> findByIdCliente(String idCliente);
}
