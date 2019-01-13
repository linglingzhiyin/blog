package com.repository.emp;

import com.domain.emp.Custom;
import com.domain.emp.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomRepository extends JpaRepository<Custom, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from Custom p where p.customId in ?1 ")
    public void deleteByIds(Long[] Ids);

}
