package project.jinshang.scheduled.Bean;

/**
 * CheakBean
 *
 * @author xiazy
 * @create 2018-08-01 11:48
 **/
public class CheckBean {
    public long freightmode;
    public long memberid;

    public CheckBean(long freightmode, long memberid) {
        this.freightmode = freightmode;
        this.memberid = memberid;
    }

    public long getFreightmode() {
        return freightmode;
    }

    public void setFreightmode(long freightmode) {
        this.freightmode = freightmode;
    }

    public long getMemberid() {
        return memberid;
    }

    public void setMemberid(long memberid) {
        this.memberid = memberid;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof CheckBean) {
            CheckBean question = (CheckBean) o;
            return this.memberid==question.memberid
                    && this.freightmode==question.freightmode;
        }
        return super.equals(o);
    }


    }
