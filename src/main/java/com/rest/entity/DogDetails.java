/**
 * 
 */
package com.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Pratik Dutta
 *
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dog_details")
@EntityListeners(AuditingEntityListener.class)
public class DogDetails  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dog_id")
	private Long dogId;

	@Column(name = "dog_age")
	private Integer dogAge;

	@Column(name = "dog_color")
	private String dogColor;

	@Column(name = "availability")
	private Character availability;

	
	@Column(name = "vaccined")
	private Character vaccnitated;

	@Column(name = "dog_name")
	private String dogName;

	@Column(name = "dog_reg_no")
	private String regNo;
	
//	@ManyToOne
//	@JoinColumn(name="owner_id", nullable=false)
//	private DogOwnerDetails ownerDetail;
	
	@ManyToOne
    @JoinColumn(name = "owner_id", nullable=false)
	@JsonBackReference
    private DogOwnerDetails dogOwner;

}
