package Business;

public abstract class Role {
    private String name;
    private Integer ID;
    Role(String name){
        this.name = name;
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
    public boolean setPWD(String pwd){
        // upload the pwd to database
        return true;
    }
    public Role login(String pwd){
        // invoke the login_verify in System.class
        return this;
    }
}
