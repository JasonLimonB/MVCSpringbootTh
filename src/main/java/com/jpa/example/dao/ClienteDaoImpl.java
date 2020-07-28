package com.jpa.example.dao;

import com.jpa.example.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao{

    @PersistenceContext
    private EntityManager em;

    @Transactional( readOnly = true)
    @Override
    public List<Cliente> findAll() {
        return em.createQuery("from Cliente").getResultList();
    }

    @Override
    @Transactional
    public void guardarCliente( Cliente cliente ) {
        if(cliente.getId() != null && cliente.getId() > 0){
            em.merge(cliente);
        }else {
            em.persist(cliente);
        }
    }

    @Override
    @Transactional( readOnly = true)
    public Cliente findOne(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        em.remove(findOne(id));
    }

}
