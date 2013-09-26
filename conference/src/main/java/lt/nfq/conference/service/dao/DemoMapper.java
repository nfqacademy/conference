package lt.nfq.conference.service.dao;

import lt.nfq.conference.domain.Demo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DemoMapper {
	@Select("SELECT * FROM demo WHERE id = #{id}")
	Demo getDemoData(@Param("id") int id);
}
