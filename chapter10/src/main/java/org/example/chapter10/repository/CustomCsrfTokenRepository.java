package org.example.chapter10.repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.chapter10.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomCsrfTokenRepository implements CsrfTokenRepository {
    private final JpaTokenRepository jpaTokenRepository;

    public CustomCsrfTokenRepository(JpaTokenRepository jpaTokenRepository) {
        this.jpaTokenRepository = jpaTokenRepository;
    }

    // 애플리케이션이 새 토큰을 생성할때 호출하는 메서드
    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    // 특정 클라이언트를 위해 생성된 토큰을 저장합니다.
    // 기본 구현에서는 HTTP 세션을 이용하여 구현합니다.
    // 클라이언트는 요청에 X-IDENTIFIER 라는 헤더와 함께 보냅니다.
    // 데이터베이스에 해당 ID가 존재하면 새로운 토큰으로 업데이트하고 없다면 데이터베이스에 칼럼을 추가합니다.
    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader("X-IDENTIFIER");
        // 클라이언트 ID로 데이터베이스에서 토큰을 얻음
        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        // ID가 존재하면 새로 생성된 값으로 토큰의 값을 업데이트
        if (existingToken.isPresent()) {
            Token csrfToken = existingToken.get();
            csrfToken.changeToken(token.getToken());
        } else {
            // ID가 존재하지 않으면 생성된 CSRF 값과 ID로 새 레코드 생성
            Token csrfToken = new Token(identifier, token.getToken());
            jpaTokenRepository.save(csrfToken);
        }
    }

    // 토큰 세부 정보가 있으면 로드하고 없으면 null 반환
    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");

        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        if (existingToken.isPresent()) {
            Token csrfToken = existingToken.get();
            return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", csrfToken.getToken());
        }
        return null;
    }
}
