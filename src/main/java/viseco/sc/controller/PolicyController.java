package viseco.sc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class PolicyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyController.class);

    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public PolicyController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }



    @RequestMapping(value="/mano/{data}", method = RequestMethod.GET)
    public ResponseEntity<String> mano(HttpServletRequest request, @PathVariable("data")  String data) {
        LOGGER.info("Received request from " + request.getRemoteAddr());
        messagingTemplate.convertAndSend("/data/notification", data);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }}