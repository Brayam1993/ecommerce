package com.example.ecomerce.service;

import com.example.ecomerce.entity.Cliente;
import com.example.ecomerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente actualizarCliente(Long id, Cliente cliente) {
        cliente.setCliente_id(id);
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
