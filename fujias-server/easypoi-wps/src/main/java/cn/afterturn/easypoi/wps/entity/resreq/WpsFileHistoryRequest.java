package cn.afterturn.easypoi.wps.entity.resreq;

import java.io.Serializable;

import lombok.Data;

/**
 * @author jueyue on 20-5-8.
 */
@Data
public class WpsFileHistoryRequest extends WpsResponse implements Serializable {

    private String id;
    private int offset;
    private int count;
}
