package app.controllers;

import app.models.Notification.NotificationTemplate;
import app.security.GlobalException;
import app.service.NotificationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@RestController
@RequestMapping(path="/api")
public class NotificationController {

    private final NotificationTemplateService templateService;
    private GlobalExceptionController exceptionController = new GlobalExceptionController();

    @Autowired
    public NotificationController(NotificationTemplateService templateService) {
        this.templateService = templateService;
    }

    // return notifications queue - list of the current content of the queue [no authorization]
    @GetMapping (path="/getNotifications")
    public Object getNotifications() {
        GlobalException exception;
        if(templateService.getNotificationTemplates().isEmpty()){
            exception = new GlobalException("No notifications sent yet!", HttpStatus.OK);
            return exceptionController.GlobalException(exception);
        }
        return templateService.getNotificationTemplates();
    }

    // return  most notified template - statistics
    @GetMapping (path="/getTemplateStatistics")
    public String getTemplateStatistics() {
        return templateService.getTemplateStatistics();
    }

    // return most notified channel - statistics
    @GetMapping (path="/getChannelStatistics")
    public String getChannelStatistics() {
        return templateService.getChannelStatistics();
    }
}
