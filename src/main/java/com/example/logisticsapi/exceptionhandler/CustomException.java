package com.example.logisticsapi.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomException {

    private Integer status;
    private LocalDateTime date;
    private String title;
    private List<ErrorFields> errorFields;
}
