package com.authority.source;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface MetadataSourceMapper {

	Date getLastModified();
	
	List<Map<String, Object>> getAllUrlRoleMappings();
}
