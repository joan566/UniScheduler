package org.unischeduler.auth.domain.outputports;

public interface AccessTokenIssuerPort {
    String issueAccessToken(Long userId, String email);
}
