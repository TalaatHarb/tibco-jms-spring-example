package net.talaatharb.jms.tibco.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jms.JmsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.talaatharb.jms.tibco.service.JmsSenderService;

@RestController
public class SampleController {

	@Autowired
	private JmsSenderService jmsService;

	@PostMapping(path = "/api/message", produces = MediaType.APPLICATION_JSON_VALUE)
	public String send(@RequestBody String message) {
		try {
			jmsService.send(message);
			return String.format("{\"message\":\"%s\"}", message);
		} catch (JmsException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

}
