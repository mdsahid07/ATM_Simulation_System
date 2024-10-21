package Business;

public class User extends Role{
    private ROLE_TYPE type;
    private List<Account> account_list;
    User(String name){
        super(name);
        this.type = ROLE_TYPE.USER;
    }

    
//    public List<Account> getAccount(){
//
//    }
}
