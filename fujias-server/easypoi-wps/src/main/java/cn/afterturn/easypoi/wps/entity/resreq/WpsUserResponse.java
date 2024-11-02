package cn.afterturn.easypoi.wps.entity.resreq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.afterturn.easypoi.wps.entity.WpsUserEntity;
import lombok.Data;

/**
 * @author jueyue on 20-5-8.
 */
@Data
public class WpsUserResponse extends WpsResponse implements Serializable {

    private List<WpsUserEntity> users = new ArrayList<>();

    public List<WpsUserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<WpsUserEntity> users) {
        this.users = users;
    }

}
