package es.nter.crud_validation.presentation.dto.auth;

public record AuthOutDto(int status, String token, String expiration) {
}
