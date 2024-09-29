package com.brunobasques_jsp.third_challange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunobasques_jsp.third_challange.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
