package payroll;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeService service;

    public EmployeeController(EmployeeRepository repository, EmployeeService service) {
        this.repository = repository;
        this.service = service;
    }

    //Get all employees
    //Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    List<Employee> all() {
        return service.findAllEmployees();
    }

    //Create an employee
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    //Get a single employee
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    //Update an employee by Id or create a new one if the id does not exist
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole((newEmployee.getRole()));
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return  repository.save(newEmployee);
                });
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

//            throw new IdNotFoundException(id);
    }

}
