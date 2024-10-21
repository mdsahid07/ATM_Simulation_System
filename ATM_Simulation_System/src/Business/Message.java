package Business;

public class Message {
    public static String message;
    public static int ID;

    Message(String Message, int ID) {
        this.message = Message;
        this.ID = ID;
    }

    public String getMessage() {
        return message;
    }

    public int getID() {
        return ID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return message;
    }
}
