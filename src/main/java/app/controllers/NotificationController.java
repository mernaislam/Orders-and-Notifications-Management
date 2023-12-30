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
//    @PostMapping(path="/addNotification")
//    public void addNotification(@RequestBody NotificationSubject subject, @RequestBody Order order) {
//        templateService.generateNotification(subject, order);
//    }

    // return notifications queue
    @GetMapping (path="/getNotifications")
    public Queue<NotificationTemplate> getNotifications() {
        return templateService.getNotificationTemplates();
    }
    // return template statistics
    @GetMapping (path="/getNotificationsStatistics")
    public String getNotificationsStatistics() {
        return templateService.getTemplateStatistics();
    }
    // test
    @GetMapping (path="/getNotificationsSize")
    public Integer getNotificationsSize() {
        return templateService.getNotificationTemplates().size();
    }
}
