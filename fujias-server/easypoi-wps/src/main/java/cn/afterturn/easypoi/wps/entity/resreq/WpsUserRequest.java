package cn.afterturn.easypoi.wps.entity.resreq;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author jueyue on 20-5-8.
 */
@Data
public class WpsUserRequest implements Serializable {

    List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

}
