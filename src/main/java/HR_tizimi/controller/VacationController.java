package HR_tizimi.controller;

import HR_tizimi.dto.ApiResponse;
import HR_tizimi.dto.ProfileDTO;
import HR_tizimi.dto.VacationDTO;
import HR_tizimi.dto.ProfileVacationDTO;
import HR_tizimi.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacation")
public class VacationController {
    @Autowired
    private VacationService vacationService;

    @PostMapping("/admin/create")
    public ResponseEntity<VacationDTO> create(@RequestBody VacationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacationService.create(dto));
    }

    @GetMapping("/admin/get/all")
    private ResponseEntity<List<VacationDTO>> getAll(){
        return ResponseEntity.ok().body(vacationService.getAll());
    }

    @PutMapping(value = "/admin/update/name")
    public ResponseEntity<ApiResponse> updateName(@RequestBody VacationDTO dto) {
        return ResponseEntity.ok().body(vacationService.updateName(dto));
    }

    @PutMapping(value = "/admin/update/status")
    public ResponseEntity<ApiResponse> updateStatus(@RequestBody ProfileVacationDTO dto) {
        return ResponseEntity.ok().body(vacationService.updateStatus(dto));
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<ApiResponse> delete(@RequestBody VacationDTO dto){
        return ResponseEntity.ok().body(vacationService.delete(dto));
    }

    @PostMapping("/user/join")
    public ResponseEntity<ApiResponse> join(@RequestBody ProfileVacationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacationService.join(dto));
    }

    @GetMapping("/user/get/balace")
    public ResponseEntity<Integer> getProfileVacationBalance() {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacationService.getProfileVacationBalance());
    }
}
