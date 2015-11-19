package me.shib.java.omdb.service;

import java.io.File;

public class LocalCacheManager {
	
	private File localCacheDirectory;
	private long localCacheRenewalInterval;
	
	protected LocalCacheManager(long localCacheRenewalIntervalInMinutes, String localCacheDirectoryName) {
		if((localCacheDirectoryName == null) || (localCacheDirectoryName.isEmpty())) {
			this.localCacheDirectory = new File(Config.defaultLocalCacheDirectory);
		}
		else {
			this.localCacheDirectory = new File(localCacheDirectoryName);
		}
		if((!this.localCacheDirectory.exists()) || (!this.localCacheDirectory.isDirectory())) {
			this.localCacheDirectory.mkdirs();
		}
		this.localCacheRenewalInterval = localCacheRenewalIntervalInMinutes * 60000;
	}
	
}
