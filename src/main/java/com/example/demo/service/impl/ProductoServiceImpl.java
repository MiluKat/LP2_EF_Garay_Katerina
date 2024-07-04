package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductoEntity;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public List<ProductoEntity> buscarTodosProductos() {
		return productoRepository.findAll();
	}

	@Override
	public ProductoEntity buscarProductoPorId(Long id) {
		return productoRepository.findById(id).get();
	}

	@Override
	public ProductoEntity crearProducto(ProductoEntity productoEntity) {
		return productoRepository.save(productoEntity);
	}

	@Override
	public ProductoEntity actualizarProducto(ProductoEntity productoEntity) {
		ProductoEntity productoBuscado = buscarProductoPorId(productoEntity.getProductoId());
		if(productoBuscado != null) { // Si ya existe, tiene que actualizar
			productoBuscado.setNombre(productoEntity.getNombre());
			productoBuscado.setPrecio(productoEntity.getPrecio());
			productoBuscado.setStock(productoEntity.getStock());
			productoBuscado.setUrlImagen("");
			return productoRepository.save(productoBuscado);
		}
		return null;
	}

	@Override
	public void eliminarProducto(Long id) {
		productoRepository.deleteById(id);
		
	}

	@Override
	public ProductoEntity buscarProductoById(Integer id) {
		return productoRepository.findById(id.longValue()).get();
	}







}
