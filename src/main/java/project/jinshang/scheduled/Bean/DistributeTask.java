package project.jinshang.scheduled.Bean;

import java.util.Date;

public class DistributeTask {
    private Integer id;

    private String taskname;

    private String taskcode;

    private Date runtime;

    private Integer interval;

    private Short state;

    private Date endtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname == null ? null : taskname.trim();
    }

    public String getTaskcode() {
        return taskcode;
    }

    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode == null ? null : taskcode.trim();
    }

    public Date getRuntime() {
        return runtime;
    }

    public void setRuntime(Date runtime) {
        this.runtime = runtime;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}