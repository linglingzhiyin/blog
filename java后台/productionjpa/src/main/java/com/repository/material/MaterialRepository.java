package com.repository.material;

import com.domain.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from Material p where p.materialId in ?1 ")
    public void deleteByIds(Long[] ids);

    @Query("select p from Material p where p.materialName like ?1 ")
    public List<Material> searchMaterialByMaterialType(String s);

}
