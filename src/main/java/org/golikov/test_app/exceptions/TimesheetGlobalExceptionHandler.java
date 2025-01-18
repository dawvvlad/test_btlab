package org.golikov.test_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TimesheetGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<TimesheetIncorrectData> handleNoSuchValueException(NoSuchValueException e) {
        TimesheetIncorrectData incorrectData = new TimesheetIncorrectData();
        incorrectData.setInfo(e.getMessage());

        return new ResponseEntity<TimesheetIncorrectData>(incorrectData, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<IncorrectRequest> handleException(Exception e) {
        IncorrectRequest incorrectRequest = new IncorrectRequest();
        incorrectRequest.setInfo(e.getMessage());
        return new ResponseEntity<IncorrectRequest>(incorrectRequest, HttpStatus.BAD_REQUEST);
    }
}
