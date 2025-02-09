
package com.adriandlph.auth.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "event_to_send")
public class EventToSend {
	
	@EmbeddedId
	private EventToSendId id;
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Event event;
	@JoinColumn(name = "subscriber_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Subscriber subscriber;
	@Column(name = "sent_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar sentDate = null;

	public EventToSend() {
	}

	public EventToSendId getId() {
		return id;
	}

	public void setId(EventToSendId id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public Calendar getSentDate() {
		return sentDate;
	}

	public void setSentDate(Calendar sentDate) {
		this.sentDate = sentDate;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 23 * hash + Objects.hashCode(this.id);
		hash = 23 * hash + Objects.hashCode(this.event);
		hash = 23 * hash + Objects.hashCode(this.subscriber);
		hash = 23 * hash + Objects.hashCode(this.sentDate);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof EventToSend other)) return false;
		
		if (!Objects.equals(this.id, other.id)) return false;
		if (!Objects.equals(this.event, other.event)) return false;
		if (!Objects.equals(this.subscriber, other.subscriber)) return false;
		return Objects.equals(this.sentDate, other.sentDate);
	}

	@Override
	public String toString() {
		StringBuffer str;
		
		str = new StringBuffer("EventToSend { ");
		str.append("id=");
		str.append(Objects.toString(id));
		str.append(", sentDate=");
		str.append(sentDate == null ? null : sentDate.getTime()); // Date class has a prettier string format
		str.append(" }");
		
		return str.toString();
	}
	
}
