package project.jinshang.mod_server.bean;

import java.math.BigDecimal;
import java.util.List;

public class ServerPayComplex {

    private List<ServerPayPageModel> list;

    private BigDecimal totalSum;

    public List<ServerPayPageModel> getList() {
        return list;
    }

    public void setList(List<ServerPayPageModel> list) {
        this.list = list;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }
}
