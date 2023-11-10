package org.example.db;

import org.example.units.MilitaryUnit;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private DatabaseConnection dbConn = new DatabaseConnection();
    private final List<MilitaryUnit> tankList = dbConn.selectFromTanks();
    private final List<MilitaryUnit> infantryList = dbConn.selectFromInfantry();
    private final List<MilitaryUnit> artilleryList = dbConn.selectFromArtillery();

    public List<MilitaryUnit> getAllUnits() {
        ArrayList<MilitaryUnit> combinedList = new ArrayList<>();
        combinedList.addAll(tankList);
        combinedList.addAll(infantryList);
        combinedList.addAll(artilleryList);
        return combinedList;
    }
}
