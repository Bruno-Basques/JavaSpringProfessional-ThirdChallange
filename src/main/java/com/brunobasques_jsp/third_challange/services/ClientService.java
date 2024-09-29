package com.brunobasques_jsp.third_challange.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brunobasques_jsp.third_challange.entities.Client;
import com.brunobasques_jsp.third_challange.repositories.ClientRepository;
import com.brunobasques_jsp.third_challange.services.exceptions.DatabaseException;
import com.brunobasques_jsp.third_challange.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

	@Autowired
    private ClientRepository repository;
    
	@Transactional(readOnly = true)
    public Client findById(Long id) {
        Client client = repository
        		.findById(id)
        		.orElseThrow(() -> 
        		new ResourceNotFoundException("Client with the Id %1$s was not found.".formatted(id)));
        return client;
    }

    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result;
    }

    @Transactional
    public Client insert(Client entity) {
        entity = repository.save(entity);
        return entity;
    }

    @Transactional
    public Client update(Long id, Client client) {
        try {
            Client entity = repository.getReferenceById(id);
            copyNewClientToOldClient(client, entity);
            entity = repository.save(entity);
            return entity;
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Client with the Id %1$s was not found to be updated.".formatted(id));
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
    	if (!repository.existsById(id)) {
    		throw new ResourceNotFoundException("Client with the Id %1$s was not found to be deleted.".formatted(id));
    	}
    	try {
            repository.deleteById(id);    		
    	}
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referencial integrity fail.");
        }
    }
    
    private void copyNewClientToOldClient(Client newInformationClient, Client oldInformationClient) {
    	oldInformationClient.setName(newInformationClient.getName());
    	oldInformationClient.setCpf(newInformationClient.getCpf());
    	oldInformationClient.setIncome(newInformationClient.getIncome());
    	oldInformationClient.setBirthDate(newInformationClient.getBirthDate());
    	oldInformationClient.setChildren(newInformationClient.getChildren());
    }
}
