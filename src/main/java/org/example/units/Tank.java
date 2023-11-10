package org.example.units;


import org.example.units.interfaces.Attackable;

// Клас для танків
public class Tank extends MilitaryUnit implements Attackable {
    private static final double ARMOR_ACCUMULATES = 0.3;
    private int armor;

    public Tank(String name, int damage, int health, int price, int armor) {
        super(name, health, damage, price);
        this.armor = armor;
    }

    @Override
    public void receiveDamage(int damage) {
        if (armor > 0) {
            armor -= (int) ((1-ARMOR_ACCUMULATES)*damage);
            if (armor<0) {
                armor=0;
            }
        } else {
            super.receiveDamage(damage);
        }
    }

    @Override
    public String toString() {
        return super.toString()+ "Armor: " + armor;
    }

}
