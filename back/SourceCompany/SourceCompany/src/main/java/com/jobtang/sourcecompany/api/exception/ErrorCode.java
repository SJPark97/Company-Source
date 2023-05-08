package com.jobtang.sourcecompany.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements EnumModel {

    // COMMON
    INVALID_CODE(400, "C001", "Invalid Code"),
    RESOURCE_NOT_FOUND(204, "C002", "Resource not found"),
    EXPIRED_CODE(400, "C003", "Expired Code"),
    TEMPORARY_SERVER_ERROR(401,"C004","TEMPORARY_SERVER_ERROR"),
    USER_EXISTS(409,"C005","Duplicated Data"),
    SAVE_FAILED(400,"C006","Save Error"),
    USER_NOT_FOUND(204, "C007", "UserEmail Not Found"),

    // AWS
    AWS_ERROR(400, "A001", "aws client error"),

    // 분석


    // 커뮤니티
    COMM_EXISTS(400 , "G001","community not exists"),
    COMM_DELETED(401, "G002","community is deleted");

    private int status;
    private String code;
    private String message;
    private String detail;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    @Override
    public String getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.message;
    }
}
