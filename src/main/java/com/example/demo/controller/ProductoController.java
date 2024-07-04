package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.DetallePedidoEntity;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;


@Controller
public class ProductoController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProductoService productoService;
	
	/*@Autowired
	private PdfService pdfService;*/
	
	@GetMapping("/menu")
	public String showMenu(HttpSession session, Model model) {
		if(session.getAttribute("usuario") == null) {
			return "redirect:/";
		}
		
		String correo = session.getAttribute("usuario").toString();
		UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
		model.addAttribute("foto", usuarioEntity.getUrlImagen());
															
		List<ProductoEntity>productos = productoService.buscarTodosProductos();
		model.addAttribute("productos", productos);
		
		return "menu";
	}
	
	@GetMapping("/lista_productos")
	public String home(Model model) {
		List<ProductoEntity>productos = productoService.buscarTodosProductos();
		model.addAttribute("productos", productos);
		return "lista_productos";
	}
	
	@GetMapping("/agregar_producto")
	public String showAgregarProducto(Model model) {
		model.addAttribute("producto", new ProductoEntity());
		return "agregar_producto";
	}
	
	@PostMapping("/registrar_producto")
	public String registrarProducto(ProductoEntity productoEntity) {
		productoService.crearProducto(productoEntity);
		return "redirect:/menu";
	}
	
	@GetMapping("/editar_producto/{id}")
	public String showEditarProducto(@PathVariable("id")Long id, Model model) {
		ProductoEntity productoBuscar = productoService.buscarProductoPorId(id);
	
		model.addAttribute("producto", productoBuscar);
		
		return "editar_producto";
	}
	
	@PostMapping("/editar_producto")
	public String editarEmpleado(Model model, ProductoEntity productoEntity) {
	
		productoService.actualizarProducto(productoEntity);
		return "redirect:/lista_productos";
	}
	
	@GetMapping("/buscar/{id}")
	public String buscarPorId(@PathVariable("id") Long id, Model model) {
		ProductoEntity productoEncontrado = productoService.buscarProductoPorId(id);
		model.addAttribute("producto", productoEncontrado);
		return "buscar";
	}
	
	
	@GetMapping("/delete/{id}")
	public String eliminarPorId(@PathVariable("id") Long id, Model model) {
		productoService.eliminarProducto(id);
		return "redirect:/lista_productos";
		
	}
	
}
