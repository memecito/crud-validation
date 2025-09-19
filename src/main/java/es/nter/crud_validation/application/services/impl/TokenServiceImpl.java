package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.services.TokenService;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Tokens;
import es.nter.crud_validation.exceptions.BadRequestException;
import es.nter.crud_validation.infraestructure.repositories.TokensRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokensRepository tokensRepository;
    @Override
    public Tokens createToken(Person person) {
        Tokens token = Tokens.builder()
                .token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusMinutes(30))
                .person(person)
                .used(false)
                .build();
        return tokensRepository.save(token);
    }

    @Override
    @Transactional
    public Person verifyToken(String verificationToken)  {
        Tokens token= (Tokens) tokensRepository.findByToken(verificationToken).orElseThrow(
                ()->new BadRequestException(""));
        if(token.isUsed())
            throw new BadRequestException("Token usado");

        token.setUsed(true);
        token.setConfirmedAt(LocalDateTime.now());
        tokensRepository.save(token);
        return token.getPerson();
    }
}
