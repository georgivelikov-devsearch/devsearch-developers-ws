package devsearch.developers.ws.exception;

public class RestApiDevelopersException extends Exception {

    private static final long serialVersionUID = 4561331221134856278L;
    private String sourceExceptionMessage;
    private String exceptionCode;

    public RestApiDevelopersException(String message) {
	super(message);
    }

    public RestApiDevelopersException(ExceptionMessages message) {
	super(message.getExceptionMessage());
	this.exceptionCode = message.getExceptionCode();
    }

    public RestApiDevelopersException(ExceptionMessages message, String sourceExceptionMessage) {
	super(message.getExceptionMessage());
	this.exceptionCode = message.getExceptionCode();
	this.sourceExceptionMessage = sourceExceptionMessage;
    }

    public RestApiDevelopersException(String message, String exceptionCode, String sourceExceptionMessage) {
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
