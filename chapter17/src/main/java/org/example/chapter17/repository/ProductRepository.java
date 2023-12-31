package org.example.chapter17.repository;

import org.example.chapter17.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(
            """
                    SELECT p FROM ProductEntity p
                    WHERE p.name LIKE %:text%
                    AND p.owner=?#{authentication.name}                  
                            """
    )
    List<ProductEntity> findProductByNameContains(String text);

}
