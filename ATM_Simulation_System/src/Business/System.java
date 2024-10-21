package Business;

import java.util.ArrayList;
import java.util.List;

public class System {
    public List<Role> roles;
    public Role role;

    System(){
        roles = new ArrayList<Role>();
        role = null;
    }
    public Role VerifyLogin(String username, String password) {
        // If user is invalid than return null
        if(/*type*/"".equals(ROLE_TYPE.ADMIN)){
            role = new Admin("Admin");
        }
        else{
            role = new User("User");
        }
       return role;
    }
}
