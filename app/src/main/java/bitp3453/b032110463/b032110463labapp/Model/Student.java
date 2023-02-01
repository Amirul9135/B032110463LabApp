package bitp3453.b032110463.b032110463labapp.Model;


public class Student {
    private String strFullname, strStudNo, strEmail, strGender, strBirthdate, strState;

    public Student(String strFullname, String strStudNo, String strEmail, String strGender, String strBirthdate, String strState) {
        this.strFullname = strFullname;
        this.strStudNo = strStudNo;
        this.strEmail = strEmail;
        this.strGender = strGender;
        this.strBirthdate = strBirthdate;
        this.strState = strState;
    }
    public Student(){
        this.strFullname = "";
        this.strStudNo = "";
        this.strEmail = "";
        this.strGender = "";
        this.strBirthdate = "";
        this.strState = "";
    }

    public String getStrFullname() {
        return strFullname;
    }

    public void setStrFullname(String strFullname) {
        this.strFullname = strFullname;
    }

    public String getStrStudNo() {
        return strStudNo;
    }

    public void setStrStudNo(String strStudNo) {
        this.strStudNo = strStudNo;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrGender() {
        return strGender;
    }

    public void setStrGender(String strGender) {
        this.strGender = strGender;
    }

    public String getStrBirthdate() {
        return strBirthdate;
    }

    public void setStrBirthdate(String strBirthdate) {
        this.strBirthdate = strBirthdate;
    }

    public String getStrState() {
        return strState;
    }

    public void setStrState(String strState) {
        this.strState = strState;
    }
}
