package devsearch.developers.ws.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import devsearch.common.exception.DevsearchApiException;
import devsearch.common.exception.ExceptionMessage;
import devsearch.common.utils.Utils;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = DevsearchApiException.class)
    public ResponseEntity<Object> handleRestApiException(DevsearchApiException ex, WebRequest request) {
	String dateStr = Utils.getDateString(new Date());
	String path = getPath(request);
	String method = getMethod(request);
	ExceptionMessage exception = new ExceptionMessage(dateStr, path, method, ex.getMessage(), ex.getExceptionCode(),
		ex.getSourceExceptionMessage());
	return new ResponseEntity<>(exception, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
	String dateStr = Utils.getDateString(new Date());
	String path = getPath(request);
	String method = getMethod(request);
	ExceptionMessage exception = new ExceptionMessage(dateStr, path, method, ex.getMessage(),
		ExceptionMessages.INTERNAL_SERVER_ERROR.getExceptionCode());
	return new ResponseEntity<>(exception, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getPath(WebRequest request) {
	return ((ServletWebRequest) request).getRequest().getRequestURI().toString();
    }

    private String getMethod(WebRequest request) {
	return ((ServletWebRequest) request).getHttpMethod().toString();
    }
}
