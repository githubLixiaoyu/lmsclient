package com.zxtech.support.cache.ehcache;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;




public class EhCacheImpl implements com.zxtech.support.cache.Cache {
	private Cache cache;
	private String name;
	private Logger logger = Logger.getLogger(EhCacheImpl.class);
	public EhCacheImpl(){
		
	}
	
	public EhCacheImpl(String name, int maxElementsInMemory, boolean overflowToDisk, boolean eternal, long timeToLiveSeconds, long timeToIdleSeconds){
		this.name = name;
		if (!CacheManager.getInstance().cacheExists(name)) {
			cache = new Cache(name,maxElementsInMemory,overflowToDisk,eternal,timeToLiveSeconds,timeToIdleSeconds);
			CacheManager.getInstance().addCache(cache);
		} else {
			cache = CacheManager.getInstance().getCache(name);
		}
	}
	
	public EhCacheImpl(String name){
		this.name = name;
		if (!CacheManager.getInstance().cacheExists(name)) {
			//设置缓存操作后1800秒过期(0为无限)
//			cache = new Cache(name,10,true,false,0,60*30);
			cache = new Cache(name,10,true,false,0,0);
			CacheManager.getInstance().addCache(cache);
		} else {
			cache = CacheManager.getInstance().getCache(name);
		}
	}
	
	@Override
	public void put(String key, Object value) {
		cache.put(new Element(key, value));
	}

	@Override
	public Object get(String key) {
		Element element = cache.get(key);
		if(null != element){
			return element.getObjectValue();
		}
		return null;
	}

	@Override
	public boolean isKeyInCache(String key) {
		return cache.isKeyInCache(key);
	}

	@Override
	public void removeCache()
	{
		if (CacheManager.getInstance().cacheExists(name)) {
			CacheManager.getInstance().removeCache(name);
		}
	}

	@Override
	public void clearCache() {
		cache.removeAll();
	}

	@Override
	public void remove(String key) {
		if(cache.isKeyInCache(key)){
			cache.remove(key);
		} else {
			logger.debug("缓存" + key + "不存在");
		}
		
	}

}
