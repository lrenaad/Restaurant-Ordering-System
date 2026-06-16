package data_structure_project_;

public class LuxuryRestaurant extends RestaurantModel {

    private String cuisine;

    public LuxuryRestaurant(String name, String location, String phoneNumber, String cuisine) {
        super(name, location, phoneNumber);
        this.cuisine = cuisine;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @Override
    public String getRestaurantType() {
        return "Fine Dining";
    }

    @Override
    public void ShowRestaurantInfo() {
        super.ShowRestaurantInfo();
        System.out.println("Cuisine: " + cuisine);
        System.out.println("-------------------------------------");
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Cuisine: " + cuisine + "\n"
                + "-------------------------------------";
    }
}
