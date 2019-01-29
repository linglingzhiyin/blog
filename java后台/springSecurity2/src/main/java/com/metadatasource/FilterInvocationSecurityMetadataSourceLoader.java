package com.metadatasource;

import java.util.Date;

public interface FilterInvocationSecurityMetadataSourceLoader {

	Date getLastModified();
	
	Date getLastLoaded();
	
	void load();
	
	void reload();
}
