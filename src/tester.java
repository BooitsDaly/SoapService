import ServiceLayer.Service;

public class tester {
    public static void main(String args[]){
        Service test = new Service();
        //System.out.println(test.getAllTimecards(2));
        System.out.println(test.insertDepartments("{\"company\":\"cnd9351\",\"dept_name\":\"IT\",\"dept_no\":\"d11\",\"location\":\"rochester\"}"));
        System.out.println("here");
    }
}
