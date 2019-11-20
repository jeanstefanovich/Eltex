package task3_University;

import javax.print.MultiDocPrintService;
import java.util.ArrayList;

public abstract class User implements CSV, JSON{
    private String id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;

    public String getId(){
        return this.id;
    }
    public void setId(String value){
        this.id = value;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String value){
        this.lastName = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}





