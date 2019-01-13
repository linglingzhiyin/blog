package com.repository.technology;

import com.domain.material.MaterialConsume;
import com.domain.technology.TechnologyRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TechnologyRequirementRepository extends JpaRepository<TechnologyRequirement, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from TechnologyRequirement p where p.technologyRequirementId in ?1 ")
    public void deleteByIds(Long[] ids);


}
