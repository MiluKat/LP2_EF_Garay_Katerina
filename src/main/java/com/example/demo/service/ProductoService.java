package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ProductoEntity;

public interface ProductoService {
	List<ProductoEntity>buscarTodosProductos();
	/*ProductoEntity buscarProductoPorId(Integer id);
	ProductoEntity crearProducto(ProductoEntity productoEntity);
	ProductoEntity actualizarProducto(ProductoEntity productoEntity);
	void eliminarProducto(String id);*/
}
