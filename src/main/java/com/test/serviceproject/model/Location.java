package com.test.serviceproject.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
//@Value
public class Location {

    private String code, name, description;
    private Coordinates coordinates;
    private Location parent;
    private Set<Location> children;
    
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public Location getParent() {
		return parent;
	}
	public Set<Location> getChildren() {
		return children;
	}

}