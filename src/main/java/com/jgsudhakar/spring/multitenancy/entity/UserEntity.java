package com.jgsudhakar.spring.multitenancy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "USER_TBL")
public class UserEntity implements Serializable{

	/**
	 * Default Serial Id
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "UUID_SEQ")
	@SequenceGenerator(initialValue = 1,allocationSize = 1,name = "UUID_SEQ")
	@Column(name = "UUID")
	private Long id;

	@Column(name = "FIRST_NAME")
	private String userFirstName;
	
	@Column(name = "LAST_NAME")
	private String userLastName;

	@Column(name = "TENANT_ID")
	private String tenantId;

}
