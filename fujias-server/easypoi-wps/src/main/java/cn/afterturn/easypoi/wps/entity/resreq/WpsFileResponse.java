package cn.afterturn.easypoi.wps.entity.resreq;

import java.io.Serializable;

import cn.afterturn.easypoi.wps.entity.WpsFileEntity;
import cn.afterturn.easypoi.wps.entity.WpsUserEntity;
import lombok.Data;

/**
 * 获取文件返回对象
 *
 * @author jueyue on 20-5-8.
 */
@Data
public class WpsFileResponse extends WpsResponse implements Serializable {

    private WpsFileEntity file;

    private WpsUserEntity user = new WpsUserEntity();

    public WpsFileEntity getFile() {
        return file;
    }

    public void setFile(WpsFileEntity file) {
        this.file = file;
    }

    public WpsUserEntity getUser() {
        return user;
    }

    public void setUser(WpsUserEntity user) {
        this.user = user;
    }

}
