package com.repository.schedule;

import com.domain.material.Material;
import com.domain.schedule.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Transactional
    @Query("DELETE from Product p where p.productId in ?1 ")
    public void deleteByIds(Long[] ids);

    @Query("select p from Product p where p.productName like ?1 ")
    public List<Material> searchProductByProductName(String s);

}
