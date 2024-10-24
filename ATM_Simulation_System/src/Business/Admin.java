package Business;

import Data_Access.MainDAL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends Role {
    Admin(String name, Integer ID) {
        super(name, ID, ROLE_TYPE.ADMIN);
    }

    public boolean delete_account(Integer AccNumber) {
        return MainDAL.write("Delete from Account Where AccNumber=" + AccNumber);
    }

    //    public boolean add_account(String name,Integer Id){
//        return MainDAL.write(String.format("Insert into Account (Pin,Balance,Name,UserId) Values('%s',%f,'%s',%d)","0000",0.0,name,Id));
//    }
//    public boolean block_account(Integer ID){
//        return true;
//    }
    public boolean add_user(String name, String SSN, String address, String phone) {
        try {
            MainDAL.write(String.format("Insert into User (Name,Password,UserType) Values ('%s','%s','%s')", name, "123456", ROLE_TYPE.USER.toString()));
            ResultSet set = MainDAL.read("SELECT Id FROM  User ORDER BY Id DESC LIMIT 1");
            int Id = -1;
            if (set.next()) {
                Id = set.getInt("Id");
            }
            return MainDAL.write(String.format("Insert into Account (Pin,Balance,Name,UserId,SSN,Address,Phone) Values('%s',%f,'%s',%d,'%s','%s','%s')", "0000", 0.0, name, Id, SSN, address, phone));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkUserName(String userName) {
        try {
            ResultSet set= MainDAL.read("SELECT Name FROM  User WHERE Name = '" + userName + "'");
            if (set.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkSSN(String SSN) {
        try {
            ResultSet set= MainDAL.read("SELECT SSN FROM  Account WHERE SSN = '" + SSN + "'");
            if (set.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkPhoneNumber(String phoneNumber) {
        try {
            ResultSet set= MainDAL.read("SELECT AccNumber FROM  Account WHERE Phone = '" + phoneNumber + "'");
            if (set.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    public boolean update_user(User user){
//        return true;
//    }
    public boolean delete_user(Integer ID) {
        System.out.println(ID);
        MainDAL.write(String.format("Delete from Account Where UserId='%d'", ID));
        return MainDAL.write("Delete from User Where Id=" + ID);
    }
//    public boolean update_account(){
//
//    }
//    public static void main(String[] args){
//        Admin ad = new Admin("jj",1);
//        User user = new User("sss",5);
//        ad.add_account(user,"1472");
//    }
}
