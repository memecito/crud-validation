package es.nter.crud_validation.presentation.dto.auth;

import lombok.Builder;

@Builder
public record AuthOutDto (int status, String token, String expiration ) {
}
