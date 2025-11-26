package HR_tizimi.controller;

import HR_tizimi.dto.ApiResponse;
import HR_tizimi.dto.AttachDTO;
import HR_tizimi.dto.ProfileDTO;
import HR_tizimi.dto.ProfileFilterDTO;
import HR_tizimi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/admin/create")
    public ResponseEntity<ApiResponse> create(@RequestBody ProfileDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.create(dto));
    }

    @PutMapping(value = "/user/update/detail")
    public ResponseEntity<ApiResponse> updateDetail(@RequestBody ProfileDTO dto) {
        return ResponseEntity.ok().body(profileService.updateDetail(dto));
    }

    @PutMapping(value = "/user/update/username")
    public ResponseEntity<ApiResponse> updateUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok().body(profileService.updateUsername(username));
    }

    @PutMapping("/user/update/password")
    public ResponseEntity<ApiResponse> changePassword(@RequestParam("password") String password){
        return ResponseEntity.ok().body(profileService.changePassword(password));
    }

    @GetMapping("/user/get/detail")
    public ResponseEntity<ProfileDTO> getDetail() {
        return ResponseEntity.ok().body(profileService.getDetail());
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<ApiResponse> delete(){
        return ResponseEntity.ok().body(profileService.delete());
    }


    @GetMapping("/admin/pagination")
    private ResponseEntity<PageImpl<ProfileDTO>> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "size", defaultValue = "10") int size){
        return ResponseEntity.ok(profileService.pagination(page - 1, size));
    }

    @PostMapping("/admin/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> filter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                                       @RequestBody ProfileFilterDTO filter) {
        PageImpl<ProfileDTO> result = profileService.filterProfile(filter, page - 1, size);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/user/update-photo/{profileId}")
    public ResponseEntity<ApiResponse> updateAttach(@PathVariable Integer profileId,
                                                    @RequestBody AttachDTO dto) {
        return ResponseEntity.ok().body(profileService.updateAttach(profileId, dto));
    }
}
