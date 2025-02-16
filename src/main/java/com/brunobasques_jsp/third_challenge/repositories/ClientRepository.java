package com.brunobasques_jsp.third_challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunobasques_jsp.third_challenge.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
