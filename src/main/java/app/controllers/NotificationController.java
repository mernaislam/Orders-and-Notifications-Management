package app.controllers;

import app.models.Notification.NotificationTemplate;
import app.service.NotificationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@RestController
@RequestMapping(path="/app/notifications")
public class NotificationController {

    private final NotificationTemplateService templateService;
    @Autowired
    public NotificationController(NotificationTemplateService templateService) {
        this.templateService = templateService;
    }
    // return notifications queue - list of the current content of the queue
    @GetMapping (path="/getNotifications")
    public Queue<NotificationTemplate> getNotifications() {
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
