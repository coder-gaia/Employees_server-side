package alexandre.dev.employee_server.controller;

import alexandre.dev.employee_server.entity.Employee;
import alexandre.dev.employee_server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(originPatterns = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Employee> create(@RequestBody Employee employeeToBeCreated){
        return new ResponseEntity<Employee>(employeeService.createNewEmp(employeeToBeCreated), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmps();
        System.out.println("Retrieved employees: " + employees);
        return ResponseEntity.ok(employees);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id){
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id){
        Employee emp = employeeService.getEmployeeById(id);
       if(emp == null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(emp);
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);

        if(updatedEmployee == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedEmployee);
    }
}
