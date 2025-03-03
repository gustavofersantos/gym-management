package com.ecommercegroup.gymecommerce.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import com.ecommercegroup.gymecommerce.entities.User;

public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private UUID id;
    private String name;
    private String phone;
    private String email;
    private Date birthdate;
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

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
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
