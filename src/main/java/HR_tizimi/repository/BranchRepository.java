package HR_tizimi.repository;

import HR_tizimi.entity.BranchEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

    @Query("from BranchEntity b where b.name =:name and b.visible = true ")
    Optional<BranchEntity> findByBranchName(@Param("name") String name);

    @Query("from BranchEntity b where b.id =:id and b.visible = true ")
    Optional<BranchEntity> findByBranchId(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update BranchEntity b set b.name =:name, b.prtId =:prtId where b.id =:id")
    int update(@Param("id") Integer id,@Param("name") String name, @Param("prtId") Integer prtId);

    @Transactional
    @Modifying
    @Query("update BranchEntity b set b.visible = false, b.prtId =:prtId where b.id =:id")
    int delete(@Param("id") Integer id, @Param("prtId") Integer prtId);
    @Query("from BranchEntity b where b.visible = true ")
    Optional<List<BranchEntity>> getBranchList();
}
