package com.example.msclientes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.msclientes.exceptions.ClienteNotFoundException;
import com.example.msclientes.models.Cliente;
import com.example.msclientes.repositories.ClienteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvar(Cliente cliente) {
		log.info("Salvando cliente com BI: " + cliente.getBi());
		return this.clienteRepository.save(cliente);
	}

	public Cliente getCliente(Integer idCliente) {
		log.info("Buncando cliente com ID: " + idCliente);
		return this.clienteRepository
				.findById(idCliente)
				.orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado. Id invalido :" + idCliente));
	}

	public void eliminar(Integer idCliente) {
		log.info("Elimando cliente com ID: " + idCliente);
		Cliente cliente = this.getCliente(idCliente);
		this.clienteRepository.delete(cliente);
	}

	public List<Cliente> listarClientes() {
		log.info("Listando todos os clientes.");
		return this.clienteRepository.findAll();
	}

	public Cliente findByBi(String bi) {
		log.info("Buscando cliente com BI: " + bi);

		return this.clienteRepository
				.findAllByBi(bi)
				.orElseThrow(() -> new ClienteNotFoundException(
						"Cliente não encontrado. Bi invalido . Bi: " + bi));
	}

}
