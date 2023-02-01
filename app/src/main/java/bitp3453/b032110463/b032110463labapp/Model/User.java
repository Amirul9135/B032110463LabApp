package bitp3453.b032110463.b032110463labapp.Model;
import java.io.Serializable;

public class User implements Serializable {
    String strName, strPassword, strAddress, strEmail,strBirthDate, strGender;

    public User(String strName, String strPassword, String strAddress, String strEmail, String strBirthDate, String strGender) {
        this.strName = strName;
        this.strPassword = strPassword;
        this.strAddress = strAddress;
        this.strEmail = strEmail;
        this.strBirthDate = strBirthDate;
        this.strGender = strGender;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getStrAddress() {
        return strAddress;
    }

    public void setStrAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrBirthDate() {
        return strBirthDate;
    }

    public void setStrBirthDate(String strBirthDate) {
        this.strBirthDate = strBirthDate;
    }

    public String getStrGender() {
        return strGender;
    }

    public void setStrGender(String strGender) {
        this.strGender = strGender;
    }
}
