package project.jinshang.mod_wms_middleware.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "wms_middleware_msg")
public class WmsMidMsg {
    @Id @GeneratedValue
    private int id;
    private String url;
    private String params;
    private int status;
    private Timestamp createDt;

    public int getId() {
        return id;
    }

    public WmsMidMsg setId(int id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public WmsMidMsg setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getParams() {
        return params;
    }

    public WmsMidMsg setParams(String params) {
        this.params = params;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public WmsMidMsg setStatus(int status) {
        this.status = status;
        return this;
    }

    public Timestamp getCreateDt() {
        return createDt;
    }

    public WmsMidMsg setCreateDt(Timestamp createDt) {
        this.createDt = createDt;
        return this;
    }
}
