package oopproject;

import java.util.ArrayList;
import java.util.Random;

public class World implements adding {
    private static int currentTurn;
    ArrayList<Monster> monsters = new ArrayList<>();
    ArrayList<healthPot> healthPotions = new ArrayList<>();

    public static int getCurrentTurn() {
        return currentTurn;
    }
    
    public static void setCurrentTurn(int currentTurn) {
        World.currentTurn = currentTurn;
    }

    @Override
    public void addMonster(Monster m) {
        monsters.add(new Monster(m));
    }

    @Override
    public void addHealthPotion(healthPot p) {
        healthPotions.add(new healthPot(p));
    }

    public Monster spawnObject(player p) {
        Random random = new Random();
        int r = random.nextInt(100);
        if (r < 50) {
            System.out.println("HealthPot world list size " + healthPotions.size());
            spawnHealthPot();
            spawnHealthPot();
            spawnHealthPot();
            spawnHealthPot();
            r = random.nextInt(healthPotions.size());
            System.out.println("HealthPot Random numb " + r);
            System.out.println("A new health potion was added to your inventory!" + healthPotions.get(r).toString());
            p.addHealthPotion(healthPotions.get(r));
            healthPotions.remove(r);
            
            return null;
        } else {
            System.out.println("World Monster list size " + monsters.size());
            r = random.nextInt(monsters.size());
            System.out.println("Monster Random numb " + r);
            return monsters.get(r);
        }
    }

    
    public void spawnHealthPot() {
        Random random = new Random();
        int r = random.nextInt(100);
        if (r < 50) {
            r = random.nextInt(100);
            if (r <= 49) {
                healthPotions.add(new healthPot());
            } else if (r <= 90) {
                healthPotions.add(new healthPot(50));
            } else {
                healthPotions.add(new healthPot(100));
            }
        }
    }
}
