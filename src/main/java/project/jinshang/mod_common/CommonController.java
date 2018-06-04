package project.jinshang.mod_common;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.*;
import project.jinshang.mod_admin.mod_commondata.service.CommonDataValueService;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_common.bean.ExpressModel;
import project.jinshang.mod_common.service.MobileService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_member.service.SellerCategoryService;
import project.jinshang.mod_product.bean.Brand;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.CategoriesSimple;
import project.jinshang.mod_product.service.BrandService;
import project.jinshang.mod_product.service.CategoriesService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;


/**
 * create : wyh
 * date : 2017/10/28
 */

@Controller
@RequestMapping("/rest/common")
@Api(tags = "通用模块")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME, AppConstant.ADMIN_SESSION_NAME})
public class CommonController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MobileService mobileService;


    @Autowired
    private CategoriesService categoriesService;


    @Autowired
    private CommonDataValueService commonDataValueService;

    @Autowired
    private SellerCategoryService sellerCategoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SessionRepository repository;



    //    @RequestMapping(value = "/poi", method = RequestMethod.GET)
//    @ApiOperation(value = "")
    public ResponseEntity<InputStreamResource> test(Model model) throws RestMainException {
        try {
            List<Map<String, Object>> data = new ArrayList<>();
            String[] rowTitles = new String[]{"交易时间", "汇款人名称", "金额"};
            Map<String, Object> m1 = new HashMap<>();
            m1.put("交易时间", new Date());
            m1.put("汇款人名称", "上海辛聪不锈钢制品有限公司");
            m1.put("金额", 867.34);
            data.add(m1);
            data.add(m1);
            data.add(m1);
            XSSFWorkbook workbook = ExcelGen.common("紧商科技应收帐款财务管理、作业程序", rowTitles, data, new String[]{"金额"});
            if (workbook != null) {
//                FileOutputStream fileOutputStream = new FileOutputStream(new File("tmp.xlsx"));
//                workbook.write(fileOutputStream);
//                fileOutputStream.flush();
//                fileOutputStream.close();
                // todo 暂时以内存方式
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", "test.xlsx"));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
            return null;
        } catch (Exception e) {
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value = "/poi-parse", method = RequestMethod.POST)
    @ApiOperation(value = "")
    public BasicRet poiParse(
            Model model,
            @RequestParam MultipartFile file
    ) throws RestMainException {
        try {
            List<Map<String, Object>> list = ExcelGen.parseCommon(file.getInputStream());
            return new BasicRet(BasicRet.SUCCESS);
        } catch (Exception e) {
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value = "/erm", method = RequestMethod.GET)
    @ApiOperation(value = "给定一个URL生成可跳转到该url的二维码")
    protected void erm(@RequestParam(required = true) String url,
                       @RequestParam(required = false, defaultValue = "300") int size,
                       HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        ByteArrayOutputStream out = QRCode.from(url).to(ImageType.PNG).withSize(size, size).stream();
        response.setContentType("image/png");
        response.setContentLength(out.size());
        OutputStream outStream = response.getOutputStream();
        outStream.write(out.toByteArray());
        outStream.flush();
        outStream.close();
    }


    @RequestMapping(value = "/ImgCode", method = RequestMethod.GET)
    @ApiOperation(value = "图片验证码")
    public ResponseEntity<InputStreamResource> ImgCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, createText);

            String webToken = request.getParameter("webToken");
            if(StringUtils.hasText(webToken) && !webToken.equals("null") && !webToken.equals("undefined") ){
                Session springSession = repository.getSession(webToken);
                session.setAttribute(Constants.KAPTCHA_SESSION_KEY,createText);
            }


            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return null;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        response.setHeader("Cache-Control", "no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", 0);
//        response.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream = response.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        String contentType = "image/jpeg";
        return ResponseEntity
                .ok()
                .headers(headers)
//                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType(contentType))
                .body(new InputStreamResource(new ByteArrayInputStream(captchaChallengeAsJpeg)));
    }


    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    @ApiOperation("获取登录用户信息(session)")
    @ResponseBody
    public UserInfoRet userInfo(Model model) {
        UserInfoRet basicRet = new UserInfoRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        if (member == null) {
            basicRet.setResult(BasicRet.TOKEN_ERR);
            basicRet.setMessage("用户未登录");
        } else {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.setData(member);
        }

        return basicRet;
    }


    @RequestMapping(value = "/db/userInfo", method = RequestMethod.POST)
    @ApiOperation("获取登录用户信息(从数据库获取用户信息)")
    @ResponseBody
    public UserInfoRet userInfoFromDb(Model model) {
        UserInfoRet basicRet = new UserInfoRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (member == null) {
            basicRet.setResult(BasicRet.TOKEN_ERR);
            basicRet.setMessage("用户未登录");
        } else {
            member = memberService.getMemberById(member.getId());
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("获取成功");
            basicRet.data = member;
        }

        return basicRet;
    }


    private class UserInfoRet extends BasicRet {
        private Member data;

        public Member getData() {
            return data;
        }

        public void setData(Member data) {
            this.data = data;
        }
    }


    @RequestMapping(value = "/adminInfo", method = RequestMethod.POST)
    @ApiOperation("获取管理员登录信息(session)")
    @ResponseBody
    public AdminInfoRet adminInfo(Model model) {
        AdminInfoRet adminInfoRet = new AdminInfoRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        if (admin == null) {
            adminInfoRet.setResult(BasicRet.TOKEN_ERR);
            adminInfoRet.setMessage("用户未登录");
            return adminInfoRet;
        }

        adminInfoRet.setMessage("查询成功");
        adminInfoRet.setResult(BasicRet.SUCCESS);
        adminInfoRet.setAdmin(admin);
        return adminInfoRet;
    }


    private class AdminInfoRet extends BasicRet {
        private Admin admin;

        public Admin getAdmin() {
            return admin;
        }

        public void setAdmin(Admin admin) {
            this.admin = admin;
        }
    }


    @RequestMapping(value = "/exisUsername", method = RequestMethod.POST)
    @ApiOperation(value = "查询用户名是否已经存在")
    @ResponseBody
    public BasicRet exisUsername(@RequestParam(required = true) String username) {
        if (memberService.exisUsername(username)) {
            return new BasicRet(BasicRet.ERR, "用户名已存在");
        }

        return new BasicRet(BasicRet.SUCCESS);
    }


    @RequestMapping(value = "/exisMobile", method = RequestMethod.POST)
    @ApiOperation(value = "检测手机号是否已经被注册")
    @ResponseBody
    public BasicRet exisMobile(@RequestParam(required = true) String mobile, HttpSession session) {
        if (!CommonUtils.isMobile(mobile)) {
            return new BasicRet(BasicRet.ERR, "手机号不合法");
        }

        if (!mobile.equals(AppConstant.MOCK_MOBILE) && memberService.exisMobile(mobile)) {
            return new BasicRet(BasicRet.ERR, "手机号已存在");
        }
        return new BasicRet(BasicRet.SUCCESS);
    }


    @RequestMapping(value = "/getProductCategories", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有产品分类")
    @ResponseBody
    @Cacheable(value = "CategoriesCache",key = "'getProductCategories'")
    public BasicRet getProductCategories() {
        BasicExtRet basicRet = new BasicExtRet();

        List<Categories> list = categoriesService.getAll();
        list = ProductCategoryUtils.getChildsManyGroup(list, 0, 1);

        basicRet.setData(list);
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @RequestMapping(value = "/getProductCategories_fastener", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有紧固件产品分类")
    @ResponseBody
    @Cacheable(value = "CategoriesCache",key = "'getProductCategoriesFastener'")
    public BasicRet getProductCategoriesFastener() {
        BasicExtRet basicRet = new BasicExtRet();
        List<Categories> list = categoriesService.getAllFastener();
        list = ProductCategoryUtils.getChildsManyGroup(list, 0, 1);

        basicRet.setData(list);
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }



    @RequestMapping(value = "/getSubCategoriesTree", method = RequestMethod.POST)
    @ApiOperation(value = "获取子分类树")
    @ResponseBody
    @Cacheable(value = "CategoriesCache",key = "'getSubCategoriesTree'+#p0")
    public BasicRet getSubCategoriesTree(@RequestParam(required = true) long parentid) {
        BasicExtRet basicRet = new BasicExtRet();
        List<Categories> list = categoriesService.getAll();
        list = ProductCategoryUtils.getChildsManyGroup(list, parentid);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setData(list);
        return basicRet;
    }

    //测试
   /*@RequestMapping(value = "/saveSellerCategory",method = RequestMethod.POST)
    @ApiOperation(value = "保存卖家分类")
    @ResponseBody
    public BasicRet saveSellerCategory(@RequestParam(required = true) long sellid){
        BasicRet basicRet = new BasicRet();

        //复制分类
        List<Categories> list =  categoriesService.getAll();

        List<SellerCategory> sellerCategories = new ArrayList<>();

        for(Categories categories:list){
            SellerCategory sellerCategory = new SellerCategory();
            sellerCategory.setCategoryid(categories.getId());
            sellerCategory.setBrokeragerate(categories.getBrokeragerate());
            if(StringUtils.hasText(categories.getDescription())){
                sellerCategory.setDescription(categories.getDescription());
            }else {
                sellerCategory.setDescription("");
            }
            if(StringUtils.hasText(categories.getImg())){
                sellerCategory.setImg(categories.getImg());
            }else {
                sellerCategory.setImg("");
            }
            if(StringUtils.hasText(categories.getKeywords())){
                sellerCategory.setKeywords(categories.getKeywords());
            }else {
                sellerCategory.setKeywords("");
            }
            if(StringUtils.hasText(categories.getName())){
                sellerCategory.setName(categories.getName());
            }else {
                sellerCategory.setName("");
            }
            sellerCategory.setParentid(categories.getParentid());
            sellerCategory.setSellerid(sellid);
            sellerCategory.setSort(categories.getSort());
            if(StringUtils.hasText(categories.getTitle())){
                sellerCategory.setTitle(categories.getTitle());
            }else {
                sellerCategory.setTitle("");
            }

            sellerCategories.add(sellerCategory);
        }
        sellerCategoryService.insertAll(sellerCategories);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("保存成功");
        return  basicRet;
    }
*/


    @RequestMapping(value = "/getSubCategories", method = RequestMethod.POST)
    @ApiOperation(value = "获取子分类（只获取一级）")
    @ResponseBody
    public BasicRet getSubCategories(@RequestParam(required = true) long parentid) {
        BasicExtRet basicRet = new BasicExtRet();
        List<Categories> list = categoriesService.getCategoryByParentid(parentid);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("查询成功");
        basicRet.setData(list);
        return basicRet;
    }


    @RequestMapping(value = "/getParentCategories", method = RequestMethod.POST)
    @ApiOperation(value = "获取父分类")
    @ResponseBody
    public BasicExtRet getParentCategories(@RequestParam(required = true) long subid) {
        BasicExtRet basicRet = new BasicExtRet();
        List<Categories> list = categoriesService.getAll();

        List<Categories> resultList = new ArrayList<>();
        ProductCategoryUtils.get_parent_list(list, subid, resultList);
        Collections.reverse(resultList);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setData(resultList);
        return basicRet;
    }


    @RequestMapping(value = "/getCategoryById", method = RequestMethod.POST)
    @ApiOperation(value = "根据id获取分类")
    @ResponseBody
    public CategoryRet getCategoryById(long id) {
        CategoryRet ret = new CategoryRet();

        Categories categories = categoriesService.getById(id);
        if (categories == null) {
            ret.setMessage("不存在该分类");
            ret.setResult(BasicRet.ERR);
            return ret;
        }

        ret.setMessage("查询成功");
        ret.setResult(BasicRet.SUCCESS);
        ret.data.categories = categories;
        return ret;
    }

    @RequestMapping(value = "/getExpressList", method = RequestMethod.POST)
    @ApiOperation(value = "获取快递列表")
    @ResponseBody
    public ExpressListRet getExpressList() {
        ExpressListRet expressListRet = new ExpressListRet();
        List<String> list = new ArrayList<>();
        list.add("邮政EMS");
        list.add("申通快递");
        list.add("顺丰快递");
        list.add("圆通快递");
        list.add("韵达快递");
        list.add("宅急送");
        list.add("中通快递");
        list.add("邮政平邮");

        expressListRet.data = list;
        expressListRet.setResult(BasicRet.SUCCESS);
        return expressListRet;

    }

    @PostMapping("getcommonDataValue")
    @ApiOperation("获取公共数据值")
    @ResponseBody
    public StringListRet getcommonDataValue(@RequestParam(required = true) String name) {
        StringListRet stringListRet = new StringListRet();

        if (name.equals("物流公司")) {
            List<String> list = commonDataValueService.getcommonDataValue(name);
            List<ExpressModel> expressModels = new ArrayList<>();
            for (String vl : list) {
                ExpressModel expressModel = new ExpressModel();
                String[] vlStr = vl.split("-");
                expressModel.setName(vlStr[0]);
                expressModel.setValue(vlStr[1]);
                expressModels.add(expressModel);
            }
            stringListRet.data.expressModels = expressModels;
        } else {
            stringListRet.data.stringList = commonDataValueService.getcommonDataValue(name);
        }
        stringListRet.setResult(BasicRet.SUCCESS);
        return stringListRet;
    }


    @RequestMapping(value = "/getBrandByCateid", method = RequestMethod.POST)
    @ApiOperation(value = "品牌查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "分类id", name = "categoryid", required = false, dataType = "int", paramType = "query", defaultValue = "0")
    })
    @ResponseBody
    public BrandListRet getBrandByCateid(@RequestParam(required = false, defaultValue = "0") long categoryid) {
        BrandListRet brandListRet = new BrandListRet();

        Brand brand = new Brand();
        brand.setCategoryid(categoryid);
        brandListRet.data.list = brandService.getByCateid(categoryid, Quantity.STATE_1);
        brandListRet.setResult(BasicRet.SUCCESS);
        return brandListRet;
    }



    @RequestMapping(value = "/packageToString", method = RequestMethod.POST)
    @ApiOperation(value = "转化为易看的包装方式")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "包装方式", name = "packagetype", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "数量", name = "num", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "单位", name = "unit", required = true, dataType = "string", paramType = "query"),
    })
    @ResponseBody
   public PackageToStringRet  packageToString(@RequestParam(required = true) String packagetype,
                                              @RequestParam(required = true) BigDecimal num,
                                              @RequestParam(required = true) String unit){
        PackageToStringRet packageToStringRet = new PackageToStringRet();

        String packageStr =JinshangUtils.packageToString(packagetype,num,unit);

        packageToStringRet.data.packageStr =  packageStr;
        packageToStringRet.setResult(BasicRet.SUCCESS);
        return  packageToStringRet;
    }




    private  class  PackageToStringRet extends BasicRet{
       private class PackageToStringData{
           private  String packageStr;

           public String getPackageStr() {
               return packageStr;
           }

           public void setPackageStr(String packageStr) {
               this.packageStr = packageStr;
           }
       }

       private  PackageToStringData data = new PackageToStringData();

        public PackageToStringData getData() {
            return data;
        }

        public void setData(PackageToStringData data) {
            this.data = data;
        }
    }

    private class BrandListRet extends BasicRet {
        private class BrandData {
            private List<Brand> list;

            public List<Brand> getList() {
                return list;
            }

            public void setList(List<Brand> list) {
                this.list = list;
            }
        }

        private BrandData data = new BrandData();

        public BrandData getData() {
            return data;
        }

        public void setData(BrandData data) {
            this.data = data;
        }
    }


    private class ExpressListRet extends BasicRet {
        private List<String> data;

        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
            this.data = data;
        }
    }


    private class CategoryRet extends BasicRet {
        private class CategoryData {
            private Categories categories;

            public Categories getCategories() {
                return categories;
            }

            public void setCategories(Categories categories) {
                this.categories = categories;
            }
        }

        private CategoryData data = new CategoryData();

        public CategoryData getData() {
            return data;
        }

        public void setData(CategoryData data) {
            this.data = data;
        }
    }


    private class StringListRet extends BasicRet {

        private class Data {
            private List<String> stringList;
            private List<ExpressModel> expressModels;

            public List<String> getStringList() {
                return stringList;
            }

            public void setStringList(List<String> stringList) {
                this.stringList = stringList;
            }

            public List<ExpressModel> getExpressModels() {
                return expressModels;
            }

            public void setExpressModels(List<ExpressModel> expressModels) {
                this.expressModels = expressModels;
            }
        }

        private Data data = new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    @RequestMapping(value = "/getRootProductCategories", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有最高级产品分类")
    @ResponseBody
    public BasicRet getRootProductCategories() {
        BasicExtRet basicRet = new BasicExtRet();

        List<Categories> list = categoriesService.getRootAll();

        basicRet.setData(list);
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }



    /*
    沪铝连续AL0
    沪锌连续ZN0
    沪铜连续CU0
    黄金连续AU0
    螺纹钢连续RB0
    线材连续WR0
    沪铅连续PB0
    白银连续AG0
    热轧卷板连续HC0
    沪锡连续SN0
    沪镍连续NI0
    燃油连续FU0
     */
    @RequestMapping(value = "/getSinaFutures",method = RequestMethod.POST)
    @ApiOperation(value = "获取新浪期货数据")
    @ResponseBody
    public BasicRet getSinaFutures() throws IOException {
        String url = "http://hq.sinajs.cn/list=AL0,ZN0,CU0,AU0,RB0,WR0,PB0,AG0,HC0,SN0,NI0,FU0";
        String content =  HttpClientUtils.get(url,"gbk");
       // System.out.println(content);
        BasicExtRet basicRet = new BasicExtRet();
        basicRet.setData(content);
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


}
