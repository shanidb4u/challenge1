package com.nab.currencyenquiry.exception;

import com.nab.currencyenquiry.domain.ApiError;
import lombok.Getter;

@Getter
public class ApiGeneralException extends RuntimeException {

    private ApiError apiError;

    public ApiGeneralException(ApiError apiError) {
        this.apiError = apiError;
    }
}
