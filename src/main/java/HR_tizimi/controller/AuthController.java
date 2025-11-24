package HR_tizimi.controller;

import HR_tizimi.dto.ApiResponse;
import HR_tizimi.dto.AuthDTO;
import HR_tizimi.dto.RegistrationDTO;
import HR_tizimi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
    @PostMapping("/registration")
    public ResponseEntity<ApiResponse> registration(@RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.registration(dto));
    }


}
