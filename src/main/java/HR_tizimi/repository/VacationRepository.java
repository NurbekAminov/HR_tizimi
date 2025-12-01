package HR_tizimi.repository;

import HR_tizimi.entity.VacationEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacationRepository extends JpaRepository<VacationEntity, Integer> {
    @Query("from VacationEntity p where p.name =:name and p.visible = true ")
    Optional<VacationEntity> findByVacationName(@Param("name") String name);
    @Query("from VacationEntity p where p.name =:name and p.id !=:id and p.visible = true ")
    Optional<VacationEntity> findByVacationName(@Param("id") Integer id, @Param("name") String name);

    @Query("from VacationEntity p where p.id =:id and p.visible = true ")
    Optional<VacationEntity> findByVacationId(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update VacationEntity p set p.name =:name, p.balance =:balance, p.prtId =:prtId where p.id =:id")
    int update(@Param("id") Integer id, @Param("name") String name, @Param("balance") Integer balance, @Param("prtId") Integer prtId);

    @Transactional
    @Modifying
    @Query("update VacationEntity p set p.visible = false, p.prtId =:prtId where p.id =:id")
    int delete(@Param("id") Integer id, @Param("prtId") Integer prtId);

    @Query("from VacationEntity v where v.visible = true ")
    Optional<List<VacationEntity>> getVacationList();
}
