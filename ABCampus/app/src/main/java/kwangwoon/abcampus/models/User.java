package kwangwoon.abcampus.models;

public class User {

    private String name;
    private String student_id;
    private String unique_id;
    private String password;
    private String old_password;
    private String new_password;

    public String getName() {
        return name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getUnique_id() {
        return unique_id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setStudent_id(String student_id) {
        this.student_id= student_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

}