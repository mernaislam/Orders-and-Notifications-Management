package app.models.Notification;

import app.models.Language.EnglishLanguage;
import app.models.Language.Language;
import app.models.Orders.Order;
import app.service.OrderService;

public class NotificationTemplateFactory {

    public NotificationTemplate createNotificationTemplate(NotificationSubject subject, Order order, OrderService orderService) {
        // should get the preferred language from the customer and modify in the customer management, then create the language accordingly
        // for now, assume English language is the preferred choice
        Language language = new EnglishLanguage();

        NotificationTemplate notification = null;
        switch (subject) {
            case ORDER_PLACEMENT -> notification = new OrderPlacementNotification(language, order, orderService);
            case ORDER_SHIPMENT -> notification = new OrderShipmentNotification(language, order, orderService);
            case PLACEMENT_CANCELLATION -> notification = new PlacementCancellationNotification(language, order, orderService);
            case SHIPMENT_CANCELLATION -> notification = new ShipmentCancellationNotification(language, order, orderService);
        };
        if (notification != null) {
            notification.createTemplate();
        }

        return notification;
    }
}
