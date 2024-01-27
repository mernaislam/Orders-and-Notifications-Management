package app.service;

import app.models.Customer.Customer;
import app.models.Notification.NotificationTemplate;
import app.repos.StatisticsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StatisticsService {

    private final StatisticsRepo statisticsRepo;

    @Autowired
    public StatisticsService(StatisticsRepo statisticsRepo) {
        this.statisticsRepo = statisticsRepo;
    }

    public void updateNotificationCount(NotificationTemplate notification){
        HashMap<String, Integer> notificationTemplateCount = statisticsRepo.getNotificationTemplateCount();
        String notificationTemplateName = notification.getClass().getSimpleName();
        if (notificationTemplateCount.containsKey(notificationTemplateName)){
            notificationTemplateCount.put(notificationTemplateName, notificationTemplateCount.get(notificationTemplateName) + 1);
        }
        else {
            notificationTemplateCount.put(notificationTemplateName, 1);
        }
    }
    public void updateCustomerNotificationCount(Customer customer){
        HashMap<Customer, Integer> customerNotificationCount = statisticsRepo.getCustomerNotificationCount();
        if (customerNotificationCount.containsKey(customer)){
            customerNotificationCount.put(customer, customerNotificationCount.get(customer) + 1);
        }
        else {
            customerNotificationCount.put(customer, 1);
        }
    }
    public void updateStatistics(NotificationTemplate notification, Customer customer){
        updateNotificationCount(notification);
        updateCustomerNotificationCount(customer);
    }

    public String getTemplateStatistics(){
        HashMap<String, Integer> notificationTemplateCount = statisticsRepo.getNotificationTemplateCount();
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

    public String getCustomerStatistics(){
        HashMap<Customer, Integer> customerNotificationCount = statisticsRepo.getCustomerNotificationCount();
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
            mostNotifiedCustomer += "\n    - Name: " + mostNotified.getName();
            mostNotifiedCustomer += "\n    - Username: " + mostNotified.getUsername();
            mostNotifiedCustomer += "\n    - Email: " + mostNotified.getEmail();
            mostNotifiedCustomer += "\n    - Phone Number: " + mostNotified.getPhoneNumber();
            mostNotifiedCustomer += "\nNumber of times notified: " + mx;
        }
        return mostNotifiedCustomer;
    }
}
