package com.repository.schedule;

import com.domain.schedule.Manufacture;
import com.domain.schedule.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from Manufacture p where p.manufactureSnId in ?1 ")
    public void deleteByIds(Long[] ids);

}
