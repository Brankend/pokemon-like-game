package oopproject;

public class healthPot {

    private int healingPower;

    public int getHealingPower() {
        return healingPower;
    }

    public void setHealingPower(int healingPower) {
        this.healingPower = healingPower;
    }

    public healthPot(healthPot p) {
        this.healingPower = p.healingPower;
    }

    public healthPot() {
        this.healingPower = 30;
    }

    public healthPot(int healingPower) {
        this.healingPower = healingPower;
    }

    @Override
    public String toString() {
        return "healingPower=" + healingPower + '}';
    }

}
