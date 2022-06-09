package payroll;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAllEmployees(){
        return repository.findAll();
    }

    String deleteEmployee(Long id){
        if(repository.findById(id) == null) {
            throw new IdNotFoundException(id);
        } else {
            repository.deleteById(id);
            return "employee with id "+id+"was deleted";
        }
    }
}
