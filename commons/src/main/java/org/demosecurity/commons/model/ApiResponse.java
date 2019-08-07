/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import org.demosecurity.commons.exception.GeneralException;

/**
 *
 * @author joksin
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ApiResponse<T> {
    
    private final String status;
    private final String message;
    private final T data;
    
    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse("success", null, data);
    }
    
    public static <T> ApiResponse<T> exception(GeneralException ex) {
        return new ApiResponse(ex.getStatus(), ex.getMessage(), null);
    }
    
}
