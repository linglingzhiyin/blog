package com.metadatasource.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author lixinjie
 * @since 2018-10-31
 */
//@Mapper
public interface MetadataSourceMapper {

	Date getLastModified();
	
	List<Map<String, Object>> getAllUrlRoleMappings();
}
