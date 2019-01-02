package com.authority.source;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public interface MetadataSourceService {
	//获取时间
	Date getLastModified();
	//
	LinkedHashMap<String, List<String>> getAllUrlRolesMappings();
}
