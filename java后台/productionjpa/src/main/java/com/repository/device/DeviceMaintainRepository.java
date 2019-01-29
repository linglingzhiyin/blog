package com.repository.device;

import com.domain.device.Device;
import com.domain.device.DeviceMaintain;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DeviceMaintainRepository extends JpaRepository<DeviceMaintain,Long> {
    @Modifying
    @Transactional
    @Query("DELETE from DeviceMaintain p where p.deviceMaintainId in ?1 ")
    public void deleteByIds(Long[] deviceIds);

}
