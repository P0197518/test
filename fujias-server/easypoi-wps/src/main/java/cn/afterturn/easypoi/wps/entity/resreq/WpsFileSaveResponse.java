package cn.afterturn.easypoi.wps.entity.resreq;

import java.io.Serializable;

import cn.afterturn.easypoi.wps.entity.WpsFileEntity;
import lombok.Data;

/**
 * @author jueyue on 20-5-8.
 */
@Data
public class WpsFileSaveResponse extends WpsResponse implements Serializable {

    public WpsFileSaveResponse() {
    }

    public WpsFileSaveResponse(WpsFileEntity file) {
        this.file = file;
    }

    private WpsFileEntity file;
}
