package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into lease_contracts (contract_id,VIN,lease_start,lease_end,monthly_payment) values (?,?,?,?,?)")) {
            preparedStatement.setInt(1, leaseContract.getContractId());
            preparedStatement.setString(2, leaseContract.getVin());
            preparedStatement.setObject(3, leaseContract.getLeaseStart());
            preparedStatement.setObject(4, leaseContract.getLeaseEnd());
            preparedStatement.setDouble(5, leaseContract.getMonthlyPayment());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}