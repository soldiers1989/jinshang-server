package project.jinshang.mod_common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import mizuki.project.core.restserver.modules.oss_ali.AliOSS;
import mizuki.project.core.restserver.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;

import java.util.List;
import java.util.Map;

/**
 * ycj
 */
@RestController
@RequestMapping("/rest/common/oss")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME,AppConstant.ADMIN_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
@Api(tags = "通用模块")
public class OssRestAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AliOSS aliOSS;

    /**
     * data/
        test/
            private
            public
     */
    @RequestMapping(value = "/sts",method = RequestMethod.POST)
    @ApiOperation(value = "oss sts获取")
    public OssStsRet oss_sts(
            Model model,
            @ApiParam(required = true,
                    value = "请求的资源目录，json数组字符串, 如：[\"data/member/100\"]")
            @RequestParam String resources
    ) throws RestMainException {
        OssStsRet ret = new OssStsRet();
        try{
            Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
            Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
            List<String> list = (List<String>) JsonUtil.toObject(resources,List.class);
            if(list==null){
                return (OssStsRet)ret.setResult(BasicRet.ERR).setMessage("json解析错误");
            }
            for(String resource:list){
//                if(resource.startsWith("data/customer/"+customer.getId()+"/cert")){
//                    continue;
//                }
//                if(resource.startsWith("data/solution/")){
//                    int solutionId = Integer.parseInt(resource.split("/")[2]);
//                    // todo  判断权限
//                }
//                return (OssStsRet)ret.setResult(BasicRet.ERR).setMessage("resources不符合");
            }
            // todo rolesession
            Map<String,String> map = aliOSS.stsGetPutForUser("testId", list);
            ret.data.accessKeyId = map.get("accessKeyId");
            ret.data.accessKeySecret = map.get("accessKeySecret");
            ret.data.stsToken = map.get("stsToken");
            ret.data.expiration = map.get("expiration");
            ret.data.bucket = map.get("bucket");
            ret.data.region = map.get("region");
            return (OssStsRet)ret.setResult(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }
    public class OssStsRet extends BasicRet{
        private OssStsRetData data = new OssStsRetData();

        public OssStsRetData getData() {
            return data;
        }

        public class OssStsRetData{
            private String accessKeyId;
            private String accessKeySecret;
            private String stsToken;
            private String expiration;
            private String region;
            private String bucket;

            public String getAccessKeyId() {
                return accessKeyId;
            }

            public String getAccessKeySecret() {
                return accessKeySecret;
            }

            public String getStsToken() {
                return stsToken;
            }

            public String getExpiration() {
                return expiration;
            }

            public String getRegion() {
                return region;
            }

            public String getBucket() {
                return bucket;
            }
        }
    }
}
