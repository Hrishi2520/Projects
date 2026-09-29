import com.example.util.EMPService;
import com.example.util.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        List<Employee> employeeList = new ArrayList<>();

        while(true) {
            printTask();
            Thread.sleep(2000);
            System.out.println("Enter Key:");
            String key = sc.nextLine();
            switch (key) {
                case "1" :
                    create(sc, employeeList);
                    break;
                case "2" :
                    update(sc, employeeList);
                    break;
                case "3" :
                    System.out.println("Enter Name: \n");
                    String name = sc.nextLine();
                    sleep(2);
                    Employee employee = EMPService.find(name, employeeList);
                    if (employee != null) System.out.println("Employee Found!");
                    break;
                case "4" :
                    System.out.println("Enter Name: \n");
                    name = sc.nextLine();
                    sleep(2);
                    boolean deleted = EMPService.delete(name, employeeList);
                    if (deleted) System.out.println("Employee Deleted Successfully.");
                    break;
                case "5" :
                    sleep(2);
                    System.out.println("Highest Salary: "+ EMPService.highestSalary(employeeList));
                    break;
                case "6" :
                    sleep(2);
                    System.out.println("2nd Highest Salary: "+ EMPService.secondHighestSalary(employeeList));
                    break;
                case "7" :
                    sleep(2);
                    System.out.println(EMPService.findEmpByDept(employeeList));
                    break;
                case "8" :
                    System.out.println("Current Employee List: \n"+employeeList);
                    break;
                case "0" : {
                    System.out.println("Exiting...\n");
                    Thread.sleep(3000);
                    System.out.println("Exited...\n");
                    return;
                }
            }
            Thread.sleep(2000);
            System.out.println("---------------------------------------------------------------------------------");

        }
    }

    private static void update(Scanner sc, List<Employee> employeeList) {
        System.out.println("Enter Id: ");
        int id = sc.nextInt();
        sc.nextLine();
        sleep(1);

        System.out.println("Enter NAME: ");
        String name = sc.nextLine();
        sleep(1);

        System.out.println("Enter Salary: ");
        double sal = sc.nextDouble();
        sc.nextLine();
        sleep(1);

        System.out.println("Enter Department Name: ");
        String dept = sc.nextLine();
        sleep(1);

        System.out.println("Updating New Employee...");
        EMPService.update(id, name, sal, dept, employeeList);
        sleep(2);
        System.out.println("Employee Update Successful");
    }

    public static void sleep(int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void printTask(){
        System.out.println("Create: 1 | Update: 2 | Search: 3 | delete: 4 | Highest Sal: 5 | 2nd Highest Sal: 6 | Department Wise: 7 | ListView: 8 | Exit: 0\n");
    }

    public static void create(Scanner sc, List<Employee> employeeList) {
        System.out.println("Enter Id: ");
        int id = sc.nextInt();
        sc.nextLine();
        sleep(1);

        System.out.println("Enter NAME: ");
        String name = sc.nextLine();
        sleep(1);

        System.out.println("Enter Salary: ");
        double sal = sc.nextDouble();
        sc.nextLine();
        sleep(1);

        System.out.println("Enter Department Name: ");
        String dept = sc.nextLine();
        sleep(1);

        System.out.println("Creating New Employee...");
        EMPService.create(id, name, sal, dept, employeeList);
        sleep(2);
        System.out.println("Employee Creation Successful");
    }
}