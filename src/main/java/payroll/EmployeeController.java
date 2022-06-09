package payroll;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    //Get all employees
    //Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    List<Employee> all() {return service.findAllEmployees();}

    //Create an employee
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {return service.saveEmployee(newEmployee);}

    //Get a single employee
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {return service.showEmployee(id);}

    //Update an employee by Id or create a new one if the id does not exist
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return  service.updateEmployee(id, newEmployee);
    }

    //Delete an employee
    @DeleteMapping("/employees/{id}")
    String deleteEmployee(@PathVariable Long id) {

        try{
            return service.deleteEmployee(id);
        }
        catch (IdNotFoundException e) {
            return e.getMessage();
        }
    }
}
