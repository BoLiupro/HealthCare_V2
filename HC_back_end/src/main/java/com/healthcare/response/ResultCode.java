package com.healthcare.response;

/**
 * @author xiaoge
 * @Description 枚举这个就类似静态类,目的是指定返回的规范
 * 规定:
 * #200表示成功
 * #1001～1999 区间表示参数错误
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 * #4001～4999 区间表示业务错误
 * #5001～5999 区间表示部门错误
 * #9001～9999 区间表示运行时异常
 * #后面对什么的操作自己在这里注明就行了
 */
public enum ResultCode implements CustomizeResultCode{

    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "您的登录已经超时或者已经在另一台机器登录，您被迫下线"),
    USER_SESSION_INVALID(2010,"你的登录超时"),

    /* 业务错误 */
    NO_PERMISSION(4001, "没有权限"),

    /*部门错误*/
    DEPARTMENT_NOT_EXIST(5007, "部门不存在"),
    DEPARTMENT_ALREADY_EXIST(5008, "部门已存在"),

    /*运行时异常*/
    ARITHMETIC_EXCEPTION(9001,"算数异常"),
    NULL_POINTER_EXCEPTION(9002,"空指针异常"),
    ARRAY_INDEX_OUTOfBOUNDS_EXCEPTION(9003,"数组越界");

    private Integer code;

    private String message;

    ResultCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}