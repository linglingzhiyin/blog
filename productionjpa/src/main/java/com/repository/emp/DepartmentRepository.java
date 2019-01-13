package com.repository.emp;

import com.domain.device.DeviceType;
import com.domain.emp.Department;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from Department p where p.departmentId in ?1 ")
    public void deleteByIds(Long[] deviceTypeIds);

    @Query("select p from Department p where p.departmentId >=?1 and " +
            "p.departmentId <(?1+10)")
    public List<Department> searchDepartmentByDepartmentId(Long searchValue);

    @Query("select p from Department p where p.departmentName like ?1 ")
    public List<Department> searchDepartmentByDepartmentName(String s);
}
