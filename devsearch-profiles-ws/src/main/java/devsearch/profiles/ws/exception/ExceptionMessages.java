package devsearch.profiles.ws.exception;

public enum ExceptionMessages {

    INTERNAL_SERVER_ERROR("Internal Server Error", "exc_000"),
    RECORD_ALREADY_EXISTS_WITH_THIS_ID("Record with this id already exists", "exc_001"),
    RECORD_ALREADY_EXISTS_WITH_THIS_USERNAME("Record with this username already exists", "exc_002"),
    RECORD_ALREADY_EXISTS_WITH_THIS_EMAIL("Record with this email already exists", "exc_003"),
    NO_RECORD_FOUND_WITH_THIS_ID("Record with provided id is not found", "exc_004"),
    NO_RECORD_FOUND_WITH_THIS_USERNAME("Record with provided username is not found", "exc_005"),
    NO_RECORD_FOUND_WITH_THIS_EMAIL("Record with provided email is not found", "exc_006"),
    CREATE_RECORD_FAILED(
	    "Could not create record. Field is missing or not valid. Please check documentation for required fields",
	    "exc_007"),
    UPDATE_RECORD_FAILED(
	    "Could not update record. Field is missing or not valid. Please check documentation for required fields",
	    "exc_008"),
    DELETE_RECORD_FAILED("Could not delete record", "exc_009"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified", "exc_010"),
    AUTHENTICATION_FAILED("Authentication failed. Wrong password or incorrect JWT", "exc_011"),
    ACCESS_FORBIDDEN("Access is forbidden. User does not have permissions to perform this oppearion", "exc_012"),
    NO_PFOFILE_FOUND_FOR_THIS_USER("No profile found for this user", "exc_013"),
    NO_USER_FOUND_FOR_THIS_USER_ID("No user found for the provided user id", "exc_014"),
    PROFILE_ALREADY_EXISTS_FOR_THIS_USER("Profile for this user already exists", "exc_015");

    private String exceptionMessage;
    private String exceptionCode;

    ExceptionMessages(String errorMessage, String exceptionCode) {
	this.exceptionMessage = errorMessage;
	this.setExceptionCode(exceptionCode);
    }

    public String getExceptionMessage() {
	return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
	this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionCode() {
	return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
	this.exceptionCode = exceptionCode;
    }
}
