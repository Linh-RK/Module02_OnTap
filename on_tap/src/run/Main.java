package run;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        DepartmentManagement departmentMenu = new DepartmentManagement();
        EmployeeManagement employeeMenu = new EmployeeManagement();
        do {
            System.out.println("---------------------------MENU---------------------------");
            System.out.println("            1. Department Management                      ");
            System.out.println("            2. Employee Management                        ");
            System.out.println("            3. Exit                                       ");
            System.out.println("----------------------------------------------------------");
            choice = inputChoice(scanner);
            switch (choice) {
                case 1:
                    departmentMenu.departmentManagement(scanner);
                    break;
                case 2:
                    employeeMenu.employeeManagement(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a choice between 1 and 3: ");
            }
        }while(true);
    }

    public static int inputChoice(Scanner scanner) {
        System.out.println("Please enter your choice: ");
        do {
            try{
                return Integer.parseInt(scanner.next());
            }
            catch(Exception e) {
                System.out.println("Choice must be an positive integer: ");
            }
        }while(true);
    }
}
