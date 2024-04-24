package br.inatel.promotions.api.user.controller;

public record UserRequest(String name,
                          String email,
                          String password,
                          String role) {
}
