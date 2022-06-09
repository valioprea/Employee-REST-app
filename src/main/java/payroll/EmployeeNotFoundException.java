package payroll;

public class EmployeeNotFoundException extends RuntimeException{

    EmployeeNotFoundException(Long id) {
       super("Sorry mate, can't find the employee with id "+id);
    }
}
