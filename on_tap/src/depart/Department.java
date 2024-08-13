package depart;

import data.Data;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Department {
     Scanner sc = new Scanner(System.in);
    private  int departId;
    private  String departName;
    private  String description;
    private  String phone;
    private  Boolean status;

    public Department() {
    }

    public Department(int departId, String departName, String description, String phone, Boolean status) {
        this.departId = departId;
        this.departName = departName;
        this.description = description;
        this.phone = phone;
        this.status = status;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    //
    public void inputData(Scanner sc, Department[] arrdepartment, int index) {
       this.departId = inputDepartId(arrdepartment, index);
       this.departName = inputDepartName(arrdepartment, index);
       this.description= inputDescription(sc);
       this.phone = inputPhone(sc, arrdepartment, index);
       this.status = inputStatus(arrdepartment, index);

    }
    public void displayData(){
        System.out.println("---------------------------------------------------------");
        System.out.printf("| %-3d | %-10s | %-10s | %-12s | %-6s |\n",this.departId,this.departName,this.description,this.phone,this.status);
//        System.out.println("Department:");
//        System.out.println("Id: " + departId);
//        System.out.println("Name: " + departName);
//        System.out.println("Description: " + description);
//        System.out.println("Phone: " + phone);
//        System.out.println("Status: " + status);

    }

    private  Boolean inputStatus(Department[] arrdepartment, int index) {
        System.out.println("Enter Department status (true or false): ");
        do {
            String departmentStatus = sc.nextLine();
            if((departmentStatus.equals("true") || departmentStatus.equals("false"))) {
                return Boolean.parseBoolean(departmentStatus);
            } else
                System.out.println("Department status must be true or false");
        }while(true);
    }

    private  String inputPhone(Scanner sc, Department[] arrdepartment, int index) {
        System.out.println("Enter phone number");
        Pattern p = Pattern.compile("^0[135789]\\d{7,8}$");
        do {
            String phone = sc.nextLine();
            if (!p.matcher(phone).find()) {
                System.out.println("Invalid phone number");
            } else if (isExistedPhone(arrdepartment, index)) {
                System.out.println("Phone number already exists");
            } else
                return phone;
        }while(true);
    }

    private  boolean isExistedPhone(Department[] arrayDepartment, int index) {
        if (index==-1)
            return false;
        else {
            for (int i = 0; i <= index; i++) {
                if(Data.arrayDepartment[i].getPhone().equals(phone))
                    return true;
            }
        }
        return false;
    }

    private  String inputDescription(Scanner sc) {
        System.out.println("Enter description: ");
        do {
            String description = sc.nextLine();
            if (description.length()<5) {
                System.out.println("Description must be more than 5 characters!");
            } else
                return description;
        }while (true);
    }

    private  String inputDepartName(Department[] arrayDepartment, int index) {
        System.out.println("Enter department name: ");
        Pattern pattern = Pattern.compile("^[DP]\\w{5,}");
        do {
            String name = sc.nextLine();
            if(!pattern.matcher(name).find()) {
                System.out.println("Department name is not in format!");
            }else if(isExistedDepartName(name, arrayDepartment, index))
                System.out.println("Department already exists!");
            else
                return name;
        }while (true);
    }

    private  boolean isExistedDepartName(String name, Department[] arrayDepartment, int index) {
        if(index == -1)
            return false;
        else {
            for(int i = 0; i < index+1; i++) {
                if(Data.arrayDepartment[i].getDepartName().equalsIgnoreCase(name))
                    return true;
            }
        }
        return false;
    }

    private  int inputDepartId(Department[] arrayDepartment, int index) {
        if (index == -1) {
           return 1;
        } else
            return Data.arrayDepartment[index].getDepartId()+1;

    }

    public void inputDataUpdate(Scanner scanner, Department[] arrayDepartment, int currentDepartmentIndex) {
        this.departName = inputDepartName(arrayDepartment, currentDepartmentIndex);
        this.description= inputDescription(sc);
        this.phone = inputPhone(sc, arrayDepartment, currentDepartmentIndex);
        this.status = inputStatus(arrayDepartment, currentDepartmentIndex);
    }
}
