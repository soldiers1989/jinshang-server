package project.jinshang.scheduled.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DistributeTaskMapper {

    @Update("update distribute_task set runtime=now(),state=1 where taskcode=#{taskcode} and state=0 and runtime + (space || ' seconds')::interval <= now()")
    int start(@Param("taskcode") String taskcode);

    @Update("update distribute_task set endtime=now(),state=0 where taskcode=#{taskcode}")
    int end(@Param("taskcode") String taskcode);
}