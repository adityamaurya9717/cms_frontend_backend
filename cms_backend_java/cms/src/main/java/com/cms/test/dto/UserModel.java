package com.cms.test.dto;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class UserModel {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String dob;

    public void setFromResultSet(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.email = rs.getString("email");
        this.phone = rs.getString("phone");
        this.firstName = rs.getString("first_name");
        this.lastName = rs.getString("last_name");
        this.dob = rs.getString("dob");
    }
}
