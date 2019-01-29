package com.metadatasource.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author lixinjie
 * @since 2018-10-31
 */
public interface MetadataSourceService {
	//获取时间
	Date getLastModified();
	//
	LinkedHashMap<String, List<String>> getAllUrlRolesMappings();
}
