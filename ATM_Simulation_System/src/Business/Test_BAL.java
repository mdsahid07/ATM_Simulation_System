package Business;

public class Test_BAL {
    public static void main(String[] args) {
        SystemModel m=new SystemModel();
        Role r=m.VerifyLogin("Shiqi","123456");

        if(r!=null){
            System.out.println(r.getType());
        }
        else{
            System.out.println("Invalid Username or Password");
        }

    }
}
