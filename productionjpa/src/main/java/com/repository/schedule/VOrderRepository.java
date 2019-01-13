package com.repository.schedule;

import com.domain.schedule.VOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VOrderRepository extends JpaRepository<VOrder, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from VOrder p where p.orderId in ?1 ")
    public void deleteByIds(Long[] ids);

}
