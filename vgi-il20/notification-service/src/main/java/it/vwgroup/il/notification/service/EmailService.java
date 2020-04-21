package it.vwgroup.il.notification.service;

import javax.mail.MessagingException;

import it.vwgroup.il.notification.domain.NotificationType;
import it.vwgroup.il.notification.domain.Recipient;

import java.io.IOException;

public interface EmailService {

	void send(NotificationType type, Recipient recipient, String attachment) throws MessagingException, IOException;

}
