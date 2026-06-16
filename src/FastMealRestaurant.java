package data_structure_project_;

public class FastMealRestaurant extends RestaurantModel {

    private String driveThruAvailable;
    private int avgDeliveryTime;

    public FastMealRestaurant(String name, String location, String phoneNumber, String driveThruAvailable, int avgDeliveryTime) {
        super(name, location, phoneNumber);
        this.driveThruAvailable = driveThruAvailable;
        this.avgDeliveryTime = avgDeliveryTime;
    }

    public String getDriveThruAvailable() {
        return driveThruAvailable;
    }

    public void setDriveThruAvailable(String driveThruAvailable) {
        this.driveThruAvailable = driveThruAvailable;
    }

    public int getAvgDeliveryTime() {
        return avgDeliveryTime;
    }

    public void setAvgDeliveryTime(int avgDeliveryTime) {
        this.avgDeliveryTime = avgDeliveryTime;
    }

    @Override
    public String getRestaurantType() {
        return "Fast Food";
    }

    @Override
    public void ShowRestaurantInfo() {
        super.ShowRestaurantInfo();
        System.out.println("Drive-Thru: " + driveThruAvailable);
        System.out.println("Avg Delivery Time: " + avgDeliveryTime + " minutes");
        System.out.println("-------------------------------------");
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Drive-Thru: " + driveThruAvailable + "\n"
                + "Avg Delivery Time: " + avgDeliveryTime + " minutes\n"
                + "-------------------------------------";
    }

}
