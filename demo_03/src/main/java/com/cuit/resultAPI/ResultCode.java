package com.cuit.resultAPI;

/**
 * 枚举一些常用API操作码
 */
public enum ResultCode implements ErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    // unauthorized 未经授权
    UNAUTHORIZED(401, "暂未登录或登录已经过期"),
    // forbidden 禁止
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

