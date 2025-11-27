package HR_tizimi.repository;

import HR_tizimi.entity.PositionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Integer> {
    @Query("from PositionEntity p where p.name =:name and p.visible = true ")
    Optional<PositionEntity> findByPositionName(@Param("name") String name);

    @Query("from PositionEntity p where p.id =:id and p.visible = true ")
    Optional<PositionEntity> findByPositionId(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update PositionEntity p set p.name =:name, p.prtId =:prtId where p.id =:id")
    int update(@Param("id") Integer id,@Param("name") String name, @Param("prtId") Integer prtId);

    @Transactional
    @Modifying
    @Query("update PositionEntity p set p.visible = false, p.prtId =:prtId where p.id =:id")
    int delete(@Param("id") Integer id, @Param("prtId") Integer prtId);
    @Query("from PositionEntity p where p.visible = true ")
    Optional<List<PositionEntity>> getProfileList();
}
