# Orders-and-Notifications-Management
A Java implementation of a software that allows making online purchase for orders, as well as managing message notifications based on various actions taken during the various orders’ purchase relevant operations.

# Main Features
- Allows users to register and login to the system.
- Security is implemented using JWT for authorization purposes.
- Allows to display all the products in the system.
- Allows placing & shipping Simple Orders, which are orders that contain several products, as well as Compound Orders, which are orders that contain multiple Simple Orders.
- Allows the user to cancel an order, whether cancel its shipment or cancel its placement.
- Allows the user to view all the orders that was made or a specific order by its ID.
- Automatically notifications are sent to the user when the order is placed, when the order is cancelled, and when the order is shipped.
- Notifications are generated in different languages, depending on the customer's preference.
- Notifications are sent via Email channel or Email & SMS channels, depending on the customer's preference.
- Allows to view all the notifications in the notifications queue, as well as view statistics about the notifications.

# Design Patterns used
### Strategy Pattern:
- Used in the Language model as the Notification Template (context) uses Strategy Pattern to translate text from the default language to any language the customer chooses.
### Template Method Pattern:
- Used in the Process Order model as we were able to process orders in a different manner based on the order type whether it is Simple or Compound order.
- Used in the Notification Template model as we were able to create the notification templates efficiently since we reused several steps in every template, only changing the implementation of the ones that were different.
### Composite Pattern:
- Used in the Order model as the recursive tree structure allowed us to navigate the hierarchy as if dealing with individual objects, regardless of whether they were simple or compound orders.
### Factory Method Pattern:
- Used in the Notification Template Factory class as we were able to separate the creation of the notification templates and encapsulate it in a separate class.
### Decorator Pattern:
- Used in the Channel model as we were able to extend the functionality of sending notifications through several channels.

# Technologies used
- [Spring Boot](https://spring.io/): A Java-based open-source framework that simplifies the development of Spring-based applications.
