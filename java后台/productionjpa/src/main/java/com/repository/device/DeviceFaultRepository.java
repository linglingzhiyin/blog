package com.repository.device;

import com.domain.device.Device;
import com.domain.device.DeviceCheck;
import com.domain.device.DeviceFault;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeviceFaultRepository extends JpaRepository<DeviceFault, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from DeviceFault p where p.deviceFaultId in ?1 ")
    public void deleteByIds(Long[] deviceIds);

    @Query("select p from DeviceFault p where p.deviceFaultId >=?1 and " +
            "p.deviceFaultId <(?1+10)")
    public List<DeviceFault> searchDeviceFaultByDeviceFaultId(Long searchValue);

}
