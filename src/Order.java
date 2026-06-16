package data_structure_project_;

public class Order {

    private int orderID;
    private String customerName;
    private SinglyLinkedList<MenuItem> orderItems;
    private double totalPrice;
    private String status;
    private RestaurantModel restaurant;
    private static int count = 100;

    public Order(String customerName, SinglyLinkedList<MenuItem> orderItems, RestaurantModel restaurant) {
        this.orderID = ++count;
        this.customerName = customerName;
        this.orderItems = orderItems;
        this.totalPrice = calculateTotal();
        this.status = "Pending";
        this.restaurant = restaurant;
    }

    // Calculate total price from menu items 
    private double calculateTotal() {
        double total = 0.0;
        SinglyLinkedList.Node current = orderItems.head;
        while (current != null) {
            MenuItem item = (MenuItem) current.getElement();
            total += item.getPrice();
            current = current.getNext();
        }
        return total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public SinglyLinkedList<MenuItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(SinglyLinkedList<MenuItem> orderItems) {
        this.orderItems = orderItems;
        this.totalPrice = calculateTotal();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public RestaurantModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantModel restaurant) {
        this.restaurant = restaurant;
    }

    public void markAsDelivered() {
        this.status = "Delivered";
        System.out.println("Order #" + orderID + " has been delivered!");
    }

    @Override
    public String toString() {
        String result = " -- Order Information --\n"
                + "Order ID: " + orderID + "\n"
                + "Customer: " + customerName + "\n"
                + "Restaurant: " + restaurant.getName() + " (" + restaurant.getRestaurantType() + ")\n"
                + "Items Ordered:\n"
                + orderItems.getItemsAsString()
                + "Total Price: SR " + totalPrice + "\n"
                + "Status: " + status + "\n"
                + "-------------------------------------";

        return result;
    }
}
