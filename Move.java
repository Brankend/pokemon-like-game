package oopproject;

import java.util.Random;

public class Move {
    private String name;
    private int damage;
    private int cd;
    private boolean element;
    private int cr;
    private boolean available;
    private int cooldown;//Determines when the move was last used to calculate cooldown duration.

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCr() {
        return cr;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public boolean isElement() {
        return element;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setElement(boolean element) {
        this.element = element;
    }

    public Move(String name, int damage, int cd, boolean element, int cr) {
        this.name = name;
        this.damage = damage;
        this.cd = cd;
        this.element = element;
        this.cr = cr;
        cooldown = cd + 1;
    }

    public Move(Move m) {
        name = m.name;
        damage = m.damage;
        cd = m.cd;
        element = m.element;
        cr = m.cr;
        cooldown = cd + 1;
    }

    public int critChanceCalc() {
        Random r = new Random();
        if (r.nextInt(101) <= cr) {
            System.out.println("CRITICAL HIT!!!");
            return -2;
        }
        System.out.println("Normal Hit");
        return -1;
    }

    public int elementCheack(Monster defending, Monster attacking) {
        if ((element == true && "water".equals(defending.getElement()) && "fire".equals(attacking.getElement())) || (element == true && "earth".equals(defending.getElement()) && "water".equals(attacking.getElement())) || (element == true && "wind".equals(defending.getElement()) && "earth".equals(attacking.getElement())) || (element == true && "fire".equals(defending.getElement()) && "wind".equals(attacking.getElement()))) {
            return 2;
        }
        return 1;
    }

    @Override
    public String toString() {
        return (name + " damage: " + damage + " cd: " + cd + " Elemental: " + element + "\n");
    }
}
