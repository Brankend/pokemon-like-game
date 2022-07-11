package oopproject;
import java.util.Random;
import java.util.ArrayList;

public final class Monster extends character {

    private String element;
    ArrayList<Move> moves = new ArrayList<>();

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Monster(String name, String element, int health) {
        super.setName(name);
        super.setHealth(health);
        setElement(element);
    }

    public Monster(Monster m) {
        super.setName(m.getName());
        super.setHealth(m.getHealth());
        super.setLevel(m.getLevel());
        element = m.getElement();
        for (int i = 0; i < m.moves.size(); i++) {
            moves.add(new Move(m.moves.get(i)));
        }
    }

    public void addMove(Move m) {
        moves.add(m);
    }

    public String printMoves() {
        String allMoves = "";
        for (int i = 0; i < moves.size(); i++) {
            allMoves += i + 1 + ": ";
            allMoves += moves.get(i).toString();

        }
        return allMoves;
    }

    @Override
    public String toString() {
        return (super.getName() + " Health: " + super.getHealth() + " Element: " + element + "\n");
    }

    public void attack(player p, int m) {
        Random r = new Random();
        Move chosenMove = moves.get(r.nextInt(moves.size()));
        Monster enemy = p.monsters.get(m);
        int damage = chosenMove.getDamage() * chosenMove.critChanceCalc() * chosenMove.elementCheack(enemy, this);
        enemy.calcHealth(damage);
        System.out.println(getName() + " attacked: " + enemy.getName() + " using " + chosenMove.getName() + " for " + damage + " damage." + " Health: " + enemy.getHealth());
        if (enemy.getHealth() <= 0) {
            p.monsters.remove(m);
            System.out.println("Your monster was defeated and you can't use it anymore");
        }
    }
}
