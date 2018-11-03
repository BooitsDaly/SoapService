package BuisnessLayer;


import com.google.gson.Gson;
import companydata.DataLayer;
import companydata.Employee;
import companydata.Timecard;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Buisness {

    public String deleteAllCompany(String companyName){
        DataLayer dl = null;
        try{
            String json;
            dl = new DataLayer("development");
            int response = dl.deleteCompany(companyName);
            if(response > 0){
                json = "{\"Success\":\" "+ companyName +" was deleted\"}";
            }else{
                json = "{\"error\":\" "+ companyName +" was not deleted\"}";
            }
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
            companydata.Department response = dl.getDepartment(department.getDepartment(), department.getDepartmentID());
            String responseString =response.toString();
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
            String responseString = response.toString();
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
            System.out.println("got in the try");
            System.out.println(dept);
            Department department = gson.fromJson(dept, Department.class);
            System.out.println(department);
            //check that the id doesnt match
            List<companydata.Department> departments = dl.getAllDepartment(department.getCompany());
            System.out.println("got in the try");
            for (companydata.Department department1 : departments) {
                if (department1.getDeptNo().equals(department.getDepartmentNumb())) {
                    responseString = "{\"error\":\" Cannot have two department numbers be the same \"}";
                    break;
                }
            }
            if(responseString.equals("")){
                companydata.Department response = dl.insertDepartment(department);
                responseString = response.toString();
            }
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
            List<companydata.Department> departments = dl.getAllDepartment(department.getCompany());
            for (companydata.Department department1 : departments) {
                if(department1.getId() == department.getId() && !(department1.getDeptNo().equals(department.getDepartmentNumb()))){
                    companydata.Department response = dl.updateDepartment(department);
                    responseString = response.toString();
                }else{
                    responseString = "{\"error\":\" Department ID must match exisiting ID, Department number must also be unique  \"}";
                }
            }
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
            dl = new DataLayer("department");
            Gson gson = new Gson();
            Department department = gson.fromJson(dept, Department.class);
            int response = dl.deleteDepartment(department.getCompany(),department.getId());
            if(response < 0){
                responseString = "{\"success\":\" Department "+ department.getDepartmentID() +" from " +department.getCompany() + " \"}";
            }else{
                responseString = "{\"error\":\" An error occurred while trying to delete the department  \"}";
            }
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
            return response.toString();
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
            return response.toString();
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
            boolean i = false;
            boolean ii = false;
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
            Gson gson = new Gson();
            Employee emp = gson.fromJson(employee, Employee.class);
            //department id must exist as a department in company
            Department result = (Department) dl.getDepartment(emp.getEmpNo(),emp.getDeptId());
            if(result.getDepartment() != null){
                i = true;
            }
            //mng_id must be a record id of an existing employee
            List<Employee> allEmployees = dl.getAllEmployee(emp.getEmpNo());
            for (Employee allEmployee : allEmployees) {
                if (allEmployee.getMngId() == emp.getMngId()) {
                    ii = true;
                }else if(allEmployee.getId() == emp.getId()){
                    v = false;
                    break;
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timeStamp = Calendar.getInstance().getTime();
            Date inputDate = emp.getHireDate();
            if(timeStamp.equals(inputDate) || timeStamp.after(inputDate)){
                iii = true;
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(timeStamp);
            if(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
                iv = true;
            }
            if(i && ii && iii && iv && v){
                responseString = dl.insertEmployee(emp).toString();
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
            boolean i = false;
            boolean ii = false;
            boolean iii = false;
            boolean iv = false;
            boolean v = false;
            /**
             * i.	dept_id must exist as a Department in your company
             * ii.	mng_id must be the record id of an existing Employee in your company.
             * iii.	hire_date must be a valid date equal to the current date or earlier (e.g. current date or in the past)
             * iv.	hire_date must be a Monday, Tuesday, Wednesday, Thursday or a Friday. It cannot be Saturday or Sunday.
             * v.	emp_id must already exist
             */
            //TODO: Ask about the validation of the emp_id
            Gson gson = new Gson();
            Employee emp = gson.fromJson(employee, Employee.class);
            //department id must exist as a department in company
            Department result = (Department) dl.getDepartment(emp.getEmpNo(),emp.getDeptId());
            if(result.getDepartment() != null){
                i = true;
            }
            //mng_id must be a record id of an existing employee
            List<Employee> allEmployees = dl.getAllEmployee(emp.getEmpNo());
            for (Employee allEmployee : allEmployees) {
                if (allEmployee.getMngId() == emp.getMngId()) {
                    ii = true;
                }else if(allEmployee.getId() == emp.getId()){
                    v = true;
                    break;
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timeStamp = Calendar.getInstance().getTime();
            Date inputDate = emp.getHireDate();
            if(timeStamp.equals(inputDate) || timeStamp.after(inputDate)){
                iii = true;
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(timeStamp);
            if(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
                iv = true;
            }
            if(i && ii && iii && iv && v){
                responseString = dl.updateEmployee(emp).toString();
            }else{
                responseString = "{\"error\":\" An error occurred while trying to change employees information \"}";
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
            int result = dl.deleteEmployee(emp_id);
            return "{\"success\":\"Employee "+ result +" deleted \"}";
        }catch(Exception e){
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
            return result.toString();
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
            return result.toString();
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
            boolean i = false;
            boolean ii = false;
            boolean iii = false;
            boolean iv = false;
            boolean v = false;
            boolean vi = true;

            Gson gson = new Gson();
            Timecard time = gson.fromJson(timecard, Timecard.class);
            //check 1
            Timecard check1 = dl.getTimecard(time.getId());
            if(check1.getId() != 0){
                i = true;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timeStamp = Calendar.getInstance().getTime();
            sdf.setLenient(false);
            Date startTime = sdf.parse(String.valueOf(time.getStartTime()));
            Date endTime = sdf.parse(String.valueOf(time.getEndTime()));
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            Calendar startWorkday = Calendar.getInstance();
            startWorkday.set(Calendar.HOUR_OF_DAY,6);
            startWorkday.set(Calendar.MINUTE,0);
            startWorkday.set(Calendar.SECOND,0);
            Calendar endWorkday = Calendar.getInstance();
            endWorkday.set(Calendar.HOUR_OF_DAY,6);
            endWorkday.set(Calendar.MINUTE,0);
            endWorkday.set(Calendar.SECOND,0);
            List<Timecard> allTimecards = dl.getAllTimecard(time.getId());

            //check 2
            if(timeStamp.after(startTime) || timeStamp.equals(startTime)){
                Calendar c=Calendar.getInstance();
                c.add(Calendar.DATE, -7);
                if(c.getTime().before(timeStamp)){
                    ii = true;
                }
            }
            //check 3
            if(startTime.equals(endTime)){
                Calendar c = Calendar.getInstance();
                c.add(Calendar.HOUR, 1);
                if(c.getTime().before(endTime)){
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

            //check 5
            if(startTime.after(startWorkday.getTime()) && endTime.before(endWorkday.getTime())){
                v = true;
            }

            //check 6
            for (Timecard timecards : allTimecards){
                if(timecards.getStartTime() == time.getStartTime()){
                    vi = false;
                }
            }

            if(i && ii && iii && iv && v && vi){
                Timecard result = dl.insertTimecard(time);
                return result.toString();
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
            boolean i = false;
            boolean ii = false;
            boolean iii = false;
            boolean iv = false;
            boolean v = false;
            boolean vi = true;

            Gson gson = new Gson();
            Timecard time = gson.fromJson(timecard, Timecard.class);
            //check 1
            Timecard check1 = dl.getTimecard(time.getId());
            if(check1.getId() != 0){
                i = true;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timeStamp = Calendar.getInstance().getTime();
            sdf.setLenient(false);
            Date startTime = sdf.parse(String.valueOf(time.getStartTime()));
            Date endTime = sdf.parse(String.valueOf(time.getEndTime()));
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            Calendar startWorkday = Calendar.getInstance();
            startWorkday.set(Calendar.HOUR_OF_DAY,6);
            startWorkday.set(Calendar.MINUTE,0);
            startWorkday.set(Calendar.SECOND,0);
            Calendar endWorkday = Calendar.getInstance();
            endWorkday.set(Calendar.HOUR_OF_DAY,6);
            endWorkday.set(Calendar.MINUTE,0);
            endWorkday.set(Calendar.SECOND,0);
            List<Timecard> allTimecards = dl.getAllTimecard(time.getId());

            //check 2
            if(timeStamp.after(startTime) || timeStamp.equals(startTime)){
                Calendar c=Calendar.getInstance();
                c.add(Calendar.DATE, -7);
                if(c.getTime().before(timeStamp)){
                    ii = true;
                }
            }
            //check 3
            if(startTime.equals(endTime)){
                Calendar c = Calendar.getInstance();
                c.add(Calendar.HOUR, 1);
                if(c.getTime().before(endTime)){
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

            //check 5
            if(startTime.after(startWorkday.getTime()) && endTime.before(endWorkday.getTime())){
                v = true;
            }

            //check 6
            for (Timecard timecards : allTimecards){
                if(timecards.getStartTime() == time.getStartTime()){
                    vi = false;
                }
            }

            if(i && ii && iii && iv && v && vi){
                Timecard result = dl.insertTimecard(time);
                return result.toString();
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
            return "{\"success\":\" Timecard "+ response +"  deleted \"}";
        }catch(Exception e){
            return "{\"error\":\" An error occurred while trying to delete timecard information \"}";
        }finally {
            dl.close();
        }
    }
}
