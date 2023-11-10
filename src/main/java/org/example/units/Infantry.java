package org.example.units;

import org.example.BattleField;
import org.example.graphic.StaticGrapicClass;
import org.example.units.interfaces.Attackable;

import java.awt.*;

public class Infantry extends MilitaryUnit implements Attackable {
    private static final int healAmount = 100;
    private boolean healingEnabled;

    public Infantry(String name, int health, int damage, int price) {
        super(name, health, damage, price);
    }

    public void setHealingEnabled() {
        this.healingEnabled = true;
    }

    @Override
    public int attackWithDamage(MilitaryUnit unit) {
        if (unit instanceof Tank) {
            // Chance to double damage the tank using anti-tanks shells, grenade
            if (BattleField.random.nextBoolean()) {
                System.out.println( unit.name + " Tank is seriously damaged by infantry");
                StaticGrapicClass.drawPanzerFaust();
                return super.attackWithDamage(unit) + super.attackWithDamage(unit);
            }
        }
        if (unit instanceof Artillery) {
            // Chance to double damage the tank using anti-tanks shells, grenade
            System.out.println( unit.name + " is out of range for infantry");
            return 0;
        }
        return super.attackWithDamage(unit);
    }

}