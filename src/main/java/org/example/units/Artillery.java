package org.example.units;

import org.example.BattleField;
import org.example.units.interfaces.Attackable;

import java.util.Random;

public class Artillery extends MilitaryUnit implements Attackable {
    private static final double coefficientForArtilleryDamageToInfantry = 1 - 0.3;
    private static final Random random = new Random();
    private final double chanceToShoot;

    public Artillery(String name, int health, int damage, int price, double chanceToShoot) {
        super(name, health, damage, price);
        this.chanceToShoot = chanceToShoot;
    }

    public boolean chanceToFire() {
        return random.nextDouble() < chanceToShoot;
    }

    @Override
    public int attackWithDamage(MilitaryUnit unit) {
        if (chanceToFire()) {
            if (unit instanceof Infantry) {
                unit.health += (int) (damage*coefficientForArtilleryDamageToInfantry);
            }
            return super.attackWithDamage(unit);
        } else {
            System.out.println("Artillery missed the target " + unit.name + " , reloading...");
        }
        return 0;
    }
}
