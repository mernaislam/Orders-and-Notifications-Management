package app.controllers;

import app.security.GlobalException;
import app.service.NotificationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class NotificationController {
    private final NotificationTemplateService templateService;
    private final GlobalExceptionController exceptionController = new GlobalExceptionController();

    @Autowired
    public NotificationController(NotificationTemplateService templateService) {
        this.templateService = templateService;
    }

    // return notifications queue - list of the current content of the queue [no authorization]
    @GetMapping (path="/getNotifications")
    public Object getNotifications() {
        GlobalException exception;
        if(templateService.getNotificationTemplates().isEmpty()){
            exception = new GlobalException("No notifications in the current notifications queue!", HttpStatus.OK);
            return exceptionController.GlobalException(exception);
        }
        return templateService.getNotificationTemplates();
    }
}
