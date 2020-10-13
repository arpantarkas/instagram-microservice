package com.arpan.instagram.util;

import com.arpan.instagram.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtil <T> {
    public static <T> ResponseDto<T> setSuccessResponse (Integer status, String message, List<T> responseList) {
        return (ResponseDto<T>) new ResponseDto<>()
                .setMessage(message)
                .setStatus(status)
                .setResult(getPage((List<Object>) responseList));

    }

    public static <T> ResponseDto <T> setErrorResponse (Integer status, String errorMessage) {
        return (ResponseDto<T>) new ResponseDto<>()
                .setMessage(errorMessage)
                .setStatus(status)
                .setResult(getPage((List<Object>) new ArrayList<T>()));

    }

    public static <T> Page<T> getPage (List<T> responseList) {
        return new PageImpl<>(responseList);
    }
}
