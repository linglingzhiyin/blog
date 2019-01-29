package com.repository.material;

import com.domain.material.MaterialConsume;
import com.domain.material.MaterialReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MaterialReceiveRepository extends JpaRepository<MaterialReceive, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from MaterialReceive p where p.materialReceiveId in ?1 ")
    public void deleteByIds(Long[] ids);

//    @Query("select p from MaterialConsume p where p.consume like ?1 ")
//    public List<MaterialConsume> searchMaterialByMaterialType(String s);

}
