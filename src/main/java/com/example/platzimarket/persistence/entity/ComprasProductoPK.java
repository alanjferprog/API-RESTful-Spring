package com.example.platzimarket.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ComprasProductoPK implements Serializable
{
    @Column(name = "id_compra")
    private Long idCompra;
    @Column(name = "id_producto")
    private Long idProducto;

    public Long getIdProducto() {
        return idProducto;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
}
