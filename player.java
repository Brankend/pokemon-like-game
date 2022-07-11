package oopproject;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Random;

public class player extends character implements adding {

    ArrayList<Monster> monsters = new ArrayList<>();
    ArrayList<healthPot> healthPotions = new ArrayList<>();
    public int lastMonsterUsedIndex;

    @Override
    public void addHealthPotion(healthPot pot) {
        healthPotions.add(pot);
    }

    public int getLastMonsterUsedIndex() {
        return lastMonsterUsedIndex;
    }

    public void setLastMonsterUsedIndex(int lastMonsterUsedIndex) {
        this.lastMonsterUsedIndex = lastMonsterUsedIndex;
    }

    public player(String name) {
        super.setName(name);
        super.setHealth(100);

    }

    public void levelUp() {
        super.setLevel(super.getLevel() + 1);
    }

    @Override
    public void addMonster(Monster m) {
        monsters.add(m);
    }

    public String printMonstersList() {
        String monstersString = "";
        for (int i = 0; i < monsters.size(); i++) {
            monstersString += ((i + 1) + ": ");
            monstersString += monsters.get(i).toString();
        }
        return monstersString;
    }

    @Override
    public String toString() {
        return ("Player name:" + super.getName() + " Player health: " + super.getHealth() + " Monsters aqquired:\n" + printMonstersList());
    }

    public void attack(Monster enemy, int ally,World w) {
        boolean choosingMove = true;
        int choiceMonster = ally;
        Scanner s = new Scanner(System.in);
        System.out.println("Choose a move\n" + monsters.get(choiceMonster).printMoves());
        for(int i = 0; i < monsters.size();i++){
            for(int j = 0; j < monsters.get(i).moves.size();j++){
                monsters.get(i).moves.get(j).setCooldown(monsters.get(i).moves.get(j).getCooldown()+1);
            }
        }
        while (choosingMove) {
            int choiceMove = s.nextInt();
            if (choiceMove <= monsters.get(choiceMonster).moves.size()) {
                choiceMove--;
                if(monsters.get(choiceMonster).moves.get(choiceMove).getCooldown()>monsters.get(choiceMonster).moves.get(choiceMove).getCd()){
                choosingMove = false;
                
                enemy.calcHealth(monsters.get(choiceMonster).moves.get(choiceMove).getDamage() * monsters.get(choiceMonster).moves.get(choiceMove).critChanceCalc() * monsters.get(choiceMonster).moves.get(choiceMove).elementCheack(enemy, monsters.get(choiceMonster)));

                System.out.println("Enemy Health:" + enemy.getHealth());
                monsters.get(choiceMonster).moves.get(choiceMove).setCooldown(0);
                }
                else{
                    System.out.println("This move is under cooldown");
                }
            } else {
                System.out.println("Invalid Choice! Please choose again carefully.");
            }
        }
        if (enemy.getHealth() <= 0) {
            System.out.println("Opponent Defeated");
        }
    }

    public boolean captureMonster(Monster enemy) {
        int chance = 100 - enemy.getHealth();
        if (chance == 0) {
            return false;
        }
        Random r = new Random();
        int num = r.nextInt(100);
        if (num <= chance) {
            return true;
        }
        return false;

    }

    public String printPots() {
        String potions = "";
        for (int i = 0; i < healthPotions.size(); i++) {
            potions += ((i + 1) + ": ");
            potions += (healthPotions.get(i).toString() + "\n");
        }
        return potions;
    }

    public boolean playTurn(Monster enemy,World w) {
        boolean choosing = true;
        Scanner s = new Scanner(System.in);
        System.out.println("Choose your next move\n1:Attack\n2:Capture the monster\n3:Use health potion");
        int choice = s.nextInt();
        while (choosing) {
            if (choice < 1 || choice > 3) {
                System.out.println("Invalid Chouce. Please choose a value from 1 to 3");
            } else {
                choosing = false;
            }
        }
        switch (choice) {
            case 1:
                boolean monsterChosen = false;
                while (!monsterChosen) {
                    System.out.println("Choose your monster (Choose carefully changing the monster will cost you one turn.\n)" + printMonstersList());
                    int monsterChoice = s.nextInt();
                    if (monsterChoice <= monsters.size()) {                     
                        monsterChoice--;
                        if(monsterChoice != lastMonsterUsedIndex  && w.getCurrentTurn()!= 0){
                            lastMonsterUsedIndex = monsterChoice;
                        }
                        else{
                            lastMonsterUsedIndex = monsterChoice;
                            attack(enemy, monsterChoice,w);
                        }
                        monsterChosen = true;
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
                w.setCurrentTurn(w.getCurrentTurn()+1);
                return true;
            case 2:
                if (captureMonster(enemy)) {
                    enemy.setHealth(100);
                    monsters.add(new Monster(enemy));
                    System.out.println("Monster captured and added to inventory" + printMonstersList());
                    w.setCurrentTurn(0);
                    return false;
                } else {
                    System.out.println("Monster couldn't be captured");
                }
                w.setCurrentTurn(w.getCurrentTurn()+1);
                return true;
            case 3:
                boolean correct = false;
                while (!correct) {
                    System.out.println("Choose which monster to heal\n" + printMonstersList());
                
                int choice2 = s.nextInt();
                if (choice2 <= monsters.size() + 1) {
                    choice2--;
                    System.out.println("Choose which health potion to use\n" + printPots());
                    int potChoice = s.nextInt();
                    if (potChoice <= healthPotions.size() + 1) {
                        potChoice--;
                        monsters.get(choice2).calcHealth(healthPotions.get(potChoice).getHealingPower());
                        healthPotions.remove(potChoice);
                        correct = true;
                    } else {
                        System.out.println("Invalid Choice");
                    }
                } else {
                    System.out.println("Invalid Choice");
                }}
                w.setCurrentTurn(w.getCurrentTurn()+1);
                return true;

        }
        w.setCurrentTurn(w.getCurrentTurn()+1);
        return true;
    }

    public void move() {
        Scanner s = new Scanner(System.in);
        boolean choosing = true;
        System.out.println("Choose which direction to go to\n1:North\n2:South\n3:East\n4:West");
         int choice = s.nextInt();
           
        
    }
}
