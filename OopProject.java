package oopproject;


import java.util.Scanner;

public class OopProject {

    public static void main(String[] args) {
        int choice;
        Monster spawnedMonster;
        World w = new World();
        boolean correct = false;
        System.out.println("Enter your name with no spaces:");
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();
        player p = new player(name);
        Monster bulbasaur = new Monster("Bulbasaur", "earth", 100);
        Monster charmander = new Monster("Charmander", "fire", 100);
        Monster squirtle = new Monster("Squirtle", "water", 100);
        Move growl = new Move("Growl", 20, 0, false, 10);
        Move waterSpray = new Move("Water Spray", 30, 1, true, 10);
        Move fireBreath = new Move("Fire Breath", 30, 1, true, 10);
        Move earthquake = new Move("Earthquake", 30, 1, true, 10);
        bulbasaur.addMove(growl);
        bulbasaur.addMove(earthquake);
        charmander.addMove(growl);
        charmander.addMove(fireBreath);
        squirtle.addMove(growl);
        squirtle.addMove(waterSpray);
        w.monsters.add(new Monster(squirtle));
        w.monsters.add(new Monster(bulbasaur));
        w.monsters.add(new Monster(charmander));
        w.spawnHealthPot();
        w.spawnHealthPot();
        w.spawnHealthPot();
        System.out.println("Welcome to Pokemon\n The game is about collecting monsters by fighting them.\n If you kill a monster you can't collect it so be careful\n press any character then enter to continue");
        s.next();
        System.out.println("The less health monsters have the higher chance you have for collecting them.");
        s.next();
        System.out.println("The elements work as following:\n1:Fire is strong against Water\n2:Water is strong against Earth\n3:Earth is string against Wind\n4:Wind is strong against fire");
        s.next();
        System.out.println("Attacking a monster with a stronger element results in dealing double damage. You can also deal double damage by landing a critical hit");
        s.next();
        System.out.println("Some moves have a cooldown so choose your moves wisely");
        s.next();
        System.out.println("Some moves are not elemental thus the monster's element doesn't matter");
        s.next();
        System.out.println("Choose one of these monsters to start with (Your first oponent is going to be a water monster so choose wisely)\n 1-Bulbasaur(Grass) 2-Charmander(Fire) 3-Squirtle(Water)");
        while (!correct) {

            choice = s.nextInt();
            switch (choice) {
                case 1:
                    p.addMonster(new Monster(bulbasaur));
                    correct = true;
                    break;
                case 2:
                    p.addMonster(new Monster(charmander));
                    correct = true;
                    break;
                case 3:
                    p.addMonster(new Monster(squirtle));
                    correct = true;
                    break;
                default:
                    System.out.println("Invalid choice please try again with a value from 1 to 3");

            }
        }
        System.out.println("This is time for your first fight. I hope you chose a suitable monster.\n In the upcoming battle you're going to fight Squirtle, a water monster.");
        //First fight only as it always needs to be squirtle//
        boolean fighting = true;
        Monster enemy = w.monsters.get(0);
        int usedMonster;
        while (fighting == true) {
            fighting = p.playTurn(enemy, w);
            usedMonster = p.lastMonsterUsedIndex;
            if (!fighting || p.monsters.get(usedMonster).getHealth() <= 0 || enemy.getHealth() <= 0) {
                World.setCurrentTurn(0);
                break;
            }
            enemy.attack(p, usedMonster);
            if (p.monsters.isEmpty()) {
                System.out.println(("You have no more monsters\n GAME OVER!"));
            }
        }
        enemy.setHealth(100);
        while (true) {//Game Loop//
            p.move();
            spawnedMonster = w.spawnObject(p);
            if (spawnedMonster != null) {
                System.out.println("A monster has just appeared!\n The monster is: " + spawnedMonster.toString());
                fighting = true;
                while (fighting == true) {
                    fighting = p.playTurn(spawnedMonster, w);
                    usedMonster = p.lastMonsterUsedIndex;
                    if (!fighting || p.monsters.get(usedMonster).getHealth() <= 0 || spawnedMonster.getHealth() <= 0) {
                        World.setCurrentTurn(0);
                        spawnedMonster.setHealth(100);
                        break;
                    }
                    spawnedMonster.attack(p, usedMonster);
                    if (p.monsters.isEmpty()) {
                        System.out.println(("You have no more monsters\n GAME OVER!"));
                        s.next();
                        System.exit(0);
                    }
                }

            } else {

            }
            
        }
    }

}
