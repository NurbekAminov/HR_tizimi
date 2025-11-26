package HR_tizimi.repository;

import HR_tizimi.dto.ProfileFilterDTO;
import HR_tizimi.entity.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProfileCustomRepository {
    @Autowired
    private EntityManager entityManager;

    /*"select *\n" +
            "from profile as p\n" +
            "inner join Profile_Position as pp on pp.profile_id = p.id\n" +
            "inner join Profile_Branch as pb on pb.profile_id = p.id\n" +
            "where pp.position_id = ? and pb.branch_id = ? and pc.visible = true";*/

    public Page<ProfileEntity> filter(ProfileFilterDTO filter, int page, int size){
        Map<String, Object> params = new HashMap<>();
        StringBuilder select = new StringBuilder(
                "SELECT DISTINCT p FROM ProfileEntity p, ProfilePositionEntity pp, PositionEntity pos, " +
                        "ProfileBranchEntity pb, BranchEntity br "
        );

        StringBuilder where = new StringBuilder(" WHERE p.visible = true " +
                "AND pp.profile = p AND pp.position = pos " +
                "AND pb.profile = p AND pb.branch = br ");

        if (filter.getPosition() != null) {
            where.append(" AND LOWER(pos.name) LIKE :position ");
            params.put("position", "%" + filter.getPosition().toLowerCase() + "%");
        }
        if (filter.getBranch() != null) {
            where.append(" AND LOWER(br.name) LIKE :branch ");
            params.put("branch", "%" + filter.getBranch().toLowerCase() + "%");
        }

        select.append(where);

// count: SELECT COUNT(DISTINCT p) FROM ProfileEntity p, ProfilePositionEntity pp, ...
        StringBuilder count = new StringBuilder("SELECT COUNT(DISTINCT p) FROM ProfileEntity p, ProfilePositionEntity pp, PositionEntity pos, ProfileBranchEntity pb, BranchEntity br ");
        count.append(where);

        Query selectQuery = entityManager.createQuery(select.toString());
        selectQuery.setFirstResult((page) * size); // 50
        selectQuery.setMaxResults(size); // 30
        params.forEach(selectQuery::setParameter);

        List<ProfileEntity> profileEntityList = selectQuery.getResultList();

        Query countQuery = entityManager.createQuery(count.toString());
        params.forEach(countQuery::setParameter);

        Long totalElements = (Long) countQuery.getSingleResult();
        return new PageImpl<>(profileEntityList, PageRequest.of(page, size), totalElements);
    }
}
