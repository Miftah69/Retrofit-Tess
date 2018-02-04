package com.example.miftah.retrofitnostraok.model;

import java.util.List;

public class PersonResponse {

	private List<Person> result;
	private String pages;
	private String elements;
	private String message;

	public void setResult(List<Person> result){
		this.result = result;
	}

	public List<Person> getResult(){
		return result;
	}

	public void setPages(String pages){
		this.pages = pages;
	}

	public String getPages(){
		return pages;
	}

	public void setElements(String elements){
		this.elements = elements;
	}

	public String getElements(){
		return elements;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

}