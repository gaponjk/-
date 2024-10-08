public class Machine {
    private  int cost;
    private int power;

    public Machine(int cost, int power) {
        this.cost = cost;
        this.power = power;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return  cost + " " + power;
    }
}
