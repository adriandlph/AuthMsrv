
package com.adriandlph.auth.data.model;

import com.adriandlph.auth.data.enumeration.EventType;
import java.util.Calendar;

/**
 *
 * @author adriandlph
 *
 */
public class EventModel {
	protected Long id;
	protected EventType eventType;
	protected String arguments;
	protected Calendar generatedAt;

	public EventModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public Calendar getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(Calendar generatedAt) {
		this.generatedAt = generatedAt;
	}

}
