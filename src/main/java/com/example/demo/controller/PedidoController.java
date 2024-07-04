package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.DetallePedidoEntity;
import com.example.demo.entity.PedidoEntity;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;

@Controller
public class PedidoController {


	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("/guardar_factura")
	public String guardarFactura(HttpSession session) {
		String correoString = (String) session.getAttribute("usuario");
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setCorreo(correoString);
		
		PedidoEntity pedidoEntity = new PedidoEntity();
		pedidoEntity.setFechaCompra(LocalDate.now());
		pedidoEntity.setUsuarioEntity(usuarioEntity);
		
		List<DetallePedidoEntity> detallePedidoEntityList = new ArrayList<DetallePedidoEntity>();
		Double total = 0.0;
		
		List<Pedido>productoSession = null;
		if(session.getAttribute("carrito") == null) {
			productoSession = new ArrayList<Pedido>();
		}else {
			productoSession = (List<Pedido>) session.getAttribute("carrito");
		}
		
		for(Pedido pedido: productoSession) {
			DetallePedidoEntity detallePedidoEntity = new DetallePedidoEntity();
			ProductoEntity productoEntity = new ProductoEntity();
			productoEntity.setProductoId(pedido.getProductoId().longValue());
			
			detallePedidoEntity.setProductoEntity(productoEntity);
			detallePedidoEntity.setCantidad(pedido.getCantidad());
			detallePedidoEntity.setPedidoEntity(pedidoEntity);
			detallePedidoEntityList.add(detallePedidoEntity);
			
		}
		pedidoEntity.setDetallePedido(detallePedidoEntityList);
		pedidoRepository.save(pedidoEntity);
		
		session.removeAttribute("carrito");
		return "redirect:/menu";
		
	}
}
