package app.service;

import app.models.Customer.Customer;
import app.models.Notification.NotificationChannel;
import app.models.Notification.NotificationTemplate;

import java.util.HashMap;

public class StatisticsService {
    private HashMap<String, Integer> notificationTemplateCount = new HashMap<>();
    private HashMap<String, Integer> channelCount = new HashMap<>();
    private HashMap<Customer, Integer> customerNotificationCount = new HashMap<>();

    public void updateNotificationCount(NotificationTemplate notification){
        String notificationTemplateName = notification.getClass().getSimpleName();
        if (notificationTemplateCount.containsKey(notificationTemplateName)){
            notificationTemplateCount.put(notificationTemplateName, notificationTemplateCount.get(notificationTemplateName) + 1);
        }
        else {
            notificationTemplateCount.put(notificationTemplateName, 1);
        }
    }
    public void updateChannelCount(NotificationChannel channel){
        String channelName = channel.getClass().getSimpleName();
        if (channelCount.containsKey(channelName)){
            channelCount.put(channelName, channelCount.get(channelName) + 1);
        }
        else {
            channelCount.put(channelName, 1);
        }
    }
    public void updateCustomerNotificationCount(Customer customer){
        if (customerNotificationCount.containsKey(customer)){
            customerNotificationCount.put(customer, customerNotificationCount.get(customer) + 1);
        }
        else {
            customerNotificationCount.put(customer, 1);
        }
    }
    public String getTemplateStatistics(){
        int mx = 0;
        String mostNotifiedTemplate = "";
        String mostNotified = null;
        for (String key: notificationTemplateCount.keySet()) {
            if (notificationTemplateCount.get(key) > mx) {
                mx = notificationTemplateCount.get(key);
                mostNotified = key;
            }
        }
        if (mostNotified != null) {
            mostNotifiedTemplate += "Most Notified Notification Template: " + mostNotified;
            mostNotifiedTemplate += "\nNumber of times notified: " + mx;
        }
        return mostNotifiedTemplate;
    }

    public String getChannelStatistics(){
        int mx = 0;
        String mostNotifiedChannel = "";
        String mostNotified = null;
        for (String key: channelCount.keySet()) {
            if (channelCount.get(key) > mx) {
                mx = channelCount.get(key);
                mostNotified = key;
            }
        }
        if (mostNotified != null) {
            mostNotifiedChannel += "Most Notified Notification Channel: " + mostNotified;
            mostNotifiedChannel += "\nNumber of times notified: " + mx;
        }
        return mostNotifiedChannel;
    }
    public String getCustomerStatistics(){
        int mx = 0;
        String mostNotifiedCustomer = "";
        Customer mostNotified = null;
        for (Customer key: customerNotificationCount.keySet()) {
            if (customerNotificationCount.get(key) > mx) {
                mx = customerNotificationCount.get(key);
                mostNotified = key;
            }
        }
        if (mostNotified != null) {
            mostNotifiedCustomer += "Most Notified Customer: ";
            mostNotifiedCustomer += "\n Name: " + mostNotified.getName();
            mostNotifiedCustomer += "\n Username: " + mostNotified.getUsername();
            mostNotifiedCustomer += "\n Email: " + mostNotified.getEmail();
            mostNotifiedCustomer += "\n Phone Number: " + mostNotified.getPhoneNumber();
            mostNotifiedCustomer += "\nNumber of times notified: " + mx;
        }
        return mostNotifiedCustomer;
    }
}
