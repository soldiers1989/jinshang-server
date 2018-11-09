package project.jinshang.mod_admin.mod_overtimeorders;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.mod_admin.mod_overtimeorders.dto.OverTimeView;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_company.SellerCompanyInfoMapper;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_product.service.OrdersService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags = "后台管理超时发货订单", description = "超时发货订单接口")
@RequestMapping("/rest/admin/overtimedelivery")
@Transactional(rollbackFor = Exception.class)
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
public class OverTimeOrdersAction {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private SellerCompanyInfoMapper sellerCompanyInfoMapper;

    @PostMapping(value = "/list")
    @ApiOperation(value = "超时发货订单统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "time", value = "查询日期", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pageNo", value = "页码(-1时候不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.TIMEOUTORDERS + "')")
    public PageRet getAllOverTimeOrdersList(String time,int pageNo, int pageSize) throws ParseException {
        PageRet pageRet = new PageRet();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = sdf.parse(time);
        PageInfo pageInfo = ordersService.getAllOverTimeOrdersList(time,utilDate,pageNo,pageSize);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping(value = "/getTwoSellerCompanyInfoList")
    @ApiOperation(value = "超时发货订单统计(只查奥展紧商)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "time", value = "查询日期", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pageNo", value = "页码(-1时候不分页)", required = true, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.TIMEOUTORDERS + "')")
    public BasicRet getAoZhangJinShangOverTimeOrdersList(String time,int pageNo, int pageSize) throws ParseException {
        BasicExtRet basicRet = new BasicExtRet();
        //只查询奥展和紧商的
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = sdf.parse(time);
        long[] ids = {6700,109};
        List<SellerCompanyInfo> sellerCompanyInfoList = sellerCompanyInfoMapper.selectAoZhangJinShangSellerCompanyInfoInIds(ids);
        List<OverTimeView> list1 =  ordersService.getAoZhangJinShangOverTimeDeliveryList(sellerCompanyInfoList,time,utilDate,pageNo,pageSize);
        basicRet.setData(list1);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("获取成功");
        return basicRet;
    }




    @GetMapping(value = "/export/list")
    @ApiOperation(value = "超时发货订单统计excel导出")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.TIMEOUTORDERS + "')")
    public ResponseEntity<InputStreamResource> exportList(String time,int pageNo, int pageSize, Model model) throws IOException {
        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"店铺","超时发货总数", "超时1天", "超时2天", "超时3天及以上"};
            //多增加rowTitles1 是为了Object o 为空是因为key需要是中文的 例如店铺 而不是companyname
            String[] rowTitles1 = new String[]{"companyname","overtimenum", "overtime1daynum", "overtime2daynum", "overtime3daynum"};
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date utilDate = sdf.parse(time);
            List<OverTimeView> list1 =  ordersService.getAllOverTimeOrdersListForExcel(time,utilDate,pageNo,pageSize);
            List<Map<String,Object>> list = project.jinshang.common.utils.BeanUtils.objectsToMaps(list1);

            workbook = ExcelGen.commonForOverTimeOrders("超时发货订单统计列表", rowTitles,rowTitles1,list, null);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("超时发货订单统计列表.xlsx".getBytes(), "iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
