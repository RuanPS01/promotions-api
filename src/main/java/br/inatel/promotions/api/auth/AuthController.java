package br.inatel.promotions.api.auth;

import br.inatel.promotions.api.core.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotions")
public class AuthController {

    private AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> postAuth(@RequestBody AuthRequest request)
            throws ApiException {
        AuthResponse auth = service.authUser(request);
        return ResponseEntity.ok(auth);
    }
}
