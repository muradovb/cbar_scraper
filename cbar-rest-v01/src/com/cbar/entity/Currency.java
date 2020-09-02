package com.cbar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="currencies")
public class Currency {

	//db fields
	@Id
	@Column(name="code")
	private String code;
	@Column(name="name")
	private String name;
	@Column(name="value")
	private String value;
	
	//constructors
	public Currency() {

	}
	
	public Currency(String code, String name, String value) {
		this.code=code;
		this.name=name;
		this.value=value;
	}

	//methods
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Currency [code=" + code + ", name=" + name + ", value=" + value + "]";
	}
	
	

}
