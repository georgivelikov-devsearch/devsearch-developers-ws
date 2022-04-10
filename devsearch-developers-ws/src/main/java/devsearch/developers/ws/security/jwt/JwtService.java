package devsearch.developers.ws.security.jwt;

import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtService {

    public String getUsername(Jwt jwt);

    public String getFirstName(Jwt jwt);

    public String getLastName(Jwt jwt);

    public String getContactEmail(Jwt jwt);
}
