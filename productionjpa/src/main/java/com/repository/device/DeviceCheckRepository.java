package com.repository.device;

import com.domain.device.Device;
import com.domain.device.DeviceCheck;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeviceCheckRepository extends JpaRepository<DeviceCheck, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from DeviceCheck p where p.deviceCheckId in ?1 ")
    public void deleteByIds(Long[] deviceIds);

    @Query("select p from DeviceCheck p where p.deviceCheckId >=?1 and " +
            "p.deviceCheckId <(?1+10)")
    public List<DeviceCheck> searchDeviceCheckByDeviceCheckId(Long searchValue);

}
