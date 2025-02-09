
package com.adriandlph.auth;

import com.adriandlph.auth.data.entity.Event;
import com.adriandlph.auth.data.entity.EventToSend;
import com.adriandlph.auth.data.entity.EventToSendId;
import com.adriandlph.auth.data.entity.Subscriber;
import com.adriandlph.auth.data.model.EventModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author adriandlph
 * 
 * NOT SUPPORTED
 *
 */
@Controller
public class Events {
	@Autowired
	private EntityManager em;
	
	/**
	 * Process all events that has not been processed until now
	 */
	private void processEvents() {
		Query query;
		
		query = em.createQuery("SELECT e FROM Event e WHERE e.processed = :processed");
		query.setParameter("processed", false);
		
		for (Event event : (List<Event>)query.getResultList()) {
			processEvent(event);
		}
	}
	
	
	/**
	 * Process an event
	 * @param event  Event to be processed
	 */
	@Transactional
	private void processEvent(Event event) {		
		if (event == null) return;
		if (event.getEventType() == null) return;
		
		switch (event.getEventType()) {
			case USER_LOGOUT:
				// This event must be sent to others microservices
				sendEvent(event);
				break;
			default:
				System.err.println("Event type not recognited.");
				return;
		}
		
		event.setProcessed(Boolean.TRUE);
		em.flush();
	}
	
	/**
	 * Send event to its subscribers.
	 * 
	 * Subscribers must consider that the same event can be received more than one time.
	 * If an event is recived more than one time, it must be treated as if it has been received a single time.
	 * 
	 * Example: 
	 *  - Event e = Create user A 
	 *  Recv e -> create user
	 *  Recv e -> create user
	 * 
	 * This is not a good event... You must check if this event has been procesed.
	 * 
	 * 
	 * - Event e = Change username to "Alex" 
	 *  Recv e -> change username
	 *  Recv e -> change username
	 * 
	 * This is a good event as its result is always the same. Does not need to
	 * check if the event has been recv prevoiously.
	 * 
	 * 
	 * 
	 * @param event 
	 */
	private void sendEvent(Event event) {
		EventToSend eventToSend;
		Query query;
		
		query = em.createQuery("SELECT s FROM Subscriber s WHERE s.eventType = :eventType");
		query.setParameter("eventType", event.getEventType());
		
		for (Subscriber sub : (List<Subscriber>)query.getResultList()) {
			if (!checkEventFilter(event, sub.getEventFilter())) continue;
			
			// 
			eventToSend = new EventToSend();
			eventToSend.setId(new EventToSendId(event.getId(), sub.getId()));
			eventToSend.setEvent(event);
			eventToSend.setSubscriber(sub);
			em.persist(eventToSend);
			em.flush();
		}		
	}
	
	/**
	 * 
	 * @param event
	 * @param filter
	 * @return 
	 */
	private boolean checkEventFilter(Event event, String filter) {
		
		if (event == null) return false;
		if (event.getEventType() == null) return false;
		
		switch (event.getEventType()) {
			// No filter defined
			case USER_LOGOUT:
				return true;
			default:
				break;
		}
		
		return false;
	}
	
	private void sendEventToEndpoint(Event event, String endpoint) {
		EventToSend eventToSend;
		EventModel eventModel;
		
		eventModel = event2eventModel(event);
		
		
		// get public key
		// 1º GET publicKey
		// 2º Send event
		// 3º Set as sent to this sub
		// POST endpoint
		// body = encrypt(encrypt(model, myPrivateKey), endpointPublicKey);
		
	}
	
	private EventModel event2eventModel(Event event) {
		EventModel model;
		
		model = new EventModel();
		model.setId(event.getId());
		model.setEventType(event.getEventType());
		model.setArguments(event.getArguments());
		model.setGeneratedAt(event.getGeneratedAt());
		
		return model;
	}
}
