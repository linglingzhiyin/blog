package com.repository.technology;

import com.domain.material.MaterialConsume;
import com.domain.technology.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from Technology p where p.technologyId in ?1 ")
    public void deleteByIds(Long[] ids);


}
