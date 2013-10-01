package lt.nfq.conference.service.dao;

import java.util.List;

import lt.nfq.conference.domain.Demo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DemoMapper {
	
	@Select("SELECT * FROM demo WHERE id = #{id}")
	Demo getDemoData(@Param("id") int id);
	
    @Select("SELECT * FROM demo ORDER BY id")
    public List<Demo> getDemoDataList();

}
