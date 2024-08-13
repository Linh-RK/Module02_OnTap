package depart;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Employee {
    private String empId;
    private String fullName;
    private Boolean gender;
    private Date birthday;
    private String address;
    private int departId;
    private Double salary;
    Scanner sc = new Scanner(System.in);


    public Employee() {}

    public Employee(String empId, String fullName, Boolean gender, Date birthday, String address, int departId, Double salary) {
        this.empId = empId;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.departId = departId;
        this.salary = salary;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void inputData (Scanner sc, Employee[] arrEmployee, int index, Department[] arrDepartment, int currentDepartmentIndex) {
        this.empId =inputEmpID(sc, arrEmployee, index);
        this.fullName =inputFullName(sc, arrEmployee);
        this.gender= inputGender(sc, arrEmployee, index);
        this.birthday = inputBirthday(sc);
        this.address = inputAddress(sc, arrEmployee, index);
        this.departId = inputDepartId(sc, arrDepartment, currentDepartmentIndex);
        this.salary= inputSalary(sc, arrEmployee, index);
    }
    public void displayData(){
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-7s | %-12s | %-10s | %-3d | %-9.2f |\n",this.empId,this.fullName,this.gender,this.birthday,this.address,this.departId,this.salary);
    }
    private Double inputSalary(Scanner sc, Employee[] arrEmployee, int index) {
        System.out.println("Enter salary");
        do {
            try{
                double salary = Double.parseDouble(sc.nextLine());
                if (salary < 0) {
                    System.out.println("Salary must be greater than 0");
                } else
                    return salary;
            }
            catch (Exception e) {
                System.out.println("Salary must be double");
            }
        }while(true);

    }

    private int inputDepartId(Scanner sc, Department[] arrDepartment, int currentDepartmentIndex) {
        System.out.println("Please enter the department ID choose inside list");
        for (int i = 0; i <= currentDepartmentIndex; i++) {
            arrDepartment[i].displayData();
        }
        do {
            try{
                int depIdInt = Integer.parseInt(sc.nextLine());
                if (!isExistedDepartId(depIdInt, arrDepartment, currentDepartmentIndex)) {
                    System.out.println("Department ID does not exist");
                } else
                    return depIdInt;
            }
            catch (Exception e){
                System.out.println("Department ID must be positive integer");
            }
        }while (true);
        
    }

    private boolean isExistedDepartId(int depIdInt, Department[] arrDepartment, int currentDepartmentIndex) {
        if(currentDepartmentIndex ==-1) {
            return false;
        } else{
            for (int i = 0; i <= currentDepartmentIndex; i++) {
                if(arrDepartment[i].getDepartId() == depIdInt){
                    return true;
                }
            }
        }
        return false;
    }

    private String inputAddress(Scanner sc, Employee[] arrEmployee, int index) {
        System.out.println("Enter address: ");
        do {
            String address = sc.nextLine();
            if(address.length() < 2)
                System.out.println("Address must be greater than 2 characters!");
            else 
                return address;
        }while(true);
    }
    private Date inputBirthday(Scanner sc) {
        System.out.println("Enter birthday");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        do {
            try{
                return formatter.parse(sc.nextLine());
            }
            catch (Exception e) {
                System.out.println(" Birthday not in format DD-MM-YYYY");
            }
        }while(true);
    }
    private Boolean inputGender(Scanner sc, Employee[] arrEmployee, int index) {
        System.out.println("Enter employee gender: ");
        do {
            String gender = sc.nextLine();
            if((gender.equals("true") || gender.equals("false"))) {
                return Boolean.parseBoolean(gender);
            } else
                System.out.println("Gender must be true or false");
        }while(true);
    }

    private String inputFullName(Scanner sc, Employee[] arrEmployee) {
        System.out.println("Enter Employee Full Name: ");
        do {
            String fullName = sc.nextLine();
            if(fullName.length()<2) {
                System.out.println("Employee Full Name must be at least 2 characters!: ");
            } else
                return fullName;
        }while (true);

    }

    private String inputEmpID(Scanner sc, Employee[] arrEmployee, int index) {
        System.out.println("Enter employee ID");
        Pattern pattern = Pattern.compile("^E\\w{3}");
        do {
            String empId = sc.nextLine();
            if (!pattern.matcher(empId).find()) {
                System.out.println("Employee ID is not in format");
            } else if(isExistedEmpId(empId, arrEmployee,index)){
                System.out.println("Employee ID already exists");
            } else
                return empId;

        }while(true);
    }

    private boolean isExistedEmpId(String empId, Employee[] arrEmployee, int index) {
        if(index== -1)
            return false;
        else {
        for (int i = 0; i <= index; i++) {
            if(empId.equalsIgnoreCase(arrEmployee[i].getEmpId())){
                return true;
            }
        }
        }
        return false;
    }


    public void inputDataUpdate(Scanner scanner, Employee[] arrayEmployee, int currentEmployeeIndex, Department[] arrayDepartment, int currentDepartmentIndex) {
        this.fullName = inputFullName(scanner, arrayEmployee);
        this.gender= inputGender(scanner, arrayEmployee, currentEmployeeIndex);
        this.birthday = inputBirthday(scanner);
        this.address = inputAddress(scanner, arrayEmployee, currentEmployeeIndex);
        this.departId = inputDepartId(scanner, arrayDepartment, currentDepartmentIndex);
        this.salary= inputSalary(scanner, arrayEmployee, currentEmployeeIndex);
    }
}
