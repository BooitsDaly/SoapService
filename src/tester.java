import ServiceLayer.Service;

public class tester {
    public static void main(String args[]) {
        Service test = new Service();
        //System.out.println(test.getAllTimecards(2));
        //System.out.println(test.insertDepartments("{\"company\":\"cnd9351\",\"dept_name\":\"IT\",\"dept_no\":\"c9351\",\"location\":\"rochester\"}"));
        //System.out.println(test.insertEmployee("{\"emp_name\":\"cndaly\",\"emp_no\":\"ghfgh\",\"hire_date\":\"2018-04-18\",\"job\":\"programmer\",\"salary\":5000.0,\"dept_id\":510 , \"mng_id\": 375 }"));
        System.out.println(test.deleteEmployee(0));
        System.out.println(test.getAllEmployees("cnd9351"));
        //System.out.println(test.updateDepartment("{\"dept_id\":507,\"company\":\"9351\",\"dept_name\":\"IT\",\"dept_no\":\"cn9351\",\"location\":\"rochester\"}"));
        //System.out.println(test.deleteDepartment("{\"dept_id\":507,\"company\":\"cnd9351\",\"dept_name\":\"IT\",\"dept_no\":\"cn9351\",\"location\":\"rochester\"}"));
        //System.out.println(test.getDepartments("{\"dept_id\":510,\"company\":\"cnd9351\",\"dept_name\":\"IT\",\"dept_no\":\"cn9351\",\"location\":\"rochester\"}"));
        //System.out.println(test.getAllDepartments("cnd9351"));
        //System.out.println(test.getEmployee(375));
        //System.out.println(test.updateEmployee("{\"emp_name\":\"cndaly\",\"emp_no\":\"weeeeee\",\"hire_date\":\"2018-04-18\",\"job\":\"programmer\",\"salary\":5000.0,\"dept_id\":510 , \"mng_id\": 375 }"));
        //System.out.println(test.deleteEmployee(386));
        //System.out.println(test.insertTimecard("{\"emp_id\":375,\"start_time\":\"2018-06-14 11:30:00\",\"end_time\": \"2018-06-14 15:30:00\"}"));
        //System.out.println(test.deleteTimecard(147));
        //System.out.println(test.updateTimecard("{\"timecard_id\": 143,\"emp_id\":375,\"start_time\":\"2018-06-14 11:30:00\",\"end_time\": \"2018-06-14 14:00:00\"}"));
        //System.out.println(test.getAllTimecards(375));


    }
}