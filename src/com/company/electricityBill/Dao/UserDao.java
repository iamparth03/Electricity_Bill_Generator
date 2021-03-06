package com.company.electricityBill.Dao;

import com.company.electricityBill.config.DbConnectivity;
import com.company.electricityBill.model.UserDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements Dao<UserDetails> {

    /**
     *
     * @param customerId unique customer Id
     * @return userDetails's data from the database
     */
    @Override
    public UserDetails getById(long customerId) {
        String sql = "select * from userDetails where customerId = " + customerId + "";
        try {
            Connection connection = DbConnectivity.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UserDetails userDetails = new UserDetails();
                userDetails.setCustomerId(rs.getString(2));
                userDetails.setCustomerName(rs.getString(3));
                userDetails.setState(rs.getString(4));
                userDetails.setUnits(rs.getInt(5));
                userDetails.setWallet(rs.getLong(6));

                return userDetails;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(UserDetails userDetails) {
        String sql = "update userDetails set wallet = ? where customerId = ?";
        try {
            Connection connection = DbConnectivity.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, userDetails.getWallet());
            ps.setString(2, userDetails.getCustomerId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(UserDetails userDetails) {
        return true;
    }

}

