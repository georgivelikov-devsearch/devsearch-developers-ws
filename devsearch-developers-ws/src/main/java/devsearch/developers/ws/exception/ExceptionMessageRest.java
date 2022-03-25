package devsearch.developers.ws.exception;

public class ExceptionMessageRest {
    private String timestamp;
    private String message;
    private String code;
    private String additionalInformation;
    private String path;
    private String method;

    public ExceptionMessageRest() {

    }

    public ExceptionMessageRest(String timestamp, String path, String method, String message, String code) {
	this.timestamp = timestamp;
	this.path = path;
	this.method = method;
	this.message = message;
	this.code = code;
    }

    public ExceptionMessageRest(String timestamp, String path, String method, String message, String code,
	    String additionalInformation) {
	this(timestamp, path, method, message, code);
	this.additionalInformation = additionalInformation;
    }

    public String getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getAdditionalInformation() {
	return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
	this.additionalInformation = additionalInformation;
    }

    public String getPath() {
	return path;
    }

    public void setPath(String path) {
	this.path = path;
    }

    public String getMethod() {
	return method;
    }

    public void setMethod(String method) {
	this.method = method;
    }
}