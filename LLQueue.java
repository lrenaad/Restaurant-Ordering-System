package data_structure_project_;

public class Customer {

    private int totalPoints;

    public Customer() {
        this(0, "", "", 0);
    }

    public Customer(int number, String name, String phoneNo, int totalPoints) {

        setTotalPoints(totalPoints);
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return String.format("%s, Total Points: %d", super.toString(), getTotalPoints());
    }
}
