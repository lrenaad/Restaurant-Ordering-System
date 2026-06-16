package data_structure_project_;

public abstract class RestaurantModel implements Restaurantable {

    private int restaurantID;
    private String name;
    private String location;
    private String phoneNumber;
    private SinglyLinkedList<MenuItem> menu;
    private static int count = 0;

    public RestaurantModel(String name, String location, String phoneNumber) {
        this.restaurantID = ++count;
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.menu = new SinglyLinkedList<>();
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static int getCount() {
        return count;
    }

   

    public SinglyLinkedList<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(SinglyLinkedList<MenuItem> menu) {
        this.menu = menu;
    }

    public void displayMenu() {
        if (menu.isEmpty()) {
            System.out.println("No menu items available.");
        } else {
            System.out.println("========================================");
            System.out.println("    " + name + " - MENU");
            System.out.println("    Type: " + getRestaurantType());
            System.out.println("========================================");
            menu.printList();
            System.out.println("========================================");
        }
    }

    @Override
    public void ShowRestaurantInfo() {
        System.out.println(" -- Restaurant Information --");
        System.out.println("Restaurant ID: " + restaurantID);
        System.out.println("Name: " + name);
        System.out.println("Type: " + getRestaurantType());
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phoneNumber);
    }

    @Override
    public abstract String getRestaurantType();

    @Override
    public String toString() {
        return " -- Restaurant Information --\n"
                + "Restaurant ID: " + restaurantID + "\n"
                + "Name: " + name + "\n"
                + "Type: " + getRestaurantType() + "\n"
                + "Location: " + location + "\n"
                + "Phone: " + phoneNumber + "\n"
                + "-------------------------------------";
    }
}
