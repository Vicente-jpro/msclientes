package com.example.msclientes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.msclientes.models.Cliente;
import com.example.msclientes.services.ClienteService;

@RestController
@RequestMapping("/clientes")
// @CrossOrigin("http://localhost:4200")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody Cliente cliente) {
		return this.clienteService.salvar(cliente);
	}

	@PatchMapping("/{id_cliente}")
	// @ApiOperation("Atualizar cliente.")
	// @ApiResponse(code = 201, message = "Cliente atualizado com sucesso.")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente atualizar(@RequestBody Cliente cliente, @PathVariable("id_cliente") Integer IdCliente) {
		Cliente client = this.clienteService.getCliente(IdCliente);
		cliente.setIdCliente(client.getIdCliente());
		return this.clienteService.salvar(cliente);
	}

	@GetMapping
	// @ApiOperation("Listar todos clientes.")
	// @ApiResponse(code = 302, message = "Clientes encontrados com sucesso.")
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> listarClientes() {
		return this.clienteService.listarClientes();
	}

	@GetMapping("/{id_cliente}")
	// @ApiOperation("Buscar cliente.")
	// @ApiResponse(code = 200, message = "Cliente encontrado com sucesso.")
	@ResponseStatus(HttpStatus.OK)
	public Cliente getCliente(@PathVariable("id_cliente") Integer IdCliente) {

		return this.clienteService.getCliente(IdCliente);
	}

	@DeleteMapping("/{id_cliente}")
	// @ApiOperation("Eliminar cliente.")
	// @ApiResponse(code = 200, message = "Cliente encontrado com sucesso.")
	@ResponseStatus(HttpStatus.OK)
	public void eliminar(@PathVariable("id_cliente") Integer IdCliente) {
		this.clienteService.eliminar(IdCliente);
	}

}
