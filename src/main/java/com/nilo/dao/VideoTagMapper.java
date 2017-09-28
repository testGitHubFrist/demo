package com.nilo.dao;

import java.util.List;


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


}
