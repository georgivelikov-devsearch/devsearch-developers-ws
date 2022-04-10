package devsearch.developers.ws.security.jwt;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class KeycloakJwtServiceImpl implements JwtService {

    @Override
    public String getUsername(Jwt jwt) {
	return jwt.getClaimAsString("preferred_username");
    }

    @Override
    public String getFirstName(Jwt jwt) {
	return jwt.getClaimAsString("given_name");
    }

    @Override
    public String getLastName(Jwt jwt) {
	return jwt.getClaimAsString("family_name");
    }

    @Override
    public String getContactEmail(Jwt jwt) {
	return jwt.getClaimAsString("email");
    }

}
