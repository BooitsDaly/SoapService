package BuisnessLayer;

public class Department{
    private String department;
    private int dept_id;
    private String company;
    private String dept_name;
    private String dept_no;
    private String location;

    public String getDepartment(){
        return department;
    }

    public int getDepartmentID(){
        return dept_id;
    }

    public String getDepartmentNumb(){
        return dept_no;
    }

    public String getCompany(){
        return company;
    }

    public String getDept_name() {
        return dept_name;
    }

    public String getDept_no() {
        return dept_no;
    }

    public String getLocation() {
        return location;
    }
}
