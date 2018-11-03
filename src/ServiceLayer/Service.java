package ServiceLayer;
import BuisnessLayer.Buisness;


public class Service {
    public String deleteAll(String companyName) {
        Buisness bl = new Buisness();
        return bl.deleteAllCompany(companyName);
    }

    public String getDepartments(String deptJson) {
        Buisness bl = new Buisness();
        return bl.getDepartmentsByName(deptJson);
    }

    public String getAllDepartments(String company) {
        Buisness bl = new Buisness();
        return bl.getDepartmentsByCompany(company);
    }

    public String insertDepartments(String deptJson){
        Buisness bl = new Buisness();
        return bl.insertDepartmentToData(deptJson);
    }

    public String updateDepartment(String deptJson){
        Buisness bl = new Buisness();
        return bl.updateDepartmentToData(deptJson);
    }

    public String deleteDepartment(String deptJson){
        Buisness bl = new Buisness();
        return bl.deleteDepartmentFromData(deptJson);
    }

    public String getEmployee(int emp_id){
        Buisness bl = new Buisness();
        return bl.getEmployeeById(emp_id);
    }

    public String getAllEmployees(String company){
        Buisness bl = new Buisness();
        return bl.getAllEmployees(company);
    }

    public String insertEmployee(String inJson){
        Buisness bl = new Buisness();
        return bl.insertEmployeeasData(inJson);
    }

    public String updateEmployee(String inJson){
        Buisness bl = new Buisness();
        return bl.updateEmployeeInData(inJson);
    }

    public String deleteEmployee(int emp_id){
        Buisness bl = new Buisness();
        return bl.deleteEmployeeInData(emp_id);
    }

    public String getTimecard(int timecard_id){
        Buisness bl = new Buisness();
        return bl.getTimecardsInData(timecard_id);
    }

    public String getAllTimecards(int emp_id){
        Buisness bl = new Buisness();
        return bl.getAllTimecardsInData(emp_id);
    }

    public String insertTimecard(String inJson){
        Buisness bl = new Buisness();
        return bl.insertTimecardInData(inJson);
    }

    public String updateTimecard(String inJson){
        Buisness bl = new Buisness();
        return bl.updateTimecardInData(inJson);
    }

    public String deleteTimecard(int timecard_id){
        Buisness bl = new Buisness();
        return bl.deleteTimecardfromData(timecard_id);
    }
}