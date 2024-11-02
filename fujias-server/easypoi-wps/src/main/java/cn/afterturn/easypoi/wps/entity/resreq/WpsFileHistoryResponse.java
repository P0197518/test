package cn.afterturn.easypoi.wps.entity.resreq;

import java.io.Serializable;
import java.util.List;

import cn.afterturn.easypoi.wps.entity.WpsFileHistoryEntity;
import lombok.Data;

/**
 * @author jueyue on 20-5-8.
 */
@Data
public class WpsFileHistoryResponse extends WpsResponse implements Serializable {

    private List<WpsFileHistoryEntity> histories;
}
