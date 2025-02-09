
package com.adriandlph.auth.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author adriandlph
 *
 */
@Embeddable
public class EventToSendId implements Serializable {
	@Column(name = "id_event_id")
	private Long eventId;
	@Column(name = "id_subscriber_id")
	private Long subscriberId;

	public EventToSendId() {
	}

	public EventToSendId(Long eventId, Long subscriberId) {
		this.eventId = eventId;
		this.subscriberId = subscriberId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 97 * hash + Objects.hashCode(this.eventId);
		hash = 97 * hash + Objects.hashCode(this.subscriberId);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		if (!(obj instanceof EventToSendId other)) return false;
		
		if (!Objects.equals(this.eventId, other.eventId)) return false;
		return Objects.equals(this.subscriberId, other.subscriberId);
	}
	
	

	@Override
	public String toString() {
		StringBuffer str;
		
		str = new StringBuffer("EventToSendId { ");
		str.append("eventId=");
		str.append(Objects.toString(eventId));
		str.append(", subscriberId=");
		str.append(Objects.toString(subscriberId));
		str.append("}");
		
		return str.toString();
	}
	
}
