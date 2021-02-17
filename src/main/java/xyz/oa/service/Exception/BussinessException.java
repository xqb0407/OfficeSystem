package xyz.oa.service.Exception;

/**
 * 业务逻辑异常
 */
public class BussinessException extends RuntimeException {
    private String code; //异常编码，异常为标识
    private String msg; //异常文本消息

    public BussinessException(String code,String msg){
        super(code +":"+ msg);
        this.code= code;
        this.msg=msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
