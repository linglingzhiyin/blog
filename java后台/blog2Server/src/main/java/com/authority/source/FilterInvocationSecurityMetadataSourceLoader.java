package com.authority.source;

import java.util.Date;

public interface FilterInvocationSecurityMetadataSourceLoader {

	Date getLastModified();
	
	Date getLastLoaded();
	
	void load();
	
	void reload();
}
