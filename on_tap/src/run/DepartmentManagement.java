package run;

import data.Data;
import depart.Department;

import java.util.Scanner;

import static run.Main.inputChoice;

public class DepartmentManagement {
    public void departmentManagement(Scanner scanner) {
        boolean flag = true;
        int choice;
        do {
            System.out.println("---------------------------DEPARTMENT MANAGEMENT---------------------------");
            System.out.println("            1. Add Department                                              ");
            System.out.println("            2. Display Department                                          ");
            System.out.println("            3. Update Department Information                               ");
            System.out.println("            4. Delete Department                                           ");
            System.out.println("            5. Update Department Status                                    ");
            System.out.println("            6. Exit                                                        ");
            System.out.println("---------------------------------------------------------------------------");
            choice = inputChoice(scanner);
            switch (choice) {
                case 1:
                    addDepartment(scanner, Data.arrayDepartment, Data.currentDepartmentIndex);
                    break;
                case 2:
                    displayDepartment(Data.arrayDepartment, Data.currentDepartmentIndex);
                    break;
                case 3:
                    updateDepartmentInfo(scanner, Data.arrayDepartment, Data.currentDepartmentIndex);
                    break;
                case 4:
                    deleteDepartment(scanner, Data.arrayDepartment, Data.currentDepartmentIndex);
                    break;
                case 5:
                    updateDepartmentStatus(scanner, Data.arrayDepartment, Data.currentDepartmentIndex);
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter a choice between 1 and 3: ");
            }
        }while (flag) ;
    }

    private void updateDepartmentStatus(Scanner scanner, Department[] arrayDepartment, int currentDepartmentIndex) {
        System.out.println("Enter Department ID you want to update status: ");
        int idUpdateStatus= inputId(scanner);
        if (!isExistedDepartmentId(idUpdateStatus, arrayDepartment, currentDepartmentIndex)) {
            System.out.println("This ID Department does not exist");
        } else {
            for (int i = 0; i <= currentDepartmentIndex; i++) {
               if(arrayDepartment[i].getDepartId() == idUpdateStatus) {
                   arrayDepartment[i].setStatus(!arrayDepartment[i].getStatus());
                   Data.arrayDepartment = arrayDepartment;
                   break;
               }
            }
            System.out.println("Department Status Updated");
            displayDepartment(arrayDepartment, currentDepartmentIndex);
        }
    }


    private void deleteDepartment(Scanner scanner, Department[] arrayDepartment, int currentDepartmentIndex) {
        System.out.println("Enter Department ID you want to delete: ");
        int idDelete = inputId(scanner);
        if (!isExistedDepartmentId(idDelete, arrayDepartment, currentDepartmentIndex)) {
            System.out.println("This ID Department does not exist");
        } else {
            for (int i = 0; i <= currentDepartmentIndex; i++) {
                if(arrayDepartment[i].getDepartId() == idDelete) {
                    for (int j = i; j <= currentDepartmentIndex; j++) {
                        arrayDepartment[j]= arrayDepartment[j+1];
                    }
                    Data.arrayDepartment = arrayDepartment;
                    Data.currentDepartmentIndex --;
                    break;
                }
            }
            System.out.println("Department deleted successfully");
        }
    }

    private void updateDepartmentInfo(Scanner scanner, Department[] arrayDepartment, int currentDepartmentIndex) {

        System.out.println("Enter ID of the department you would like to update: ");
        int idUpdate = inputId(scanner);
        if (!isExistedDepartmentId(idUpdate, arrayDepartment, currentDepartmentIndex)) {
            System.out.println("This ID Department does not exist");
        } else {
            Department departmentUpdate = new Department();
            departmentUpdate.setDepartId(idUpdate);
            departmentUpdate.inputDataUpdate(scanner, arrayDepartment, currentDepartmentIndex);
            for (int i = 0; i <= currentDepartmentIndex; i++) {
                if (arrayDepartment[i].getDepartId() == idUpdate) {
                    arrayDepartment[i]= departmentUpdate;
                }
            }
            Data.arrayDepartment = arrayDepartment;
            System.out.println("Department Information updated");
        }
    }

    private boolean isExistedDepartmentId(int id, Department[] arrayDepartment, int currentDepartmentIndex) {
        if(currentDepartmentIndex == -1)
            return false;
        else {
            for (int i = 0; i <= currentDepartmentIndex; i++) {
                if (arrayDepartment[i].getDepartId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    private int inputId(Scanner scanner) {
        do {
            try{
                int idUpdate = Integer.parseInt(scanner.nextLine());
                if (idUpdate < 1) {
                    System.out.println("ID update must be positive integer !");
                }else
                    return idUpdate;
            }
            catch (Exception e) {
                System.out.println("ID update must be positive integer !");
            }
        }while (true);
    }

    public static void displayDepartment(Department[] arrayDepartment, int currentDepartmentIndex) {
        if (currentDepartmentIndex == -1) {
            System.out.println("Data blank !");
            return;
        }else {
            System.out.println("All department information :");
            for (int i = 0; i <= currentDepartmentIndex; i++) {
                arrayDepartment[i].displayData();
            }
        }
    }

    private void addDepartment(Scanner scanner, Department[] arrayDepartment, int currentDepartmentIndex) {
        int number = inputNumberAdd(scanner);
        for (int i = 1; i <= number; i++) {
            Department department = new Department();
            department.inputData(scanner, arrayDepartment, currentDepartmentIndex);
            arrayDepartment[currentDepartmentIndex + 1] = department;
            currentDepartmentIndex ++;
            Data.currentDepartmentIndex = currentDepartmentIndex;
        }
        Data.arrayDepartment = arrayDepartment;

    }

    private int inputNumberAdd(Scanner scanner) {
        System.out.println("Enter the number of the department you would like to add: ");
        do {
            try{
                int number = Integer.parseInt(scanner.next());
                if (number < 1) {
                    System.out.println("Please enter a positive number: ");
                } else
                    return number;
            }
            catch(Exception e){
                System.out.println("Please enter a positive integer number: ");
            }
        }while (true);
    }
}
