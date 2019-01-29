package com.repository.device;

import com.domain.device.Device;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from Device p where p.deviceId in ?1 ")
    public void deleteByIds(Long[] deviceIds);

    @Query("select p from Device p where p.deviceId >=?1 and " +
            "p.deviceId <(?1+10)")
    public List<Device> searchDeviceByDeviceId(Long searchValue);

    @Query("select p from Device p where p.deviceName like ?1 ")
    public List<Device> searchDeviceByDeviceName(String s);
}
