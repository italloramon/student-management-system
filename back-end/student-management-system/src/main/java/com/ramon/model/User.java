package com.ramon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
public class User {
	
	@Getter @Setter protected String name;
	
	@Getter @Setter protected String cpf;
	
	@Getter @Setter protected String email;
	
}
