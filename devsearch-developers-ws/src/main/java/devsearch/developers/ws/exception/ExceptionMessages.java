package devsearch.developers.ws.exception;

public enum ExceptionMessages {

    INTERNAL_SERVER_ERROR("Internal Server Error", "exc_000"),
    RECORD_ALREADY_EXISTS_WITH_THIS_ID("Record with this id already exists", "exc_001"),
    NO_RECORD_FOUND_WITH_THIS_ID("Record with provided id is not found", "exc_002"),
    CREATE_RECORD_FAILED(
	    "Could not create record. Field is missing or not valid. Please check documentation for required fields",
	    "exc_003"),
    UPDATE_RECORD_FAILED(
	    "Could not update record. Field is missing or not valid. Please check documentation for required fields",
	    "exc_004"),
    DELETE_RECORD_FAILED("Could not delete record", "exc_005"),
    NO_PFOFILE_FOUND_FOR_THIS_USER("No profile found for this user", "exc_006"),
    PROFILE_ALREADY_EXISTS_FOR_THIS_USER("Profile for this user already exists", "exc_007");

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
