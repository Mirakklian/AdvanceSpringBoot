package com.rest.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "dogDetailList")
@Table(name = "owner_details")
@EntityListeners(AuditingEntityListener.class)
public class DogOwnerDetails implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 8979628384203013243L;
	
	@Id
	@Column(name="owner_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="registration_Id" , nullable= false)
	private String registrationId;
	
	@Column(name="owner_name" , nullable= false)
	private String ownerName;
	
	@Column(name="address" , nullable= false)
	private String address;
	
	@Column(name="pin" , nullable= false)
	private String pin;
	
	@OneToMany(mappedBy="dogOwner", fetch= FetchType.LAZY , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<DogDetails> dogDetailList;	

//	@OneToMany(fetch= FetchType.LAZY , cascade = CascadeType.ALL)
//    @JoinColumn(name = "owner_id") // we need to duplicate the physical information
//    private Set<DogDetails> dogDetails;
}
