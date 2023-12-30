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
    // return notifications queue
    @GetMapping (path="/getNotifications")
    public Queue<NotificationTemplate> getNotifications() {
        return templateService.getNotificationTemplates();
    }
    // return template statistics
    @GetMapping (path="/getTemplateStatistics")
    public String getTemplateStatistics() {
        return templateService.getTemplateStatistics();
    }
    // return channel statistics
    @GetMapping (path="/getChannelStatistics")
    public String getStatistics() {
        return templateService.getChannelStatistics();
    }
}
