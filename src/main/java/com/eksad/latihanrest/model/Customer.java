package com.eksad.latihanrest.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("Customer")// Optional apabila nama class sama dengan nama DiscriminatoeValue
public class Customer extends Person {
	@ApiModelProperty(value = "Cutomer Card Numbers")
	@Column(name ="card_number")
	private String cardNum;
}
