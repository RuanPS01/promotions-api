package br.inatel.promotions.api.core.interceptor;

public record JwtTokenPayload(String issuer,
                              String subject,
                              String role,
                              String method,
                              String uri) {
}
