package com.repository.device;

import com.domain.device.Device;
import com.domain.device.DeviceType;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {

    @Query("select p from DeviceType p where p.deviceTypeId >=?1 and " +
            "p.deviceTypeId <(?1+10)")
    public List<DeviceType> searchDeviceTypeByDeviceTypeId(Long deviceTypeId);

    @Query("select p from DeviceType p where p.deviceTypeName like ?1 ")
    public List<DeviceType> search_deviceType_by_deviceTypeName(String searchValue);

    @Modifying
    @Transactional
    @Query("DELETE from DeviceType p where p.deviceTypeId in ?1 ")
    public void deleteByIds(Long[] deviceTypeIds);
}
