package alexandre.dev.employee_server.service;

import alexandre.dev.employee_server.entity.Employee;
import alexandre.dev.employee_server.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createNewEmp(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmps(){
        return employeeRepository.findAll();
    }
    public void deleteEmployee(String id){
        if(!employeeRepository.existsById(id)) {
            throw new NoSuchElementException("No employee with the id: " + id + " were found");
        }
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(String id){
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(String id, Employee employee){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            Employee existingEmployee = employeeOptional.get();

            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPhone(employee.getPhone());
            existingEmployee.setDepartment(employee.getDepartment());

            return employeeRepository.save(existingEmployee);
        }
        return null;
    }
}
