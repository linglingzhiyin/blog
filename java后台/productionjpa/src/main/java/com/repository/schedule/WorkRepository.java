package com.repository.schedule;

import com.domain.material.Material;
import com.domain.schedule.Product;
import com.domain.schedule.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from Work p where p.workId in ?1 ")
    public void deleteByIds(Long[] ids);

}
