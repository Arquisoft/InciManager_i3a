package org.uniovi.i3a.incimanager.web;

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAsignee() {
		return asignee;
	}
	public void setAsignee(String asignee) {
		this.asignee = asignee;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getMultimedia() {
		return multimedia;
	}
	public void setMultimedia(String multimedia) {
		this.multimedia = multimedia;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public IncidentInfo(String name, String description, String location, String asignee, String expiration,
			String tags, String multimedia, String properties, String state) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.asignee = asignee;
		this.expiration = expiration;
		this.tags = tags;
		this.multimedia = multimedia;
		this.properties = properties;
		this.state = state;
	}
}
