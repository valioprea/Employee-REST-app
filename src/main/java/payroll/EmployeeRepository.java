package payroll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//by declaring this interface we are able to create, update and delete objects

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
