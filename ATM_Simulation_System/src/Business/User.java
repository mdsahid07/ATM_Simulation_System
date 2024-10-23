package Business;

import Data_Access.MainDAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class User extends Role{
    private List<Account> account_list;
    public User(String name,Integer ID){
        super(name,ID,ROLE_TYPE.USER);
    }

    
//    public List<Account> getAccount() throws SQLException {
//        List<Account> list = new ArrayList<>();
//        ResultSet query =  MainDAL.read("Select * from Account Where UserId=" + this.getID());
//
//        while(query.next()){
//            list.add(new Account(new User(query.getString("Name"),query.getInt("user_id"))
//                    ,query.getInt("AccNumber")));
//        }
//        return list;
//    }
    @Override
    public String toString(){
        String str = "";
        str += "Id:" + this.getID() + " Name:" +this.getName() + " Type:" + this.getType();
        return str;
    }
}
