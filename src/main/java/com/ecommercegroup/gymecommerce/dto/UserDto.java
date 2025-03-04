package com.ecommercegroup.gymecommerce.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.ecommercegroup.gymecommerce.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size (min = 2, max = 100, message = "Nome deve ter entre 2 a 100 caracteres")
    private String name;
    
    private String phone;
    private String email;
    private LocalDate birthdate;
    
    @NotBlank(message = "Cpf é obrigatório")
    private String cpf;
    private String password;
    
    public UserDto() {
    }

	public UserDto(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.birthdate = user.getBirthdate();
		this.cpf = user.getCpf();
		this.password = user.getPassword();
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
