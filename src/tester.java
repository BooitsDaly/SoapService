import ServiceLayer.Service;

public class tester {
    public static void main(String args[]) {
        Service test = new Service();
        //System.out.println(test.getAllTimecards(2));
        //System.out.println(test.insertDepartments("{\"company\":\"cnd9351\",\"dept_name\":\"IT\",\"dept_no\":\"c9351\",\"location\":\"rochester\"}"));
        //System.out.println(test.insertEmployee("{\"emp_name\":\"daly\",\"emp_no\":\"cnd9351\",\"hire_date\":\"2018-04-16\",\"job\":\"programmer\",\"salary\":5000.0,\"dept_id\":1,\"mng_id\":2 }"));
        //System.out.println(test.updateDepartment("{\"dept_id\":507,\"company\":\"9351\",\"dept_name\":\"IT\",\"dept_no\":\"cn9351\",\"location\":\"rochester\"}"));
        System.out.println(test.deleteDepartment("{\"dept_id\":507,\"company\":\"cnd9351\",\"dept_name\":\"IT\",\"dept_no\":\"cn9351\",\"location\":\"rochester\"}"));
        System.out.println(test.getAllDepartments("cnd9351"));

    }
}