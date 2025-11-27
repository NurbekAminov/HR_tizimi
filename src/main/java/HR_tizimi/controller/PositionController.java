package HR_tizimi.controller;

import HR_tizimi.dto.*;
import HR_tizimi.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @PostMapping("/create")
    public ResponseEntity<PositionDTO> create(@RequestBody PositionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(positionService.create(dto));
    }

    @GetMapping("/get")
    private ResponseEntity<List<PositionDTO>> get(){
        return ResponseEntity.ok().body(positionService.get());
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ApiResponse> updateName(@RequestBody PositionDTO dto) {
        return ResponseEntity.ok().body(positionService.updateName(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> delete(@RequestBody PositionDTO dto){
        return ResponseEntity.ok().body(positionService.delete(dto));
    }

    @PostMapping("/join")
    public ResponseEntity<ApiResponse> join(@RequestBody ProfilePositionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(positionService.join(dto));
    }
}
