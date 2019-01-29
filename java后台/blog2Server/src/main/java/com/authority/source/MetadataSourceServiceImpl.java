package com.authority.source;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MetadataSourceServiceImpl implements MetadataSourceService {

	//private MetadataSourceMapper metadataSourceMapper;
	
	@Override
	public Date getLastModified() {
		return new Date();
	}

	@Override
	public LinkedHashMap<String, List<String>> getAllUrlRolesMappings() {
		LinkedHashMap<String, List<String>> urms = new LinkedHashMap<>();
		urms.put("/api/**", Arrays.asList("ROLE_Admin"));
		urms.put("/login", Arrays.asList("permitAll"));
		urms.put("/", Arrays.asList("permitAll"));
		urms.put("/test", Arrays.asList("permitAll"));
		urms.put("/**", Arrays.asList("authenticated"));
		return urms;
	}

}
