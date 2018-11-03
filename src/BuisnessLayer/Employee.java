package BuisnessLayer;

import java.util.Date;

public class Employee extends companydata.Employee {
    private String employee;
    private String emp_id;
    private String emp_name;
    private String emp_no;
    private Date hire_date;
    private String job;
    private Double salary;
    private String dept_id;
    private String mng_id;

    public String getDept_id() {
        return dept_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public String getEmployee() {
        return employee;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public String getJob() {
        return job;
    }

    public String getMng_id() {
        return mng_id;
    }

    public Double getSalary() {
        return salary;
    }

}
