package com.repository.technology;

import com.domain.material.MaterialConsume;
import com.domain.technology.TechnologyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TechnologyPlanRepository extends JpaRepository<TechnologyPlan, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from TechnologyPlan p where p.technologyPlanId in ?1 ")
    public void deleteByIds(Long[] ids);


}
