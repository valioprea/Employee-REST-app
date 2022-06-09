package payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;


    //function that shows all employees
    public List<Employee> findAllEmployees(){
        return repository.findAll();
    }

    //function that saves employee
    public Employee saveEmployee(Employee employee){
        repository.save(employee);
        return employee;
    }

    //function that shows specific employee by Id
    public Employee showEmployee(Long id){
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    //function that updates specific employee by Id or creates a new one if Id is not found
    public Employee updateEmployee(Long id, Employee newEmployee){
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

    //function that deletes specific employee by Id
    String deleteEmployee(Long id){
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "employee with id "+id+"was deleted";
        } else {
            throw new IdNotFoundException(id);
        }
    }
}
