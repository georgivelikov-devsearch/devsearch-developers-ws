package devsearch.developers.ws.shared.utils;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new SecureRandom();

    public String generatePublicId(int length) {
	return generateRandomString(length);
    }

    // Returns true if value is null, empty string (length == 0) or only white
    // spaces
    public boolean isNullOrBlank(String value) {
	return value == null || value.isBlank();
    }

    // Returns true if value is null or empty string (length == 0)
    public boolean isNullOrEmpty(String value) {
	return value == null || value.isEmpty();
    }

    public String getDateString(Date date) {
	return AppConstants.DATE_FORMATTER.format(date);
    }

    private String generateRandomString(int length) {
	StringBuilder returnValue = new StringBuilder();

	for (int i = 0; i < length; i++) {
	    returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
	}

	return returnValue.toString();
    }
}
