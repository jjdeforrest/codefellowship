# codefellowship
creating things with spring

Fullfilled tasks:

Created a hello world and a route to capitalize a word to uppercase then a css page for colering and displayed a list of information with gifs. Also created test for the constructor etc.

#Go to http://localhost:8080/ in your browser port 8080 on localhost for home page

#go to http://localhost:8080/login for either current user or signup you can proceed to go to user profile and look at information


#error handler from spring website

@ControllerAdvice public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

@ExceptionHandler(value 
  = { IllegalArgumentException.class, IllegalStateException.class })
protected ResponseEntity<Object> handleConflict(
  RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "This should be application specific";
    return handleExceptionInternal(ex, bodyOfResponse, 
      new HttpHeaders(), HttpStatus.CONFLICT, request);
}
}
