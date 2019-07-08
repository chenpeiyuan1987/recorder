package org.yuan.project.recorder.utils;

import lombok.Getter;

@Getter
public class Fault extends RuntimeException {

    private Integer code;

    public Fault(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Fault(String message) {
        super(message);
        this.code = Result.FAILURE;
    }

}
