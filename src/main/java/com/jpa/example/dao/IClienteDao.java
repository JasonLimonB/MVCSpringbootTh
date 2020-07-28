package com.jpa.example.dao;

import com.jpa.example.entity.Cliente;

import java.util.List;

public interface IClienteDao {

    public List<Cliente> findAll();

    public void guardarCliente(Cliente cliente);

    public Cliente findOne(Long id);

    public void delete(Long id);

}
