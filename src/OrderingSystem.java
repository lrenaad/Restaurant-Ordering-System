package data_structure_project_;

import java.util.Scanner;

public class OrderingSystem {

    private String name;
    private SinglyLinkedList<Order> OrderList;
    private LLQueue<Order> deliveryLine;
    private SinglyLinkedList<RestaurantModel> restaurants;
    private Scanner input;

    public OrderingSystem(String name) {
        this.name = name;
        OrderList = new SinglyLinkedList<>();
        deliveryLine = new LLQueue<>();
        restaurants = new SinglyLinkedList<>();
        input = new Scanner(System.in);
        fillData();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void menu() {
        System.out.print("- - - - - - - - - - - - - - - - - - - - - \n"
                + "  Restaurant Ordering & Delivery System\n"
                + "- - - - - - - - - - - - - - - - - - - - - \n"
                + "1)View Restaurant Menus\n"
                + "2)Make New Order\n"
                + "3)Add Order to Delivery Queue\n"
                + "4)Remove Item\n"
                + "5)Delete Order\n"
                + "6)Show All Orders \n"
                + "7)Calculate Total Revenue \n"
                + "8)Search Order by ID  \n"
                + "9)Deliver Next Order\n"
                + "10)Deliver Line\n"
                + "11)Exit\n "
                + "Enter your choice: ");

    }

    public void ViewMenus() {
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }

        System.out.println("--Acvtive Restaurants--");
        restaurants.printList();

        System.out.print("\nEnter restaurant ID to view restaurant menu ( 0 to cancel): ");
        int restaurantID = input.nextInt();

        if (restaurantID == 0) {
            return;
        }

        RestaurantModel restaurant = findRestaurantByID(restaurantID);
        if (restaurant == null) {
            System.out.println("Invalid restaurant ID.");
        } else {
            restaurant.displayMenu();
        }
    }

    public void newOrder() {
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }

        input.nextLine();
        System.out.println("Available Restaurants:");
        restaurants.printList();

        System.out.print("\nEnter restaurant ID: ");
        int restaurantID = input.nextInt();
        input.nextLine();

        RestaurantModel restaurant = findRestaurantByID(restaurantID);
        if (restaurant == null) {
            System.out.println("Invalid restaurant ID.");
            return;
        }

        System.out.println("\n");
        restaurant.displayMenu();

        System.out.print("\nEnter customer name: ");
        String customerName = input.nextLine();

        SinglyLinkedList<MenuItem> selectedItems = new SinglyLinkedList<>();

        System.out.print("How many items would you like to order? ");
        int numItems = input.nextInt();

        for (int i = 0; i < numItems; i++) {
            System.out.print("Enter item ID #" + (i + 1) + ": ");
            int itemID = input.nextInt();

            MenuItem item = findMenuItemByID(restaurant.getMenu(), itemID);
            if (item != null) {
                selectedItems.addLast(item);
                System.out.println("Added: " + item.getItemName() + " (SR " + item.getPrice() + ")");
            } else {
                System.out.println("Invalid item ID.");
                i--;
            }
        }

        if (selectedItems.isEmpty()) {
            System.out.println("No items selected. Order cancelled.");
            return;
        }

        Order order = new Order(customerName, selectedItems, restaurant);
        OrderList.addLast(order);

        System.out.println("\n-----------------------------------");
        System.out.println("Order placed successfully!");
        System.out.println("-----------------------------------");
        System.out.println(order);
    }

    // Helper method to find menu item by ID in a menu 
    private MenuItem findMenuItemByID(SinglyLinkedList<MenuItem> menu, int itemID) {
        SinglyLinkedList.Node current = menu.head;
        while (current != null) {
            MenuItem item = (MenuItem) current.getElement();
            if (item.getItemID() == itemID) {
                return item;
            }
            current = current.getNext();
        }
        return null;
    }

    // Helper method to find restaurant by ID 
    private RestaurantModel findRestaurantByID(int restaurantID) {
        SinglyLinkedList.Node current = restaurants.head;
        while (current != null) {
            RestaurantModel r = (RestaurantModel) current.getElement();
            if (r.getRestaurantID() == restaurantID) {
                return r;
            }
            current = current.getNext();
        }
        return null;
    }

    public void viewAllOrders() {
        if (OrderList.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("------- All Orders -------");
            deliveryLine.Display();
        }
    }

    public void searchOrderByID() {
        System.out.print("Enter order ID to search: ");
        int orderID = input.nextInt();

        Order order = OrderList.findOrder(orderID);

        if (order == null) {
            System.out.println("Order not found.");
        } else {
            System.out.println("Order found: ");
            System.out.println(order);
        }
    }

    public void calculateRevenue() {
        double totalRevenue = OrderList.calculateTotalCost();
        System.out.println("Total Revenue: " + totalRevenue + "SR.");
    }

    public void deleteOrder() {
        deliveryLine.deleteOrder();
    }

    public void addToDeliveryQueue() {
        System.out.print("Enter order ID to add to delivery queue: ");
        int orderID = input.nextInt();

        Order order = OrderList.findOrder(orderID);

        if (order == null) {
            System.out.println("Order not found in the order list.");
        } else if (!order.getStatus().equalsIgnoreCase("Pending")) {
            System.out.println("Only pending orders can be added to delivery queue.");
        } else {
            deliveryLine.enqueue(order);
            System.out.println("Order added to delivery queue!");
            System.out.println(order);
        }
    }

    public void deliverNext() {
        deliveryLine.deliverNextOrder();
    }

    public void viewDeliveryLine() {
        deliveryLine.deliveryLine();
    }

    public void removeItem() {

        RestaurantModel rest = null;
        int restID;

        while (true) {
            System.out.print("Enter Restaurant ID: ");
            restID = input.nextInt();

            rest = findRestaurantByID(restID);

            if (rest == null) {
                System.out.println("Invalid restaurant ID. Please try again.");
            } else {
                break;
            }
        }
        rest.displayMenu();

        MenuItem itemToRemove = null;
        int itemID;

        while (true) {
            System.out.print("Enter Item ID to remove: ");
            itemID = input.nextInt();

            SinglyLinkedList.Node current = rest.getMenu().head;

            while (current != null) {
                MenuItem item = (MenuItem) current.getElement();
                if (item.getItemID() == itemID) {
                    itemToRemove = item;
                    break;
                }
                current = current.getNext();
            }

            if (itemToRemove == null) {
                System.out.println("Invalid item ID. Please try again.");
            } else {
                break;
            }
        }
        rest.getMenu().removeItem(itemToRemove);

        System.out.println("Item has been successfully deleted from the menu.");
    }

    // Fill sample data 
    public void fillData() {

        LuxuryRestaurant res1 = new LuxuryRestaurant("JAMLE'S", "The VIEW MALL, RIYADH", "0568290721", "ITALINA");
        LuxuryRestaurant res2 = new LuxuryRestaurant("MEMOS", "AL RABIE, Riyadh", "0509409751", "ITALINA");
        FastMealRestaurant res3 = new FastMealRestaurant("WBJ", "AL MALQA, Riyadh", "0538882470", "Yes", 45);
        FastMealRestaurant res4 = new FastMealRestaurant("SALT", "HITTIN, RIYADH", "920024788", "NO", 45);

        res1.getMenu().addLast(new MenuItem(001, "Margherita Pizza", 95.0, "PIZZE"));
        res1.getMenu().addLast(new MenuItem(002, "Truffle SHUFFLE", 84.0, "PIZEE"));
        res1.getMenu().addLast(new MenuItem(003, "PENNE ARRABBIATA", 59.0, "PASTA FRESCA"));
        res1.getMenu().addLast(new MenuItem(004, "PENNE ALFREDO", 59.0, "PASTA FRESCA"));
        res1.getMenu().addLast(new MenuItem(005, "SOFT DRINK", 14.0, "DRINKS"));

        res2.getMenu().addLast(new MenuItem(010, "MEAT PIZZA", 98.0, "PIZZA"));
        res2.getMenu().addLast(new MenuItem(011, "PEPPERONI PIZZA", 63.0, "PIZZA"));
        res2.getMenu().addLast(new MenuItem(012, "FRIED OLIVES", 44.0, "APPETIZERS"));
        res2.getMenu().addLast(new MenuItem(013, "CLASSIC ITALIAN FOCACCIA", 48.0, "Appetizer"));
        res2.getMenu().addLast(new MenuItem(014, "GINGER ALE", 24.0, "COLD BEVERAGES"));

        res3.getMenu().addLast(new MenuItem(020, "WBJ BURGER WITH FRIES", 33.0, "BURGERS"));
        res3.getMenu().addLast(new MenuItem(021, "J BURGER WITH FRIES", 35.0, "BURGERS"));
        res3.getMenu().addLast(new MenuItem(022, "FRIES", 7.0, "SIDES"));
        res3.getMenu().addLast(new MenuItem(023, "WBJ SAUCE", 3.0, "SAUCES"));
        res3.getMenu().addLast(new MenuItem(024, "PEPSI/7UP", 4.0, "DRINKS"));

        res4.getMenu().addLast(new MenuItem(030, "SALT BURGER", 39.0, "BURGERS"));
        res4.getMenu().addLast(new MenuItem(031, "CHICKEN HARRAG", 39.0, "BURGERS"));
        res4.getMenu().addLast(new MenuItem(032, "SALT FRIES", 21.0, "EXTRA & SIDES"));
        res4.getMenu().addLast(new MenuItem(033, "SOFT DRINK", 9.0, "DRINKS"));

        restaurants.addLast(res1);
        restaurants.addLast(res2);
        restaurants.addLast(res3);
        restaurants.addLast(res4);

        SinglyLinkedList<MenuItem> items1 = new SinglyLinkedList<>();
        items1.addLast(findMenuItemByID(res1.getMenu(), 001));
        items1.addLast(findMenuItemByID(res1.getMenu(), 004));
        items1.addLast(findMenuItemByID(res1.getMenu(), 005));
        Order o1 = new Order("Renad Mohammad", items1, res1);

        SinglyLinkedList<MenuItem> items2 = new SinglyLinkedList<>();
        items2.addLast(findMenuItemByID(res2.getMenu(), 010));
        items2.addLast(findMenuItemByID(res2.getMenu(), 012));
        items2.addLast(findMenuItemByID(res2.getMenu(), 013));
        items2.addLast(findMenuItemByID(res2.getMenu(), 014));
        Order o2 = new Order("Fatimah Talal", items2, res2);

        SinglyLinkedList<MenuItem> items3 = new SinglyLinkedList<>();
        items3.addLast(findMenuItemByID(res3.getMenu(), 020));
        items3.addLast(findMenuItemByID(res3.getMenu(), 024));
        Order o3 = new Order("Nourah Fahad", items3, res3);

        SinglyLinkedList<MenuItem> items4 = new SinglyLinkedList<>();
        items4.addLast(findMenuItemByID(res4.getMenu(), 030));
        items4.addLast(findMenuItemByID(res4.getMenu(), 032));
        items4.addLast(findMenuItemByID(res4.getMenu(), 033));
        Order o4 = new Order("Wateen Salman", items4, res4);

        SinglyLinkedList<MenuItem> items5 = new SinglyLinkedList<>();
        items5.addLast(findMenuItemByID(res3.getMenu(), 021));
        items5.addLast(findMenuItemByID(res3.getMenu(), 023));
        items5.addLast(findMenuItemByID(res3.getMenu(), 024));
        Order o5 = new Order("Sara Aziz", items5, res2);

        OrderList.addLast(o1);
        OrderList.addLast(o2);
        OrderList.addLast(o3);
        OrderList.addLast(o4);
        OrderList.addLast(o5);

        deliveryLine.enqueue(o1);
        deliveryLine.enqueue(o2);
        deliveryLine.enqueue(o3);
        deliveryLine.enqueue(o4);
        deliveryLine.enqueue(o5);

    }
}
