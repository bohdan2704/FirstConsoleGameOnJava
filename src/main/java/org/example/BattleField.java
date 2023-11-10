package org.example;

import org.example.graphic.StaticGrapicClass;
import org.example.units.Infantry;
import org.example.units.MilitaryUnit;
import java.util.List;
import java.util.Random;

public class BattleField {
    private final static int maxBudgetPerTeam = 900;
    public final static Random random = new Random();
    private static final int teamACapacity = 3;
    private static final int teamBCapacity = 3;
    private int teamADamaged = 0;
    private int teamBDamaged = 0;

    private List<MilitaryUnit> teamA;
    private List<MilitaryUnit> teamB;

    public BattleField() {
        StaticGrapicClass.drawIntro();

        CommandsManager menu = new CommandsManager();
//        teamA = menu.formCommand(teamACapacity);
//        teamB = menu.formCommand(teamBCapacity);
        teamA = menu.formCommandByPrice(maxBudgetPerTeam);
        teamB = menu.formCommandByPrice(maxBudgetPerTeam);

        System.out.println("These teams are about to fight: ");
        System.out.println("Team A: ");
        System.out.println(teamA);
        System.out.println("Team B: ");
        System.out.println(teamB);
    }

    public void battle() {
        while (!teamA.isEmpty() && !teamB.isEmpty()) {
            teamBDamaged += teamAttacks(teamB, teamA);
            teamADamaged += teamAttacks(teamA, teamB);
        }
        if (teamA.isEmpty()) {
            System.out.println("Team B has won the battle. \nTotal damage that TeamB caused: " + teamBDamaged);
            System.out.println("TeamA damaged: " + teamADamaged);
            System.out.println(teamB);
        } else if (teamB.isEmpty()) {
            System.out.println("Team A has won the battle. \nTotal damage that TeamA caused: " + teamADamaged);
            System.out.println("TeamB damaged: " + teamBDamaged);
            System.out.println(teamA);
        } else {
            throw new RuntimeException("37 line in BattleFIeld");
        }
    }

    public int teamAttacks(List<MilitaryUnit> attackTeam, List<MilitaryUnit> defenceTeam) {
        int causedDamage = 0;
        for (MilitaryUnit militaryUnit: attackTeam) {
            int randomUnit = random.nextInt(defenceTeam.size());
            MilitaryUnit defender = defenceTeam.get(randomUnit);
            causedDamage += militaryUnit.attackWithDamage(defender);

            if (defender.isDestroyed()) {
                defenceTeam.remove(randomUnit);
                StaticGrapicClass.drawExplosion();
            }
            if (defenceTeam.isEmpty()) {
                return causedDamage;
            }
        }
        System.out.println(" -- End of round for team...\n\n");
        return causedDamage;
    }

}
