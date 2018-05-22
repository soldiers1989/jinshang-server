package project.jinshang.mod_admin.mod_station.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_station.bean.Station;
import project.jinshang.mod_admin.mod_station.StationMapper;
import project.jinshang.mod_admin.mod_station.bean.StationExample;

import java.util.List;

@Service
public class StationService {

    @Autowired
    private StationMapper stationMapper;



    public Station get(){
       return stationMapper.get();
    }


    public  void  updateStation(Station station) {
        StationExample stationExample = new StationExample();
        int Count = stationMapper.countByExample(stationExample);
        if (Count == 0) {
            stationMapper.insert(station);
        }else {
            stationMapper.update(station);
        }

    }

}
