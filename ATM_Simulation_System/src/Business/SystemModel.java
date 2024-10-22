package Business;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SystemModel {
    public List<Role> roles;
    public Role role;

    SystemModel(){
        roles = new ArrayList<Role>();
        role = null;
    }
    public Role VerifyLogin(String username, String password) {
        // If user is invalid than return null

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsystem", "root", "123456");
            Statement statement = con.createStatement();
            ResultSet query = statement.executeQuery("Select * from User");

            while (query.next()) {
                java.lang.System.out.println(query.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(/*type*/"".equals(ROLE_TYPE.ADMIN)){
            role = new Admin("Admin");
        }
        else{
            role = new User("User");
        }
       return role;
    }
}
