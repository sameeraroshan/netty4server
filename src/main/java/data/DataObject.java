package data;

import io.netty.buffer.ByteBuf;

/**
 * Created by roshans on 1/27/2015.
 */
public class DataObject {
    private String firstName;
    private String secondName;
    private String message;

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
