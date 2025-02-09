package com.adriandlph.auth.data.entity;

import com.adriandlph.auth.data.enumeration.EventType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 *
 * @author adriandlph
 *
 */
@Entity
@Table(name = "subscriber")
public class Subscriber {
	private static final int EVENT_FILTER_MAX_LENGTH = 1024;
	private static final int ENDPOINT_MAX_LENGTH = 1024;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "eventType", nullable = true)
	private EventType eventType;
	@Column(name = "eventFilter", nullable = true, length = EVENT_FILTER_MAX_LENGTH)
	private String eventFilter;
	@Column(name = "endpoint", nullable = false, length = ENDPOINT_MAX_LENGTH)
	private String endpoint;

	public Subscriber() {
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

	public String getEventFilter() {
		return eventFilter;
	}

	public void setEventFilter(String eventFilter) {
		this.eventFilter = eventFilter;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		if (!(obj instanceof Subscriber other)) return false;
		
		if (!Objects.equals(this.eventFilter, other.eventFilter)) return false;
		if (!Objects.equals(this.endpoint, other.endpoint)) return false;
		if (!Objects.equals(this.id, other.id)) return false;
		return Objects.equals(this.eventType, other.eventType);
	}

	@Override
	public String toString() {
		StringBuffer str;
		
		str = new StringBuffer("Subscriber{ ");
		str.append("id=");
		str.append(id);
		str.append(", eventType=");
		str.append(eventType == null ? null : eventType.name());
		str.append(", eventFilter=");
		str.append(eventFilter);
		str.append(", endpoint=");
		str.append(endpoint);
		
		return str.toString();
	}

}
