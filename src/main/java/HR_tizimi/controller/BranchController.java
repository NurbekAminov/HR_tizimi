package HR_tizimi.controller;

import HR_tizimi.dto.ApiResponse;
import HR_tizimi.dto.BranchDTO;
import HR_tizimi.dto.ProfileBranchDTO;
import HR_tizimi.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping("/create")
    public ResponseEntity<BranchDTO> create(@RequestBody BranchDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(branchService.create(dto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ApiResponse> update(@RequestBody BranchDTO dto) {
        return ResponseEntity.ok().body(branchService.update(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> delete(@RequestBody BranchDTO dto){
        return ResponseEntity.ok().body(branchService.delete(dto));
    }

    @PostMapping("/join")
    public ResponseEntity<ApiResponse> join(@RequestBody ProfileBranchDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(branchService.join(dto));
    }
}
