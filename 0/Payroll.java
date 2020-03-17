import javax.activation.UnsupportedDataTypeException;

public class Payroll {

    public static void main(String[] args) throws UnsupportedDataTypeException {

        HourlyEmployee hEmployee = new HourlyEmployee();
        ComissionEmployee cEmployee = new ComissionEmployee();
        BasePlusComissionEmployee bEmployee = new BasePlusComissionEmployee();

        Employee[] employees = { hEmployee, cEmployee, bEmployee };

        printEmployees(employees);

        hEmployee.setFirstName("John");
        hEmployee.setLastName("Doe");
        hEmployee.setId(0);
        hEmployee.setHours(24);
        hEmployee.setWage(40.3f);

        cEmployee.setFirstName("Jane");
        cEmployee.setLastName("Doe");
        cEmployee.setId(1);
        cEmployee.setGrossSales(309.34f);
        cEmployee.setComission(60);

        bEmployee.setFirstName("Jim");
        bEmployee.setLastName("Doe");
        bEmployee.setId(2);
        bEmployee.setGrossSales(23.89f);
        bEmployee.setComission(8);
        bEmployee.setBaseSalary(5500f);

        printEmployees(employees);

    }

    static void printEmployees(Employee[] employees) {
        
        for (Employee employee : employees) {

            if (employee instanceof BasePlusComissionEmployee) {
                Double salary = employee.earnings() * 1.1;
                System.out.println(employee.toString());
                System.out.println("Salary: " + String.format("%.2f", salary));
            } else {
                System.out.println(employee.toString());
                System.out.println("Salary: " + String.format("%.2f", employee.earnings()));
            }
            System.out.println("----------------");
        }
    }

}