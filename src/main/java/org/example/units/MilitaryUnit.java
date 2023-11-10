package org.example.units;

import org.example.units.interfaces.Attackable;

public class MilitaryUnit implements Attackable {
    protected String name;
    protected int health;
    protected int damage;
    protected int price;

    public MilitaryUnit(String name, int health, int damage, int price) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void receiveDamage(int damage) {
        health -= damage;
//        if (health <= 0) {
//            health = 0;
//        } else {
//            System.out.println(name + " receives " + damage + " damage. Remaining health: " + health);
//        }
    }

    public boolean isDestroyed() {
        if (health <= 0) {
            System.out.println(name + " has been destroyed!");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "\nUnit:  " +
                "Name=" + name +
                ", Health=" + health +
                ", Damage=" + damage +
                ", Price=" + price;
    }

    @Override
    public int attackWithDamage(MilitaryUnit unit) {
        unit.receiveDamage(damage);
        System.out.println(this.name + " caused " + damage + " point of damage to " + unit.name);
        return damage;
    }
}
