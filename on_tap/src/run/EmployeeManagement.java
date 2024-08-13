package run;

import data.Data;
import depart.Department;
import depart.Employee;

import java.util.Scanner;

import static run.Main.inputChoice;

public class EmployeeManagement {
    public void employeeManagement(Scanner scanner) {
        boolean flag = true;
        int choice;
        do {
            System.out.println("---------------------------EMPLOYEE MANAGEMENT---------------------------");
            System.out.println("            1. Add Employee                                              ");
            System.out.println("            2. Display Employee Information                              ");
            System.out.println("            3. Display Employee by Descending Salary                     ");
            System.out.println("            4. Update Employee Information by Employee ID                ");
            System.out.println("            5. Delete Employee by Employee ID                            ");
            System.out.println("            6. Search Employee by Department Name                        ");
            System.out.println("            7. Search Employee by Salary                                 ");
            System.out.println("            8. Exit                                                      ");
            System.out.println("-------------------------------------------------------------------------");
            choice = inputChoice(scanner);
            switch (choice) {
                case 1:
                    addEmployee(scanner, Data.arrayEmployee, Data.currentEmployeeIndex, Data.arrayDepartment, Data.currentDepartmentIndex);
                    break;
                case 2:
                    displayEmployeeInfo(Data.arrayEmployee, Data.currentEmployeeIndex);
                    break;
                case 3:
                    displayEmployeeByDescendingSalary(Data.arrayEmployee, Data.currentEmployeeIndex);
                    break;
                case 4:
                    updateEmployeeInfoById(scanner, Data.arrayEmployee, Data.currentEmployeeIndex, Data.arrayDepartment, Data.currentDepartmentIndex);
                    break;
                case 5:
                    deleteEmployee(scanner, Data.arrayEmployee, Data.currentEmployeeIndex);
                    break;
                case 6:
                    displayEmployeeByDep(scanner, Data.arrayEmployee, Data.currentEmployeeIndex, Data.arrayDepartment, Data.currentDepartmentIndex);
                    break;
                case 7:
                    displayEmployeeBySalary(scanner, Data.arrayEmployee, Data.currentEmployeeIndex);
                    break;
                case 8:
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter a choice between 1 and 3: ");
            }
        }while(flag);
    }

    private void displayEmployeeBySalary(Scanner scanner, Employee[] arrayEmployee, int currentEmployeeIndex) {
        System.out.println("Enter min salary you want to display: ");
        double minSalary = inputSalary(scanner);
        System.out.println("Enter max salary you want to display: ");
        double maxSalary = inputSalary(scanner);
        System.out.println("List of employees have salary level between " + minSalary + " and " + maxSalary);
        for (int i = 0; i <= currentEmployeeIndex ; i++) {
            if(arrayEmployee[i].getSalary() >= minSalary && arrayEmployee[i].getSalary() <= maxSalary) {
                arrayEmployee[i].displayData();
            }
        }
    }

    private double inputSalary(Scanner scanner) {
        do{
            try{
                double salary = Double.parseDouble(scanner.nextLine());
                if(salary <= 0){
                    System.out.println("Please enter a positive number: ");
                }else {
                    return salary;
                }
            }
            catch  (Exception e) {
                System.err.println("Salary must be double.");
            }
        }
        while (true);
    }

    private void displayEmployeeByDep(Scanner scanner, Employee[] arrayEmployee, int currentEmployeeIndex, Department[] arrayDepartment, int currentDepartmentIndex) {
       DepartmentManagement.displayDepartment(arrayDepartment, currentDepartmentIndex);
        System.out.println("Enter department ID you want to display list employees: ");
        int departmentId = inputDepId(scanner);
            if(!isExistedDepartmentId(departmentId, arrayDepartment, currentDepartmentIndex)){
                System.out.println("This Department does not exist");
            } else {
                for (int i = 0; i <= currentEmployeeIndex; i++) {
                    if(arrayEmployee[i].getDepartId() == departmentId){
                        arrayEmployee[i].displayData();
                    }
                }
            }
    }

    private int inputDepId(Scanner scanner) {
        do {
            try{
                return Integer.parseInt(scanner.nextLine());
            }
            catch(Exception e) {
                System.err.println("Department ID must be an positive integer: ");
            }
        }while(true);
    }

    private boolean isExistedDepartmentId(int departmentId, Department[] arrayDepartment, int currentDepartmentIndex) {
        for (int i = 0; i <= currentDepartmentIndex; i++) {
            if(arrayDepartment[i].getDepartId()== departmentId){
                return true;
            }
        }
        return false;
    }

    private void deleteEmployee(Scanner scanner, Employee[] arrayEmployee, int currentEmployeeIndex) {
        System.out.println("Please enter an employee ID you want to delete: ");
        String employeeIDDelete = scanner.next();
        if(!isExistedEmployeeId(employeeIDDelete, arrayEmployee, currentEmployeeIndex)){
            System.out.println("Employee ID does not exist");
        } else {
            for (int i = 0; i <= currentEmployeeIndex; i++) {
                if(arrayEmployee[i].getEmpId().equalsIgnoreCase(employeeIDDelete)) {
                    for (int j = i; j <= currentEmployeeIndex; j++) {
                        arrayEmployee[j]= arrayEmployee[j+1];
                    }
                    Data.arrayEmployee = arrayEmployee;
                    currentEmployeeIndex--;
                    Data.currentEmployeeIndex = currentEmployeeIndex;
                    break;
                }
            }
            System.out.println("Department deleted successfully");
        }
    }

    private void updateEmployeeInfoById
    (Scanner scanner, Employee[] arrayEmployee, int currentEmployeeIndex,
     Department[] arrayDepartment, int currentDepartmentIndex) {
        System.out.println("Enter Employee ID you want to update: ");
        String employeeIDUpdate = scanner.next();
        if(!isExistedEmployeeId(employeeIDUpdate, arrayEmployee, currentEmployeeIndex)){
            System.out.println("Employee ID does not exist");
        } else {
            Employee employeeUpdate = new Employee();
            employeeUpdate.setEmpId(employeeIDUpdate);
            employeeUpdate.inputDataUpdate(scanner, arrayEmployee, currentEmployeeIndex,arrayDepartment, currentDepartmentIndex);
            for (int i = 0; i <= currentEmployeeIndex; i++) {
                if(arrayEmployee[i].getEmpId().equalsIgnoreCase(employeeIDUpdate)){
                    Data.arrayEmployee[i]= employeeUpdate;
                    System.out.println("Employee ID "+ employeeIDUpdate +" is updated");
                }
            }
        }
    }

    private boolean isExistedEmployeeId(String employeeIDUpdate, Employee[] arrayEmployee, int currentEmployeeIndex) {
        if(currentEmployeeIndex == -1)
            return false;
        else{
            for (int i = 0; i <= currentEmployeeIndex; i++) {
               if(arrayEmployee[i].getEmpId().equalsIgnoreCase(employeeIDUpdate)){
                   return true;
               } else
                   return false;
            }
        }
        return false;
    }

    private void displayEmployeeByDescendingSalary(Employee[] arrayEmployee, int currentEmployeeIndex) {
        for (int i = 0; i <=currentEmployeeIndex ; i++) {
            for (int j = i+1; j <= currentEmployeeIndex; j++) {
                if (arrayEmployee[i].getSalary() < arrayEmployee[j].getSalary()) {
                    Employee temp = arrayEmployee[i];
                    arrayEmployee[i] = arrayEmployee[j];
                    arrayEmployee[j] = temp;
                }
            }
        }
        displayEmployeeInfo(arrayEmployee, currentEmployeeIndex);
    }

    private void displayEmployeeInfo(Employee[] arrayEmployee, int currentEmployeeIndex) {
        System.out.println("Employee Information");
        for (int i = 0; i <= currentEmployeeIndex; i++) {
            arrayEmployee[i].displayData();
        }
    }

    private void addEmployee(Scanner scanner, Employee[] arrayEmployee, int currentEmployeeIndex, Department[] arrayDepartment, int currentDepartmentIndex) {
        int number = inputNumberAdd(scanner);
        for (int i = 1; i <= number; i++) {
            Employee employee = new Employee();
            employee.inputData(scanner,arrayEmployee,currentEmployeeIndex,arrayDepartment,currentDepartmentIndex);
            Data.arrayEmployee[currentEmployeeIndex + 1] = employee;
            currentEmployeeIndex ++;
            Data.currentEmployeeIndex = currentEmployeeIndex;
        }
    }

    private int inputNumberAdd(Scanner scanner) {
        System.out.println("Enter the number of employees you want to add: ");
        do{
            try{
                int number = Integer.parseInt(scanner.nextLine());
                if(number < 0 ) {
                    System.out.println("The number of employees you want to add must be positive integers: ");
                }else
                    return number;
            }
            catch (Exception e){
                System.err.println("The number of employees you want to add must be positive integers: ");
            }
        }
        while(true);
    }
}
