package com.fsse2305.lab_b02_retry1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CourseNotFoundException extends RuntimeException{
}
