package devsearch.profiles.ws.exception;

public class RestApiProfilesException extends Exception {

    private static final long serialVersionUID = 4561331221134856278L;
    private String sourceExceptionMessage;
    private String exceptionCode;

    public RestApiProfilesException(String message) {
	super(message);
    }

    public RestApiProfilesException(ExceptionMessages message) {
	super(message.getExceptionMessage());
	this.exceptionCode = message.getExceptionCode();
    }

    public RestApiProfilesException(ExceptionMessages message, String sourceExceptionMessage) {
	super(message.getExceptionMessage());
	this.exceptionCode = message.getExceptionCode();
	this.sourceExceptionMessage = sourceExceptionMessage;
    }

    public RestApiProfilesException(String message, String exceptionCode, String sourceExceptionMessage) {
	super(message);
	this.exceptionCode = exceptionCode;
	this.sourceExceptionMessage = sourceExceptionMessage;
    }

    public String getSourceExceptionMessage() {
	return this.sourceExceptionMessage;
    }

    public String getExceptionCode() {
	return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
	this.exceptionCode = exceptionCode;
    }

}
