package ServiceLayer;
import BuisnessLayer.Buisness;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "SoapService")
public class Service {

    @WebMethod(operationName = "deleteAll")
    public String deleteAll(String companyName) {
        Buisness bl = new Buisness();
        return bl.deleteAllCompany(companyName);
    }

    @WebMethod(operationName = "getDepartments")
    public String getDepartments(String deptJson) {
        Buisness bl = new Buisness();
        return bl.getDepartmentsByName(deptJson);
    }

    @WebMethod(operationName = "getAllDepartments")
    public String getAllDepartments(String company) {
        Buisness bl = new Buisness();
        return bl.getDepartmentsByCompany(company);
    }

    @WebMethod(operationName = "insertDepartments")
    public String insertDepartments(String deptJson){
        Buisness bl = new Buisness();
        return bl.insertDepartmentToData(deptJson);
    }

    @WebMethod(operationName = "updateDepartment")
    public String updateDepartment(String deptJson){
        Buisness bl = new Buisness();
        return bl.updateDepartmentToData(deptJson);
    }

    @WebMethod(operationName = "deleteDepartment")
    public String deleteDepartment(String deptJson){
        Buisness bl = new Buisness();
        return bl.deleteDepartmentFromData(deptJson);
    }

    @WebMethod(operationName = "getEmployee")
    public String getEmployee(int emp_id){
        Buisness bl = new Buisness();
        return bl.getEmployeeById(emp_id);
    }

    @WebMethod(operationName = "getAllEmployees")
    public String getAllEmployees(String company){
        Buisness bl = new Buisness();
        return bl.getAllEmployees(company);
    }

    @WebMethod(operationName = "insertEmployee")
    public String insertEmployee(String inJson){
        Buisness bl = new Buisness();
        return bl.insertEmployeeasData(inJson);
    }

    @WebMethod(operationName = "updateEmployee")
    public String updateEmployee(String inJson){
        Buisness bl = new Buisness();
        return bl.updateEmployeeInData(inJson);
    }

    @WebMethod(operationName = "deleteEmployee")
    public String deleteEmployee(int emp_id){
        Buisness bl = new Buisness();
        return bl.deleteEmployeeInData(emp_id);
    }

    @WebMethod(operationName = "getTimecard")
    public String getTimecard(int timecard_id){
        Buisness bl = new Buisness();
        return bl.getTimecardsInData(timecard_id);
    }

    @WebMethod(operationName = "getAllTimecards")
    public String getAllTimecards(int emp_id){
        Buisness bl = new Buisness();
        return bl.getAllTimecardsInData(emp_id);
    }

    @WebMethod(operationName = "insertTimecard")
    public String insertTimecard(String inJson){
        Buisness bl = new Buisness();
        return bl.insertTimecardInData(inJson);
    }

    @WebMethod(operationName = "updateTimecard")
    public String updateTimecard(String inJson){
        Buisness bl = new Buisness();
        return bl.updateTimecardInData(inJson);
    }

    @WebMethod(operationName = "deleteTimecard")
    public String deleteTimecard(int timecard_id){
        Buisness bl = new Buisness();
        return bl.deleteTimecardfromData(timecard_id);
    }
}