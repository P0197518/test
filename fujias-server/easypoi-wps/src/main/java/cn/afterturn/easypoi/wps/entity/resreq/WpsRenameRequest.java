package cn.afterturn.easypoi.wps.entity.resreq;

import java.io.Serializable;

import lombok.Data;

/**
 * @author jueyue on 20-5-8.
 */
@Data
public class WpsRenameRequest extends WpsResponse implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
