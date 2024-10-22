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
        boolean isValidUser = false;
        String userTypeStr = "";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsystem", "root", "123456");
            Statement statement = con.createStatement();
            ResultSet query = statement.executeQuery("Select * from User");

            while (query.next()) {
                String name = query.getString("name"); // Column "name" (String)
                String pwd = query.getString("password");
                String userType = query.getString("userType");
                if (username.equals(name) && password.equals(pwd) && userType.equals("Admin")) {
                    isValidUser = true;
                    userTypeStr = userType;
                    break;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(/*type*/"".equals(ROLE_TYPE.ADMIN)){
            role = new Admin("Admin",1);
        }
        else{
            role = new User("User",2);
        }
       return role;
    }
}
