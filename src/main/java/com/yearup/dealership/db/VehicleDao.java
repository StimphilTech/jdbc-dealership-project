package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("Insert into vehicles (VIN,make,model,year,SOLD,color,vehicleType,odometer,price) values (?,?,?,?,?,?,?,?,?) ")) {
            preparedStatement.setString(1,vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3,vehicle.getModel());
            preparedStatement.setInt(4,vehicle.getYear());
            preparedStatement.setBoolean(5, vehicle.isSold());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7,vehicle.getVehicleType());
            preparedStatement.setInt(8,vehicle.getOdometer());
            preparedStatement.setDouble(9,vehicle.getPrice());
            preparedStatement.executeUpdate();


        } catch (Exception e) {

            e.printStackTrace();
        }

}

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE From vehicles where VIN = ?")) {
            preparedStatement.setString(1,VIN);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        List<Vehicle> priceRange = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE price between ? AND ? ")){
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2,maxPrice);


            try (ResultSet results = preparedStatement.executeQuery()){
                while (results.next())
            {priceRange.add(createVehicleFromResultSet(results));}}



        } catch (Exception e) {
            e.printStackTrace();

    }return  priceRange;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        List<Vehicle> makeModel = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE make = ? AND model = ?")){
             preparedStatement.setString(1, make);
            preparedStatement.setString(2,model);


            try (ResultSet results = preparedStatement.executeQuery()){
                while (results.next())
                {makeModel.add(createVehicleFromResultSet(results));}}

        } catch (Exception e) {
            e.printStackTrace();

        }return  makeModel;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        List<Vehicle> yearRange = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE year BETWEEN ? AND ? ")){
            preparedStatement.setInt(1,minYear);
            preparedStatement.setInt(2,maxYear);

            try (ResultSet results = preparedStatement.executeQuery()){
                while (results.next())
                {yearRange.add(createVehicleFromResultSet(results));}}



        } catch (Exception e) {
            e.printStackTrace();

        }return  yearRange;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        List<Vehicle> byColor = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicles WHERE color = ?")){
              preparedStatement.setString(1,color);

            try (ResultSet results = preparedStatement.executeQuery()){
                while (results.next())
                {byColor.add(createVehicleFromResultSet(results));}}





        } catch (Exception e) {
            e.printStackTrace();

        }return byColor;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        List<Vehicle> mileageRange = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Vehicles WHERE odometer BETWEEN ? AND ?")){
            preparedStatement.setInt(1,minMileage);
            preparedStatement.setInt(2,maxMileage);

            try (ResultSet results = preparedStatement.executeQuery()){
                while (results.next())
                {mileageRange.add(createVehicleFromResultSet(results));}}



        } catch (Exception e) {
            e.printStackTrace();

        }return  mileageRange;
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        List<Vehicle> byType = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Vehicles WHERE vehicletype = ?")){
            preparedStatement.setString(1,type);

            try (ResultSet results = preparedStatement.executeQuery()){
                while (results.next())
                {byType.add(createVehicleFromResultSet(results));}}



        } catch (Exception e) {
            e.printStackTrace();

        }return  byType;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
