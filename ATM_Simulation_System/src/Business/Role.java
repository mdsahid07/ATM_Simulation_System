package Business;

public abstract class Role {
    private String name;
    private Integer ID;
    Role(String name,Integer ID){
        this.name = name;
        this.ID = ID;
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
    public boolean updatePWD(String pwd, String new_pwd){
        // upload the pwd to database
        return true;
    }
}
