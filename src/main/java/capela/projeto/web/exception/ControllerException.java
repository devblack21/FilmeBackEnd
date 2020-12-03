package capela.projeto.web.exception;

import org.springframework.http.HttpStatus;

public class ControllerException extends RuntimeException {
    private final HttpStatus status;

    public ControllerException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public static ControllerException paymentRequired(String message) {
        return new ControllerException(HttpStatus.PAYMENT_REQUIRED, message);
    }

    public static ControllerException forbidden(String message) {
        return new ControllerException(HttpStatus.FORBIDDEN, message);
    }

    public static ControllerException notFound(String message) {
        return new ControllerException(HttpStatus.NOT_FOUND, message);
    }

    public static ControllerException methodNotAllowed(String message) {
        return new ControllerException(HttpStatus.METHOD_NOT_ALLOWED, message);
    }

    public static ControllerException notAcceptable(String message) {
        return new ControllerException(HttpStatus.NOT_ACCEPTABLE, message);
    }

    public static ControllerException proxyAuthenticationRequired(String message) {
        return new ControllerException(HttpStatus.PROXY_AUTHENTICATION_REQUIRED, message);
    }

    public static ControllerException requestTimeout(String message) {
        return new ControllerException(HttpStatus.REQUEST_TIMEOUT, message);
    }

    public static ControllerException conflict(String message) {
        return new ControllerException(HttpStatus.CONFLICT, message);
    }

    public static ControllerException gone(String message) {
        return new ControllerException(HttpStatus.GONE, message);
    }

    public static ControllerException lengthRequired(String message) {
        return new ControllerException(HttpStatus.LENGTH_REQUIRED, message);
    }

    public static ControllerException preconditionFailed(String message) {
        return new ControllerException(HttpStatus.PRECONDITION_FAILED, message);
    }

    public static ControllerException payloadTooLarge(String message) {
        return new ControllerException(HttpStatus.PAYLOAD_TOO_LARGE, message);
    }

    public static ControllerException uriTooLong(String message) {
        return new ControllerException(HttpStatus.URI_TOO_LONG, message);
    }

    public static ControllerException unsupportedMediaType(String message) {
        return new ControllerException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message);
    }

    public static ControllerException requestedRangeNotSatisfiable(String message) {
        return new ControllerException(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, message);
    }

    public static ControllerException expectationFailed(String message) {
        return new ControllerException(HttpStatus.EXPECTATION_FAILED, message);
    }

    public static ControllerException iAmATeapot(String message) {
        return new ControllerException(HttpStatus.I_AM_A_TEAPOT, message);
    }

    public static ControllerException unprocessableEntity(String message) {
        return new ControllerException(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

    public static ControllerException locked(String message) {
        return new ControllerException(HttpStatus.LOCKED, message);
    }

    public static ControllerException failedDependency(String message) {
        return new ControllerException(HttpStatus.FAILED_DEPENDENCY, message);
    }

    public static ControllerException tooEarly(String message) {
        return new ControllerException(HttpStatus.TOO_EARLY, message);
    }

    public static ControllerException upgradeRequired(String message) {
        return new ControllerException(HttpStatus.UPGRADE_REQUIRED, message);
    }

    public static ControllerException preconditionRequired(String message) {
        return new ControllerException(HttpStatus.PRECONDITION_REQUIRED, message);
    }

    public static ControllerException tooManyRequests(String message) {
        return new ControllerException(HttpStatus.TOO_MANY_REQUESTS, message);
    }

    public static ControllerException requestHeaderFieldsTooLarge(String message) {
        return new ControllerException(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE, message);
    }

    public static ControllerException unavailableForLegalReasons(String message) {
        return new ControllerException(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, message);
    }
}
