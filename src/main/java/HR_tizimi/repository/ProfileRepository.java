package HR_tizimi.repository;

import HR_tizimi.entity.ProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByUsernameAndVisibleTrue(String username);

    @Query("from ProfileEntity p where p.id =:id and p.visible = true ")
    Optional<ProfileEntity> findByProfileId(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.name =:name, p.surname =:surname where p.id =:id")
    int updateDetail(@Param("id") Integer id, @Param("name") String name, @Param("surname") String surname);

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.photoId =:photoId, p.photoUrl =:photoUrl where p.id =:id")
    int updateAttach(@Param("id") Integer profileId, @Param("photoId") String PhotoId, @Param("photoUrl") String photpUrl);

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.username =:username where p.id =:id")
    int updateUsername(@Param("id") Integer id, @Param("username") String username);

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.password =:password where p.id =:id")
    int updatePassword(@Param("id") Integer id, @Param("password") String password);

    @Query("from ProfileEntity p where p.id =:id and p.visible= true ")
    Optional<ProfileEntity> getDetail(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.visible = false where p.id =:id")
    int delete(@Param("id") Integer id);
}
