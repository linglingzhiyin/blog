package com.repository.technology;

import com.domain.technology.ProcessT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProcessTRepository extends JpaRepository<ProcessT, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from ProcessT p where p.processTId in ?1 ")
    public void deleteByIds(Long[] ids);


}
