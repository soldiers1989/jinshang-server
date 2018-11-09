package project.jinshang.mod_admin.mod_synonym;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import mizuki.project.core.restserver.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_product.service.ProductSearchService;

import java.sql.Timestamp;
import java.util.List;

/**
 * ycj
 */
@RestController
@RequestMapping("/rest/admin/synonym")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台管理-同义词管理",description = "同义词管理")
@Transactional(rollbackFor = Exception.class)
public class SynonymRestAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SynonymMapper synonymMapper;
    @Autowired
    private ProductSearchService productSearchService;

    @RequestMapping(value="/add",method= RequestMethod.POST)
    @ApiOperation(value = "新增同义词组")
    public BasicRet add(
            Model model,
            @ApiParam(required = true,value = "json格式的数组：如[\"word1\",\"word2\"]")
            @RequestParam String words
    ) throws RestMainException {
        try{
            List<String> list = (List<String>) JsonUtil.toObject(words,List.class);
            if(list==null || list.size()==0){
                return new BasicRet(BasicRet.ERR,"words解析错误");
            }
            Synonym synonym = new Synonym()
                    .setCreateDt(new Timestamp(System.currentTimeMillis()))
                    .setWords(list);
            synonymMapper.save(synonym);

            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/del",method= RequestMethod.POST)
    @ApiOperation(value = "删除同义词组")
    public BasicRet del(
            Model model,
            @RequestParam int id
    ) throws RestMainException{
        try{
            synonymMapper.del(id);

            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/list",method= RequestMethod.POST)
    @ApiOperation(value = "获取同义词组列表")
    public SynonymListRet list(
                               String seachKey,
                               @RequestParam(required = true,defaultValue = "1") int currentPage,
                               @RequestParam(required = true,defaultValue = "20") int pageSize,Model model) throws RestMainException{
        try{
            SynonymListRet ret = new SynonymListRet();

            PageHelper.startPage(currentPage,pageSize);
            PageInfo pageInfo = new PageInfo(synonymMapper.listAll(seachKey));
            ret.getData().setPageInfo(pageInfo);
            ret.setResult(BasicRet.SUCCESS);
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/update",method= RequestMethod.POST)
    @ApiOperation(value = "修改同义词组")
    public BasicRet update(
            Model model,
            @RequestParam int id,
            @ApiParam(required = true,value = "json格式的数组：如[\"word1\",\"word2\"]")
            @RequestParam String words
    ) throws RestMainException{
        try{
            List<String> list = (List<String>) JsonUtil.toObject(words,List.class);
            if(list==null || list.size()==0){
                return new BasicRet(BasicRet.ERR,"words解析错误");
            }
            Synonym synonym = synonymMapper.findById(id);
            if(synonym==null){
                return new BasicRet(BasicRet.ERR,"不存在同义词组");
            }
            synonym.setWords(list);
            synonymMapper.update(synonym);

            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }
}
