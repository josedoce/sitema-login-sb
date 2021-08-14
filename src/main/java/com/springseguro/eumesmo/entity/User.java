package com.springseguro.eumesmo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "precisamos de um nome!")
	@Column(nullable = false)
	private String nome;
	
	@NotEmpty(message = "precisamos de um email!")
	@Column(nullable = false)
	private String email;
	
	@NotEmpty(message = "precisamos de uma senha!")
	@Column(nullable = false)
	private String password;
	
	@Column(name = "active")
	private Boolean active;
	
	@ManyToMany(cascade = {CascadeType.MERGE})
	private Set<Role> roles;
}
