
package com.adriandlph.auth.data.entity;

import com.adriandlph.auth.data.enumeration.EventType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author adriandlph
 *
 */
@Entity
@Table(name = "event")
public class Event {
	
	// Primary Keys
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	// Columns
	@Column(name = "event_type")
	@Enumerated(EnumType.ORDINAL)
	private EventType eventType;
	@Column(name = "arguments")
	private String arguments;
	@Column(name = "generated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar generatedAt;
	@Column(name = "processed")
	private Boolean processed; // All generated events must be processed
	// Foreign keys

	public Event() {
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

	public Boolean getProcessed() {
		return processed;
	}
	
	public boolean isProcessed() {
		if (processed == null) return false;
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 47 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		if (!(obj instanceof Event other)) return false;
		
		if (!Objects.equals(this.id, other.id)) return false;
		if (!Objects.equals(this.eventType, other.eventType)) return false;
		if (!Objects.equals(this.arguments, other.arguments)) return false;
		return Objects.equals(this.generatedAt, other.generatedAt);
	}

	@Override
	public String toString() {
		StringBuffer str;
		
		str = new StringBuffer();
		str.append("Event { ");
		str.append("id=");
		str.append(id);
		str.append(", eventType=");
		str.append(eventType == null ? null : eventType.name());
		str.append(", arguments=");
		str.append(arguments);
		str.append(", generatedAt=");
		str.append(generatedAt == null ? null : generatedAt.getTime());  // Date string format is more pretty than calendar one
		str.append(", processed=");
		str.append(processed);
		str.append(" }");
		
		return str.toString();
	}
	
}
