package com.ecommercegroup.gymecommerce.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "tb_user")
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String phone;
    private String email;
    private Date birthdate;
    private String cpf;
    private String password;

    public User() {
    }

    public User(UUID id, String name, String phone, String email, Date birthdate, String cpf, String password) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
        this.cpf = cpf;
        this.password = password;
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

    @Override
    public int hashCode() {
        return Objects.hash(cpf, email, id, phone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
                && Objects.equals(id, other.id) && Objects.equals(phone, other.phone);
    }
}
