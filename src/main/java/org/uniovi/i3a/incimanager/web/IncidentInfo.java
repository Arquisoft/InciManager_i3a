package org.uniovi.i3a.incimanager.web;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IncidentInfo {
	private String name;
	private String description;
	private String location;
	private String asignee;
	private String expiration;
	private String tags;
	private String multimedia;
	private String properties;
	private String state;
}
