package com.example.msclientes.controllers;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.msclientes.models.Cliente;
import com.example.msclientes.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	private final Logger logger = Logger.getLogger(ClienteController.class.getName());

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity salvar(@RequestBody Cliente cliente) {
		Cliente clt = this.clienteService.salvar(cliente);
		logger.info("Salvar cliente com BI: " + cliente.getBi());
		// Usado para apresentar rotas dinamicas.
		// EX: http://localhost:8080/clientes?bi=12345LA049
		URI headerLocation = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.query("bi={bi}")
				.buildAndExpand(clt.getBi())
				.toUri();
		return ResponseEntity.created(headerLocation).build();
	}

	@PatchMapping("/{id_cliente}")
	// @ApiOperation("Atualizar cliente.")
	// @ApiResponse(code = 201, message = "Cliente atualizado com sucesso.")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity atualizar(@RequestBody Cliente cliente, @PathVariable("id_cliente") Integer IdCliente) {
		logger.info("Atualizar cliente com ID: " + IdCliente);

		Cliente client = this.clienteService.getCliente(IdCliente);
		cliente.setIdCliente(client.getIdCliente());
		Cliente clt = this.clienteService.salvar(cliente);

		// Usado para apresentar rotas dinamicas.
		// EX: http://localhost:8080/clientes?bi=12345LA049
		URI headerLocation = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.query("bi={bi}")
				.buildAndExpand(clt.getBi())
				.toUri();
		return ResponseEntity.created(headerLocation).build();
	}

	@GetMapping
	// @ApiOperation("Listar todos clientes.")
	// @ApiResponse(code = 302, message = "Clientes encontrados com sucesso.")
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> listarClientes() {
		logger.info("Atualizar todos os clientes com ID: ");
		return this.clienteService.listarClientes();
	}

	@GetMapping("/{id_cliente}")
	// @ApiOperation("Buscar cliente.")
	// @ApiResponse(code = 200, message = "Cliente encontrado com sucesso.")
	@ResponseStatus(HttpStatus.OK)
	public Cliente getCliente(@PathVariable("id_cliente") Integer IdCliente) {
		logger.info("Buscar cliente com ID: " + IdCliente);
		return this.clienteService.getCliente(IdCliente);
	}

	@DeleteMapping("/{id_cliente}")
	// @ApiOperation("Eliminar cliente.")
	// @ApiResponse(code = 200, message = "Cliente encontrado com sucesso.")
	@ResponseStatus(HttpStatus.OK)
	public void eliminar(@PathVariable("id_cliente") Integer IdCliente) {
		logger.info("Eliminar cliente com ID: " + IdCliente);
		this.clienteService.eliminar(IdCliente);
	}

	@GetMapping(params = "bi")
	// @ApiOperation("Buscar cliente.")
	// @ApiResponse(code = 200, message = "Cliente encontrado com sucesso.")
	@ResponseStatus(HttpStatus.OK)
	public Cliente getClienteByBi(@RequestParam("bi") String bi) {
		logger.info("Buscar cliente com BI: " + bi);
		return this.clienteService.findByBi(bi);
	}

}
