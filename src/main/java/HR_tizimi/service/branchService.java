package HR_tizimi.service;

import HR_tizimi.config.CustomUserDetails;
import HR_tizimi.dto.ApiResponse;
import HR_tizimi.dto.BranchDTO;
import HR_tizimi.entity.BranchEntity;
import HR_tizimi.repository.BranchRepository;
import HR_tizimi.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class branchService {
    @Autowired
    private BranchRepository branchRepository;

    public BranchDTO create(BranchDTO dto){
        Optional<BranchEntity> optional = branchRepository.findByName(dto.getName());
        if (optional.isPresent()){
            return null;
        }
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();

        BranchEntity entity = new BranchEntity();
        entity.setName(dto.getName());
        entity.setPrtId(customUserDetails.getProfile().getId());
        entity.setCreatedDate(LocalDateTime.now());
        branchRepository.save(entity);

        dto.setId(entity.getId());
        dto.setPrtId(entity.getPrtId());
        dto.setCreatedDate(dto.getCreatedDate());

        return dto;
    }

    public ApiResponse update(Integer id, String newName){
        Optional<BranchEntity> byId = branchRepository.findById(id);
        if (byId.isEmpty()){
            return null;
        }

        Optional<BranchEntity> byName = branchRepository.findByName(newName);
        if (byName.isPresent()){
            return null;
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = branchRepository.update(id, newName, customUserDetails.getProfile().getId());
        if (effectRows == 0){
            return new ApiResponse(false, "Branch Not Updated");
        }
        return new ApiResponse(true, "Branch Updated");
    }

    public ApiResponse delete(String name){
        Optional<BranchEntity> byName = branchRepository.findByName(name);
        if (byName.isEmpty()){
            return null;
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = branchRepository.delete(name, customUserDetails.getProfile().getId());
        if (effectRows == 0){
            return new ApiResponse(false, "Branch Not Deleted");
        }
        return new ApiResponse(true, "Branch Deleted");
    }
}
