package com.example.platzimarket.persistence.crud;

import com.example.platzimarket.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer>
{
    /* @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> getByCategoria(int idCategoria);
    --Al tener la Query arriba el metodo se puede llamar de cualquier manera.
    */

    //Buscar mediante Query Methods. Es necesario seguir el CamelCase. Y el argumento debe ser tal cual la clase.
    List<Producto> findByIdCategoriaOrderByNombreAsc(Long idCategoria);

    Optional<Producto> findByIdProducto(Long idProducto);

    void removeByIdProducto(Long idProducto);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
