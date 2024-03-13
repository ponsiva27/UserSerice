package com.example.userservice.Repository;

import com.example.userservice.Model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository  extends JpaRepository<Token, Long> {

        public Token save(Token token);

        public Optional<Token> findTokenByValueAndExpiredAtGreaterThanAndIsDeleted(String tokenValue, Date timestamp, boolean isDeleted);


}
