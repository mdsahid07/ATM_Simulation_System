package Business;

import Data_Access.MainDAL;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Role {
    private String name;
    private Integer ID;
    private ROLE_TYPE type;
    Role(String name,Integer ID,ROLE_TYPE type){
        this.name = name;
        this.ID = ID;
        this.type = type;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public Integer getID(){
        return this.ID;
    }
    public boolean updatePWD(String pwd, String new_pwd) throws SQLException {
        // upload the pwd to database
        ResultSet query =  MainDAL.read("Select Password from User Where Id="+this.getID());
        boolean valid = false;
        while(query.next()){
            if(query.getString("Password").equals(pwd)){
                MainDAL.write("Update User Set Password='" + new_pwd+"' Where Id=" + this.getID() );
            }else{
                // prompt pwd error
            }
        }
        return true;
    }
    public ROLE_TYPE getType(){
        return this.type;
    }
}
