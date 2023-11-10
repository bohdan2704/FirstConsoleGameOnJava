package org.example;
import org.example.db.DatabaseManager;
import org.example.units.MilitaryUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandsManager {
    private List<MilitaryUnit> allUnits;
    public CommandsManager() {
        DatabaseManager databaseManager = new DatabaseManager();
        allUnits = databaseManager.getAllUnits();
    }

    public List<MilitaryUnit> formCommand(int teamCapacity) {
        // Random commands formation
        ArrayList<MilitaryUnit> militaryUnits = new ArrayList<>();
        for (int i = 0; i < teamCapacity; i++) {
            int index = BattleField.random.nextInt(allUnits.size());
            militaryUnits.add(allUnits.get(index));
            allUnits.remove(index);
        }
        return militaryUnits;
//        System.out.println(allUnits);
//        List<MilitaryUnit> team = new ArrayList<>();
//        while (teamCapacity > 0) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("Enter team member: ");
//            String searchName = scanner.nextLine();
//            MilitaryUnit searchUnit = findUnitByName(allUnits, searchName);
//            if (searchUnit != null) {
//                teamCapacity--;
//                team.add(searchUnit);
//                System.out.println("Good, added to your team");
//            } else {
//                System.out.println("Not found, try once more");
//            }
//        }
//        return team;
    }
    public List<MilitaryUnit> formCommandByPrice(int price) {
        // Random commands formation
        int maxIterations = allUnits.size()*100;
        ArrayList<MilitaryUnit> militaryUnits = new ArrayList<>();
        for (int i = 0; i < maxIterations; i++) {
            int index = BattleField.random.nextInt(allUnits.size());
            MilitaryUnit pickedUnit = allUnits.get(index);
            if (price - pickedUnit.getPrice() >= 0) {
                militaryUnits.add(pickedUnit);
                allUnits.remove(index);
                price-=pickedUnit.getPrice();
            }
        }
        return militaryUnits;
    }

    private MilitaryUnit findUnitByName(List<MilitaryUnit> list, String targetName) {
        for (MilitaryUnit unit : list) {
            if (unit.getName().equals(targetName)) {
                return unit; // Found the object with the desired name
            }
        }
        return null; // Object with the specified name not found
    }
}
