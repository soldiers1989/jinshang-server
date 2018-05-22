package project.jinshang.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * create : wyh
 * date : 2017/11/8
 */
public class Page implements Serializable{

    private  int  pageNo;
    private  int  pageSize;
    private  int  totalRows;
    private  int  totalPages;

    private List<?> list;


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
