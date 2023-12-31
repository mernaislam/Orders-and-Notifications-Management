package app.repos;

import app.models.Customer.Customer;

import java.util.HashMap;

public class StatisticsRepo {
    private HashMap<String, Integer> notificationTemplateCount = new HashMap<>();
    private HashMap<String, Integer> channelCount = new HashMap<>();
    private HashMap<Customer, Integer> customerNotificationCount = new HashMap<>();

    public HashMap<String, Integer> getNotificationTemplateCount() {
        return notificationTemplateCount;
    }

    public HashMap<String, Integer> getChannelCount() {
        return channelCount;
    }

    public HashMap<Customer, Integer> getCustomerNotificationCount() {
        return customerNotificationCount;
    }
}
