package com.example.LaboBiochimie.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.LaboBiochimie.enums.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Roles roleName;

	public Long getId() {
		return id;
	}

	public Roles getRoleName() {
		return roleName;
	}

	public void setRolesName(Roles roleName) {
		this.roleName = roleName;
	}
}