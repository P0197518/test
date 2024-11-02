package cn.afterturn.easypoi.wps.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author jueyue on 20-5-8.
 */
@Data
public class WpsFileEntity implements Serializable {
    /**
     * 文件id,字符串长度小于40
     */
    private String id;
    /**
     * 文件名
     */
    private String name;
    /**
     * 当前版本号，位数小于11
     */
    private int version = 1;
    /**
     * 文件大小，单位为kb
     */
    private int size;
    /**
     * 创建者id，字符串长度小于40
     */
    private String creator = "0";
    /**
     * 修改者id，字符串长度小于40
     */
    private String modifier;
    /**
     * 创建时间，时间戳，单位为秒
     */
    private long create_time = System.currentTimeMillis();
    /**
     * 修改时间，时间戳，单位为秒
     */
    private long modify_time;
    private String download_url;

    private WpsUserAclEntity user_acl = new WpsUserAclEntity();
    private WpsWatermarkEntity watermark = new WpsWatermarkEntity();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getModify_time() {
        return modify_time;
    }

    public void setModify_time(long modify_time) {
        this.modify_time = modify_time;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public WpsUserAclEntity getUser_acl() {
        return user_acl;
    }

    public void setUser_acl(WpsUserAclEntity user_acl) {
        this.user_acl = user_acl;
    }

    public WpsWatermarkEntity getWatermark() {
        return watermark;
    }

    public void setWatermark(WpsWatermarkEntity watermark) {
        this.watermark = watermark;
    }

}
