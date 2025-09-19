package es.nter.crud_validation.infraestructure.repositories;

import es.nter.crud_validation.domain.models.Tokens;
import org.antlr.v4.runtime.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokensRepository extends JpaRepository<Tokens, Long> {
    Optional<Token> findByToken(String token);
    List<Tokens> findByUserId(Long userId);
    Long deleteByUsedFalseAndExpriresAtBefore(LocalDateTime dateTime);
}
