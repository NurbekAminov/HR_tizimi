package HR_tizimi.controller;

import HR_tizimi.dto.ApiResponse;
import HR_tizimi.dto.AttachDTO;
import HR_tizimi.dto.ProfileDTO;
import HR_tizimi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping(value = "/user/update/detail/{profileId}")
    public ResponseEntity<ApiResponse> updateDetail(@PathVariable Integer profileId,
                                                    @RequestBody ProfileDTO dto) {
        return ResponseEntity.ok().body(profileService.updateDetail(profileId, dto));
    }


    @PutMapping("/user/update-photo/{profileId}")
    public ResponseEntity<ApiResponse> updateAttach(@PathVariable Integer profileId,
                                                    @RequestBody AttachDTO dto) {
        return ResponseEntity.ok().body(profileService.updateAttach(profileId, dto));
    }

    @GetMapping("/user/get/detail/{profileId}")
    public ResponseEntity<ProfileDTO> getDetail(@PathVariable Integer profileId) {
        return ResponseEntity.ok().body(profileService.getDetail(profileId));
    }

    @DeleteMapping("/user/delete/{profileId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer profileId){
        return ResponseEntity.ok().body(profileService.delete(profileId));
    }

    /*@PutMapping(value = "/user/update/username/{profileId}")
    public ResponseEntity<ApiResponse> updateUsername(@PathVariable Integer profileId,
                                                      @RequestParam("username") String username) {
        return ResponseEntity.ok().body(profileService.updateUsername(profileId, username));
    }

    @PutMapping("/user/update/password")
    public ResponseEntity<ApiResponse> changePassword(@RequestParam("password") String password){
        return ResponseEntity.ok().body(profileService.changePassword(password));
    }*/
}
