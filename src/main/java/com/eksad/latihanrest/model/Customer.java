package com.eksad.latihanrest.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("Customer")// Optional apabila nama class sama dengan nama DiscriminatoeValue
public class Customer extends Person {
	@Column(name ="card_number")
	private String cardNum;
}
