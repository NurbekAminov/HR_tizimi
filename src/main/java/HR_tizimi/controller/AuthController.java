package HR_tizimi.controller;

import HR_tizimi.dto.AuthRequestDTO;
import HR_tizimi.dto.AuthResponseDTO;
import HR_tizimi.dto.ProfileDTO;
import HR_tizimi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO dto) {
        AuthResponseDTO result = authService.login(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/registration")
    public ResponseEntity<ProfileDTO> registration(@RequestBody ProfileDTO dto) {
        ProfileDTO profileDTO = authService.registration(dto);
        return ResponseEntity.ok(profileDTO);
    }


}
