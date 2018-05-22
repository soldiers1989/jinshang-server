package project.jinshang.mod_admin.mod_station;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.mod_admin.mod_station.bean.Station;
import project.jinshang.mod_admin.mod_station.service.StationService;

import java.util.List;

@RestController
@Api(tags = "后台站点设置",description = "站点设置相关接口")
@RequestMapping(value ="/rest/admin/Station" )
public class StationAction {

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ApiOperation("站点查询")
    public StationRet get(){
        StationRet stationRet = new StationRet();

        Station station = stationService.get();

        if(station == null){
            stationRet.setResult(BasicRet.ERR);
            stationRet.setMessage("未查询到站点信息");
            return  stationRet;
        }

        stationRet.data.station = station;

        stationRet.setMessage("查询成功");
        stationRet.setResult(BasicRet.SUCCESS);
        return  stationRet;
    }

    @RequestMapping(value = "/updateStation",method = RequestMethod.POST)
    @ApiOperation("站点设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stname",value = "网站名称",dataType = "String",paramType = "query",required = true),
            @ApiImplicitParam(name = "sttitle",value = "网站标题",dataType = "String",paramType = "query",required = true),
            @ApiImplicitParam(name = "stkeyword",value = "关键词",dataType = "String",paramType = "query",required = true),
            @ApiImplicitParam(name = "stdescribe",value = "描述",dataType = "String",paramType = "query",required = true),
            @ApiImplicitParam(name = "stlogo",value = "Logo",dataType = "String",paramType = "query",required = true),
            @ApiImplicitParam(name = "aftertime",value = "售后时间",dataType = "int",paramType = "query",required = true),
    })
    public BasicRet updateStation(Station station){
        BasicRet basicRet= new BasicRet();
        stationService.updateStation(station);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }


    private  class  StationRet extends  BasicRet{
        private class StationData{
            private  Station station;

            public Station getStation() {
                return station;
            }

            public void setStation(Station station) {
                this.station = station;
            }
        }

        private  StationData data = new StationData();

        public StationData getData() {
            return data;
        }

        public void setData(StationData data) {
            this.data = data;
        }
    }
}
