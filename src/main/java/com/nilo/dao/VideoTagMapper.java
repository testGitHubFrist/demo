package com.nilo.dao;

import java.util.List;

import com.nilo.model.sysTables;


/**
 * 
 * @author zhangshanchaungTest
 * @since 1.0
 * @date 2017-5-12
 */
public interface VideoTagMapper {

	/**
	 * 根据视频id 查询tag
	 * @param videoId
	 * @return
	 */
	List queryTagListByVideoId(String videoId) throws Exception;

	/**
	 * 查询所有数据库表
	 * @param database
	 * @return
	 */
	List<sysTables> queryDataBaseTablesByName(String database);


}
