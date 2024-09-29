package com.brunobasques_jsp.third_challange.dto;

import java.time.LocalDate;

import com.brunobasques_jsp.third_challange.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class ClientDTO {

    private Long id;
    
    @NotBlank(message = "The field 'name' is required.")
    private String name;
    
    private String cpf;
    
    private Double income;
    
    @PastOrPresent(message = "The field 'birthdate' can can only be a past date, including the current day.")
    private LocalDate birthDate;
    
    private Integer children;
    
	public ClientDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}		
}
