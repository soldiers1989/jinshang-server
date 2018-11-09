package project.jinshang.scheduled.mapper;

import project.jinshang.scheduled.Bean.DistributeTaskLog;

public interface DistributeTaskLogMapper {
    int insert(DistributeTaskLog record);

    int insertSelective(DistributeTaskLog record);
}