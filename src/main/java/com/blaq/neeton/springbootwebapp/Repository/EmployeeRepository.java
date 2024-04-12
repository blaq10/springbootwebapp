package com.blaq.neeton.springbootwebapp.Repository;

import com.blaq.neeton.springbootwebapp.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
