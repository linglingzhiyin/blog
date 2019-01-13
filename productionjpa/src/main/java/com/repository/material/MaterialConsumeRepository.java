package com.repository.material;

import com.domain.material.Material;
import com.domain.material.MaterialConsume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MaterialConsumeRepository extends JpaRepository<MaterialConsume, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from MaterialConsume p where p.consumeId in ?1 ")
    public void deleteByIds(Long[] ids);

//    @Query("select p from MaterialConsume p where p.consume like ?1 ")
//    public List<MaterialConsume> searchMaterialByMaterialType(String s);

}
