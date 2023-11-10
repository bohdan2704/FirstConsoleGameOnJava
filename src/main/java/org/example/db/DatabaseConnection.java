package org.example.db;

import org.example.units.Artillery;
import org.example.units.Infantry;
import org.example.units.MilitaryUnit;
import org.example.units.Tank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String dbName = "databaseName=MilitaryUnits";
    private static final String jdbcUrl =  String.format("jdbc:sqlserver://localhost:1434;%s;integratedSecurity=true;" +
            "encrypt=true;trustServerCertificate=true", dbName) ;
    private static final String username = "loginTest";
    private static final String password = "qwerty";
    private final Connection conn;

    public DatabaseConnection() {
        conn = establishConnection();
    }

    public List<MilitaryUnit> selectFromTanks() {
        List<MilitaryUnit> units = new ArrayList<>();
        String query = "SELECT tankName, tankDamage, tankHp, price, tankArmorThickness FROM dbo.Tanks";
        String [] columns = {"tankName", "tankDamage", "tankHp", "price", "tankArmorThickness"};

        try (PreparedStatement preparedStatement = conn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString(columns[0]);
                int damage = resultSet.getInt(columns[1]);
                int hp = resultSet.getInt(columns[2]);
                int price = resultSet.getInt(columns[3]);
                int armorThickness = resultSet.getInt(columns[4]);


                Tank unit = new Tank(name, damage, hp, price, armorThickness);
                units.add(unit);
            }

        } catch (SQLException e) {
            throw new RuntimeException("DeveloperExceptionCaption: Exception while executing the query", e);
        }
        return units;
    }

    public List<MilitaryUnit> selectFromArtillery() {
        List<MilitaryUnit> units = new ArrayList<>();
        String query = "SELECT artilleryName, artilleryDamage, artilleryHp, price, chanceToHit FROM dbo.Artillery";
        String [] columns = {"artilleryName", "artilleryDamage", "artilleryHp", "price", "chanceToHit"};

        try (PreparedStatement preparedStatement = conn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString(columns[0]);
                int damage = resultSet.getInt(columns[1]);
                int hp = resultSet.getInt(columns[2]);
                int price = resultSet.getInt(columns[3]);
                double chanceToHit = resultSet.getDouble(columns[4]);


                Artillery unit = new Artillery(name, hp, damage, price, chanceToHit);
                units.add(unit);
            }

        } catch (SQLException e) {
            throw new RuntimeException("DeveloperExceptionCaption: Exception while executing the query", e);
        }
        return units;
    }

    public List<MilitaryUnit> selectFromInfantry() {
        List<MilitaryUnit> units = new ArrayList<>();
        String query = "SELECT divisionName, infantryDamage, infantryHp, price FROM dbo.Infantry";
        String [] columns = {"divisionName", "infantryDamage", "infantryHp", "price"};

        try (PreparedStatement preparedStatement = conn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString(columns[0]);
                int damage = resultSet.getInt(columns[1]);
                int hp = resultSet.getInt(columns[2]);
                int price = resultSet.getInt(columns[3]);


                Infantry unit = new Infantry(name, hp, damage, price);
                units.add(unit);
            }

        } catch (SQLException e) {
            throw new RuntimeException("DeveloperExceptionCaption: Exception while executing the query", e);
        }
        return units;
    }

    private Connection establishConnection() {
        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("DeveloperExceptionCaption: Cannot connect to database", e);
        }
    }
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("DeveloperExceptionCaption: Error while closing connection before destroying the DB object", e);
            }
        }
    }
}