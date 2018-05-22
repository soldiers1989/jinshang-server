package project.jinshang.mod_admin.mod_count.bean;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class OrderComplex {

    @ApiModelProperty(notes = "待付款")
    private List<OrderStatisticModel> list1;
    @ApiModelProperty(notes = "待发货")
    private List<OrderStatisticModel> list2;
    @ApiModelProperty(notes = "已完成")
    private List<OrderStatisticModel> list3;
    @ApiModelProperty(notes = "已取消")
    private List<OrderStatisticModel> list4;

    private PageInfo pageInfo;

    public List<OrderStatisticModel> getList1() {
        return list1;
    }

    public void setList1(List<OrderStatisticModel> list1) {
        this.list1 = list1;
    }

    public List<OrderStatisticModel> getList2() {
        return list2;
    }

    public void setList2(List<OrderStatisticModel> list2) {
        this.list2 = list2;
    }

    public List<OrderStatisticModel> getList3() {
        return list3;
    }

    public void setList3(List<OrderStatisticModel> list3) {
        this.list3 = list3;
    }

    public List<OrderStatisticModel> getList4() {
        return list4;
    }

    public void setList4(List<OrderStatisticModel> list4) {
        this.list4 = list4;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
