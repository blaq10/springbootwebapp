package com.blaq.neeton.springbootwebapp.Controller;

import com.blaq.neeton.springbootwebapp.Entity.Employee;
import com.blaq.neeton.springbootwebapp.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeesController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeesController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value = {"/showEmployee", "/", "list"})
    public String showEmployees(Model model) {

        List<Employee> employeeList = employeeRepository.findAll();
        model.addAttribute("employees", employeeList);
        System.out.println(employeeList);
        return "list-employees.html";
    }

    @GetMapping("/addEmployeeForm")
    public String addEmployeeForm(Model model) {
        Employee newEmployee = new Employee();
        model.addAttribute("employee", newEmployee);
        return "add-employee-form.html";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployeeById(@RequestParam Long id) {
        employeeRepository.deleteById(id);

        return "redirect:/list";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam Long employeeId, Model model) {
        Optional<Employee>em = employeeRepository.findById(employeeId);

        if (em.isPresent()) {
            Employee employee = em.get();

            model.addAttribute("employee", employee);
            employeeRepository.save(employee);
            employeeRepository.deleteById(employeeId);
        }
        return "add-employee-form";
    }
}
