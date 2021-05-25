package com.lhh.dao;

import java.util.List;

import com.lhh.pojo.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsDao extends JpaRepository<News, String>
{
	@Query(value="select t.* from news t where t.id in ( :ids)",nativeQuery = true)
	List<News> findByIds(@Param("ids") List<String> ids);
}
