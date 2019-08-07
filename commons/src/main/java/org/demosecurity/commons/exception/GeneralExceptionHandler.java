/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.commons.exception;

import org.demosecurity.commons.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author joksin
 */
@ControllerAdvice
public class GeneralExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity handleGenralException(GeneralException ex) {
        logger.warn(ex.getMessage(), ex);
        return new ResponseEntity(ApiResponse.exception(ex), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        GeneralException gEx = new GeneralException(ex);
        return new ResponseEntity(ApiResponse.exception(gEx), gEx.getHttpStatus());
    }

}
