# Orders-and-Notifications-Management
A Java implementation of a software that allows making online purchase for orders, as well as managing message notifications based on various actions taken during the various orders’ purchase relevant operations.

# Main Features
- Allows users to register and login to the system.
- Allows to display all the products in the system.
- Allows making Simple Orders, which are orders that contain several products, as well as Compound Orders, which are orders that contain multiple Simple Orders.
- Allows the user to cancel an order, whether cancel its shipment or cancel its placement.
- Automatically notifications are sent to the user when the order is placed, when the order is cancelled, and when the order is shipped.
- Notifications are sent via SMS or Email, depending on the user's preference.
- Allows to view all notifications in the notifications queue, as well as view statistics about the notifications.

# Design Patterns used
### Strategy Pattern:
- Used in languages as the notification template (context) uses strategy pattern in languages to write text in any language the customer chooses.
- Used in notifications as the notification template service (context) uses strategy pattern to send notifications via SMS or Email or any other channel.
### Template Method Pattern:
- Used in the process order service as we were able to process orders in a different manner based on the order type whether it is simple or compound order.
- Used in the notification template service as we were able to create the notification templates efficiently since we reused several steps in every template, only changing the implementation of the ones that were different.
### Composite Pattern:
- Used in the order service as the recursive tree structure allowed us to navigate the hierarchy as if dealing with individual objects, regardless of whether they were simple or compound orders.

# Technologies used
- ```Spring Boot```: Java-based open-source framework that simplifies the development of Spring-based applications.
