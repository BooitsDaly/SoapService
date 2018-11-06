package BuisnessLayer;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import companydata.DataLayer;
import companydata.Employee;
import companydata.Timecard;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Buisness{

    public String deleteAllCompany(String companyName){
        DataLayer dl = null;
        try{
            String json;
            dl = new DataLayer("development");
            int response = dl.deleteCompany(companyName);
            json = "{\"Success\":\" "+ companyName +" was deleted\"}";
            return json;
        }catch(Exception e){
            return "{\"error\":\" "+ companyName +" was not deleted\"}";
        }finally {
            dl.close();
        }
    }

    public String getDepartmentsByName(String dept){
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            Gson gson = new Gson();
            Department department = gson.fromJson(dept, Department.class );
            companydata.Department response = dl.getDepartment(department.getCompany(), department.getDepartmentID());
            String responseString = "{\"department\":{\"dept_id\":"+ response.getId() +"\",\"company\":\"" + response.getCompany() + "\",\"dept_name\":\""+ response.getDeptName() +"\",\"dept_no\": \""+ response.getDeptNo() +"\",\"location\":\""+ response.getLocation() + "\"}}";
            return responseString;
        }catch(Exception e){
            return "{\"error\":\" Department was not found \"}";
        }finally{
            dl.close();
        }
    }

    public String getDepartmentsByCompany(String company){
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            List<companydata.Department> response = dl.getAllDepartment(company);
            String responseString = "[{\"department\":{";
            for(int i = 0; i < response.size(); i++){
                responseString +="{\"dept_id\":"+ response.get(i).getId() +"\",\"company\":\""+ response.get(i).getCompany() +"\",\"dept_name\":\""+response.get(i).getDeptNo()+"\", \"dept_no\":\""+ response.get(i).getDeptNo() +"\",\"location\": \""+ response.get(i).getLocation() +"\" }";
                if(i != response.size()-1){
                    responseString +=",";
                }
            }
            responseString += "}]";
            return responseString;
        }catch(Exception e){
            return "{\"error\":\" Departments not found \"}";
        }finally{
            dl.close();
        }
    }

    public String insertDepartmentToData(String dept){
        DataLayer dl = null;
        String responseString = "";
        try{
            dl = new DataLayer("development");
            Gson gson = new Gson();
            Department department = gson.fromJson(dept,Department.class);
            companydata.Department test = new companydata.Department(department.getDepartmentID(),department.getCompany(),department.getDept_name(),department.getDept_no(),department.getLocation());
            companydata.Department response = dl.insertDepartment(test);
            responseString = "{\"success\":{\"department\":{\"company\":\"" + response.getCompany() + "\",\"dept_name\":\""+ response.getDeptName() +"\",\"dept_no\": \""+ response.getDeptNo() +"\",\"location\":\""+ response.getLocation() + "\"}}}";
            return responseString;
        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to add the department\"}";
        }finally{
            dl.close();
        }

    }

    public String updateDepartmentToData(String dept){
        DataLayer dl = null;
        String responseString = "";
        try{
            dl = new DataLayer("development");
            Gson gson = new Gson();
            Department department = gson.fromJson(dept, Department.class);
            companydata.Department test = new companydata.Department(department.getDepartmentID(),department.getCompany(),department.getDept_name(),department.getDept_no(),department.getLocation());
            companydata.Department response = dl.updateDepartment(test);
            responseString = "{\"success\":{\"department\":{\"dept_id\":\""+ response.getId() +"\",\"company\":\"" + response.getCompany() + "\",\"dept_name\":\""+ response.getDeptName() +"\",\"dept_no\": \""+ response.getDeptNo() +"\",\"location\":\""+ response.getLocation() + "\"}}}";
            return responseString;
        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to update the department  \"}";
        }finally{
            dl.close();
        }
    }

    public String deleteDepartmentFromData(String dept){
        DataLayer dl = null;
        String responseString = "";
        try{
            dl = new DataLayer("development");
            Gson gson = new Gson();
            Department department = gson.fromJson(dept, Department.class);
            dl.deleteDepartment(department.getCompany(),department.getDepartmentID());
            responseString = "{\"success\":\" Department "+ department.getDepartmentID() +" from " +department.getCompany() + " \"}";
            return responseString;
        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to delete the department  \"}";
        }finally{
            dl.close();
        }
    }

    public String getEmployeeById(int id){
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            Employee response = dl.getEmployee(id);
            return "{\"employee\":{\"emp_id\":"+ response.getId() +"\",\"emp_name\":\""+ response.getEmpName() +"\",\"emp_no\":\""+response.getEmpNo()+"\", \"hire_date\":\""+ response.getHireDate() +"\",\"job\": \""+ response.getJob() +"\",\"salary\":"+ response.getSalary() +", \"dept_id\": "+ response.getDeptId() +", \"mng_id\": "+ response.getMngId() +"}}";

        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to retrieve employee information \"}";
        }finally{
            dl.close();
        }
    }

    public String getAllEmployees(String company){
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            List<Employee> response = dl.getAllEmployee(company);
            String responseString = "[{\"employee\":{";
            for(int i = 0; i < response.size(); i++){
                responseString +="{\"emp_id\":"+ response.get(i).getId() +"\",\"emp_name\":\""+ response.get(i).getEmpName() +"\",\"emp_no\":\""+response.get(i).getEmpNo()+"\", \"hire_date\":\""+ response.get(i).getHireDate() +"\",\"job\": \""+ response.get(i).getJob() +"\",\"salary\":"+ response.get(i).getSalary() +", \"dept_id\": "+ response.get(i).getDeptId() +", \"mng_id\": "+ response.get(i).getMngId() +" }";
                if(i != response.size()-1){
                    responseString +=",";
                }
            }
            responseString += "}]";
            return responseString;
        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to retrieve employees information \"}";
        }finally{

            dl.close();
        }
    }

    public String insertEmployeeasData(String employee){
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            String responseString = "";
            boolean iii = false;
            boolean iv = false;
            boolean v = true;
            /**
             * i.	dept_id must exist as a Department in your company
             * ii.	mng_id must be the record id of an existing Employee in your company.
             * iii.	hire_date must be a valid date equal to the current date or earlier (e.g. current date or in the past)
             * iv.	hire_date must be a Monday, Tuesday, Wednesday, Thursday or a Friday. It cannot be Saturday or Sunday.
             * v.	emp_id must be unique amongst all employees in the database, including those of other companies. You may wish to include your RIT user ID in the employee number somehow.
             */
            Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            Employee emp = gson.fromJson(employee, Employee.class);
            //department id must exist as a department in company
            dl.getDepartment(emp.getEmpNo(), emp.getDeptId());
            //mng_id must be a record id of an existing employee
            dl.getEmployee(emp.getId());
            List<Employee> allEmployees = dl.getAllEmployee(emp.getEmpNo());
            for (Employee allEmployee : allEmployees) {
                if(allEmployee.getId() == emp.getMngId()){
                    v = false;
                    break;
                }
            }
            Date timeStamp = Calendar.getInstance().getTime();
            Date inputDate = emp.getHireDate();
            if(timeStamp.equals(inputDate) || timeStamp.after(inputDate)){
                iii = true;
            }
            if(inputDate.getDay() != Calendar.SATURDAY && inputDate.getDay() != Calendar.SUNDAY){
                iv = true;
            }
            if(iii && iv && v){
                Employee response = dl.insertEmployee(emp);
                responseString ="{\"employee\":{\"emp_id\":"+ response.getId() +"\",\"emp_name\":\""+ response.getEmpName() +"\",\"emp_no\":\""+response.getEmpNo()+"\", \"hire_date\":\""+ response.getHireDate() +"\",\"job\": \""+ response.getJob() +"\",\"salary\":"+ response.getSalary() +", \"dept_id\": "+ response.getDeptId() +", \"mng_id\": "+ response.getMngId() +"}}";

            }else{
                responseString = "{\"error\":\" An error occurred while trying to insert employees information \"}";
            }
            return responseString;
        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to insert employees information \"}";
        }finally {
            dl.close();
        }
    }

    public String updateEmployeeInData(String employee){
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            String responseString = "";
            boolean iii = false;
            boolean iv = false;
            /**
             * i.	dept_id must exist as a Department in your company
             * ii.	mng_id must be the record id of an existing Employee in your company.
             * iii.	hire_date must be a valid date equal to the current date or earlier (e.g. current date or in the past)
             * iv.	hire_date must be a Monday, Tuesday, Wednesday, Thursday or a Friday. It cannot be Saturday or Sunday.
             * v.	emp_id must already exist
             */
            Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            Employee emp = gson.fromJson(employee, Employee.class);
            //department id must exist as a department in company
            dl.getDepartment(emp.getEmpNo(), emp.getDeptId());
            dl.getEmployee(emp.getId());
            dl.getEmployee(emp.getMngId());
            //mng_id must be a record id of an existing employee
            Date timeStamp = Calendar.getInstance().getTime();
            Date inputDate = emp.getHireDate();
            if(timeStamp.equals(inputDate) || timeStamp.after(inputDate)){
                iii = true;
            }
            if(inputDate.getDay() != Calendar.SATURDAY && inputDate.getDay() != Calendar.SUNDAY){
                iv = true;
            }
            if(iii && iv){
                Employee response = dl.insertEmployee(emp);
                responseString ="{\"employee\":{\"emp_id\":"+ response.getId() +"\",\"emp_name\":\""+ response.getEmpName() +"\",\"emp_no\":\""+response.getEmpNo()+"\", \"hire_date\":\""+ response.getHireDate() +"\",\"job\": \""+ response.getJob() +"\",\"salary\":"+ response.getSalary() +", \"dept_id\": "+ response.getDeptId() +", \"mng_id\": "+ response.getMngId() +"}}";

            }else{

                responseString = "{\"error\":\" An error occurred while trying to update employees information \"}";
            }
            return responseString;
        }catch(Exception e){

            return "{\"error\":\" An error occurred while trying to change employees information \"}";
        }finally {
            dl.close();
        }
    }
    
    public String deleteEmployeeInData(int emp_id){
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            Employee employee = dl.getEmployee(emp_id);
            List<Employee> employees = dl.getAllEmployee(employee.getEmpNo());
            System.out.println(employees.size());
            for(Employee person : employees){
                System.out.println("here");
                if(person.getMngId() == employee.getId()){
                    System.out.println("here");
                    this.deleteEmployeeInData(person.getId());
                }
            }
            dl.deleteEmployee(emp_id);
            return "{\"success\":\"Employee "+ emp_id +" deleted \"}";
        }catch(Exception e){
            System.out.println(e);
            return "{\"error\":\" An error occurred while trying to delete employees information \"}";
        }finally{
            dl.close();
        }
    }

    public String getTimecardsInData(int timecard_id){
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            Timecard result = dl.getTimecard(timecard_id);
            return "{\"timecard\":{\"timecard_id\": "+ result.getId() +", \"start_time\":\""+result.getStartTime()+"\",\"end_time\":\""+ result.getEndTime() +"\", \"emp_id\":"+ result.getEmpId() +"}}";
        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to get timecard information \"}";
        }finally{
            dl.close();
        }
    }

    public String getAllTimecardsInData(int timecard_id){
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            List<Timecard> result = dl.getAllTimecard(timecard_id);
            String responseString = "[{";
            int i = 0;
            for(Timecard timcard: result){
                responseString += "{\"timecard\":{\"timecard_id\": "+ timcard.getId() +", \"start_time\":\""+timcard.getStartTime()+"\",\"end_time\":\""+ timcard.getEndTime() +"\", \"emp_id\":"+ timcard.getEmpId() +"}}";
                if(result.size() - 1 != i){
                    responseString += ",";
                }
                i++;
            }
            responseString += "}]";
            return responseString;
        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to get timecard information \"}";
        }finally{
            dl.close();
        }
    }

    public String insertTimecardInData(String timecard){
        /**
         * i.	emp_id must exist as the record id of an Employee in your company.
         * ii.	start_time must be a valid date and time equal to the current date or up to 1 week ago from the current date.
         * iii.	end_time must be a valid date and time at least 1 hour greater than the start_time and be on the same day as the start_time.
         * iv.	start_time and end_time must be a Monday, Tuesday, Wednesday, Thursday or a Friday. They cannot be Saturday or Sunday.
         * v.	start_time and end_time must be between the hours (in 24 hour format) of 06:00:00 and 18:00:00 inclusive.
         * vi.	start_time must not be on the same day as any other start_time for that employee.
         */
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            String resultString = "";
            boolean ii = false;
            boolean iii = false;
            boolean iv = false;
            boolean v = false;
            boolean vi = true;

            Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            Timecard time = gson.fromJson(timecard, Timecard.class);
            //check 1
            Timecard check1 = dl.getTimecard(time.getId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timeStamp = Calendar.getInstance().getTime();
            sdf.setLenient(false);
            Date startTime = sdf.parse(String.valueOf(time.getStartTime()));
            Date endTime = sdf.parse(String.valueOf(time.getEndTime()));
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);

            //check 2
            if(timeStamp.after(startTime) || timeStamp.equals(startTime)){
                Calendar c=Calendar.getInstance();
                c.add(Calendar.DATE, -7);
                if(c.getTime().before(timeStamp)){
                    ii = true;
                }
            }
            //check 3
            if(startTime.getDay() == (endTime.getDay())){
                Calendar c = Calendar.getInstance();
                c.add(Calendar.HOUR, 1);
                if(startTime.getTime() < (endTime.getTime())){
                    iii = true;
                }
            }
            //check 4
            if(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
                cal.setTime(endTime);
                if(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
                    iv = true;
                }
            }

            Calendar startWorkday = Calendar.getInstance();
            startWorkday.set(Calendar.HOUR_OF_DAY,6);
            startWorkday.set(Calendar.MINUTE,0);
            startWorkday.set(Calendar.SECOND,0);
            Calendar endWorkday = Calendar.getInstance();
            endWorkday.set(Calendar.HOUR_OF_DAY,18);
            endWorkday.set(Calendar.MINUTE,0);
            endWorkday.set(Calendar.SECOND,0);
            Date startWork = startWorkday.getTime();
            Date endWork = endWorkday.getTime();
            //check 5
            if(startTime.getHours() >= startWork.getHours() && endTime.getHours() <= endWork.getHours()){
                v = true;
            }


            List<Timecard> allTimecards = dl.getAllTimecard(time.getId());
            //check 6
            for (Timecard timecards : allTimecards){
                if(timecards.getStartTime() == time.getStartTime()){
                    vi = false;
                }
            }

            if(ii && iii && iv && v && vi){
                Timecard result = dl.insertTimecard(time);
                return "{\"timecard\":{\"timecard_id\": "+ result.getId() +", \"start_time\":\""+result.getStartTime()+"\",\"end_time\":\""+ result.getEndTime() +"\", \"emp_id\":"+ result.getEmpId() +"}}";
            }else{
                return "{\"error\":\" An error occurred while trying to insert timecard information \"}";
            }
        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to insert timecard information \"}";
        }finally{
            dl.close();
        }

    }

    public String updateTimecardInData(String timecard){
        /**
         * i.	emp_id must exist as the record id of an Employee in your company.
         * ii.	start_time must be a valid date and time equal to the current date or up to 1 week ago from the current date.
         * iii.	end_time must be a valid date and time at least 1 hour greater than the start_time and be on the same day as the start_time.
         * iv.	start_time and end_time must be a Monday, Tuesday, Wednesday, Thursday or a Friday. They cannot be Saturday or Sunday.
         * v.	start_time and end_time must be between the hours (in 24 hour format) of 06:00:00 and 18:00:00 inclusive.
         * vi.	start_time must not be on the same day as any other start_time for that employee.
         */
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            String resultString = "";
            boolean ii = false;
            boolean iii = false;
            boolean iv = false;
            boolean v = false;
            boolean vi = true;

            Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            Timecard time = gson.fromJson(timecard, Timecard.class);
            //check 1
            Timecard check1 = dl.getTimecard(time.getId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timeStamp = Calendar.getInstance().getTime();
            sdf.setLenient(false);
            Date startTime = sdf.parse(String.valueOf(time.getStartTime()));
            Date endTime = sdf.parse(String.valueOf(time.getEndTime()));
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);

            //check 2
            if(timeStamp.after(startTime) || timeStamp.equals(startTime)){
                Calendar c=Calendar.getInstance();
                c.add(Calendar.DATE, -7);
                if(c.getTime().before(timeStamp)){
                    ii = true;
                }
            }
            //check 3
            if(startTime.getDay() == (endTime.getDay())){
                Calendar c = Calendar.getInstance();
                c.add(Calendar.HOUR, 1);
                if(startTime.getTime() < (endTime.getTime())){
                    iii = true;
                }
            }
            //check 4
            if(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
                cal.setTime(endTime);
                if(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
                    iv = true;
                }
            }

            Calendar startWorkday = Calendar.getInstance();
            startWorkday.set(Calendar.HOUR_OF_DAY,6);
            startWorkday.set(Calendar.MINUTE,0);
            startWorkday.set(Calendar.SECOND,0);
            Calendar endWorkday = Calendar.getInstance();
            endWorkday.set(Calendar.HOUR_OF_DAY,18);
            endWorkday.set(Calendar.MINUTE,0);
            endWorkday.set(Calendar.SECOND,0);
            Date startWork = startWorkday.getTime();
            Date endWork = endWorkday.getTime();
            //check 5
            if(startTime.getHours() >= startWork.getHours() && endTime.getHours() <= endWork.getHours()){
                v = true;
            }


            List<Timecard> allTimecards = dl.getAllTimecard(time.getId());
            //check 6
            for (Timecard timecards : allTimecards){
                if(timecards.getStartTime() == time.getStartTime()){
                    vi = false;
                }
            }
            dl.getTimecard(time.getId());

            if(ii && iii && iv && v && vi){
                Timecard result = dl.updateTimecard(time);
                return "{\"timecard\":{\"timecard_id\": "+ result.getId() +", \"start_time\":\""+result.getStartTime()+"\",\"end_time\":\""+ result.getEndTime() +"\", \"emp_id\":"+ result.getEmpId() +"}}";
            }else{
                return "{\"error\":\" An error occurred while trying to update timecard information \"}";
            }
        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to update timecard information \"}";
        }finally{
            dl.close();
        }

    }

    public String deleteTimecardfromData(int timecardID){
        DataLayer dl = null;
        try{
            dl = new DataLayer("development");
            int response = dl.deleteTimecard(timecardID);
            return "{\"success\":\" Timecard "+ timecardID +"  deleted \"}";
        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to delete timecard information \"}";
        }finally {
            dl.close();
        }
    }
}
