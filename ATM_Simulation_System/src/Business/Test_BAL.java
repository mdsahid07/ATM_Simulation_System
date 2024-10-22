package Business;

public class Test_BAL {
    public static void main(String[] args) {
        SystemModel m=new SystemModel();
        Role r=m.VerifyLogin("Shiqi","123456");

        System.out.println(r);
    }
}
