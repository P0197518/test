package cn.afterturn.easypoi.wps.entity.resreq;

import java.io.Serializable;

import lombok.Data;

/**
 * @author jueyue on 20-5-8.
 */
@Data
public class WpsResponse implements Serializable {

    private int code = 200;

    private String msg;
    private String status = "success";

    public static WpsResponse success() {
        return new WpsResponse();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
