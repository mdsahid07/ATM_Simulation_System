package Business;

public class Admin extends Role{
    Admin(String name,Integer ID){
        super(name,ID,ROLE_TYPE.ADMIN);
    }
    public boolean delete_account(Integer ID){
           return true;
    }
    public boolean block_account(Integer ID){
        return true;
    }
    public boolean add_user(User user){

        return true;
    }
    public boolean update_user(User user){
        return true;
    }
    public boolean delete_user(Integer ID){
        return true;
    }
//    public boolean update_account(){
//
//    }
}
