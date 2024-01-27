package app.repos;

import app.models.Customer.Customer;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StatisticsRepo {
    private HashMap<String, Integer> notificationTemplateCount = new HashMap<>();
    private HashMap<Customer, Integer> customerNotificationCount = new HashMap<>();

    public StatisticsRepo() {
    }

    public HashMap<String, Integer> getNotificationTemplateCount() {
        return notificationTemplateCount;
    }

    public HashMap<Customer, Integer> getCustomerNotificationCount() {
        return customerNotificationCount;
    }
}
