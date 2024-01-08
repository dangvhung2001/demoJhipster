package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OrderMain;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OrderMain entity.
 */
@Repository
public interface OrderMainRepository extends JpaRepository<OrderMain, Long> {
    @Query("select orderMain from OrderMain orderMain where orderMain.user.login = ?#{authentication.name}")
    List<OrderMain> findByUserIsCurrentUser();

    default Optional<OrderMain> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<OrderMain> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<OrderMain> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select orderMain from OrderMain orderMain left join fetch orderMain.user",
        countQuery = "select count(orderMain) from OrderMain orderMain"
    )
    Page<OrderMain> findAllWithToOneRelationships(Pageable pageable);

    @Query("select orderMain from OrderMain orderMain left join fetch orderMain.user")
    List<OrderMain> findAllWithToOneRelationships();

    @Query("select orderMain from OrderMain orderMain left join fetch orderMain.user where orderMain.id =:id")
    Optional<OrderMain> findOneWithToOneRelationships(@Param("id") Long id);
}
