package pojo;

import java.util.List;

/**
 * @Description
 * @ClassName Page
 * @PackageNmae pojo
 * @Author Yanhao
 * @Date 2021/3/4 15:37
 * @Version 1.0
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;

    //当前页
    private Integer pageNo;
    //总页码
    private Long pageTotal;
    //当前显示数量
    private Integer pageSize = PAGE_SIZE;
    //总记录数
    private Long pageTotalCount;
    //当前页数据
    private List<T> items;
    //分页条的请求地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Long pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Long pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pagNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                '}';
    }
}
