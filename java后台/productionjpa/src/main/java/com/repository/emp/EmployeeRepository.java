package com.repository.emp;

import com.domain.emp.Employee;
import com.domain.emp.Department;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from Employee p where p.employeeId in ?1 ")
    public void deleteByIds(Long[] deviceTypeIds);

    @Query("select p from Employee p where p.employeeId >=?1 and " +
            "p.employeeId <(?1+10)")
    public List<Employee> searchEmployeeByEmployeeId(Long searchValue);

    @Query("select p from Employee p where p.employeeName like ?1 ")
    public List<Employee> searchEmployeeByEmployeeName(String s);
}
