package com.martin.thinkinmyspring.cache;

/**
 * @program: think-in-my-spring
 * @description: 本地缓存
 * @author: martin
 * @create: 2020-03-16 14:05
 * @Version 0.1
 **/
public class LiveCache<T> {
    // 缓存时间
    private final int cacheMillis;
    // 缓存对象
    private final T element;
    // 缓存对象创建时间
    private final long createTime;

    public LiveCache(int cacheMillis, T element) {
        this.cacheMillis = cacheMillis;
        this.element = element;
        this.createTime = System.currentTimeMillis();
    }

    // 获取缓存对象
    public T getElement() {
        long currentTime = System.currentTimeMillis();
        if(cacheMillis > 0 && currentTime - createTime > cacheMillis) {
            return null;
        } else {
            return element;
        }
    }

    // 获取缓存对象，忽略缓存时间有效性
    public T getElementIfNecessary() {
        return element;
    }

    public static void main(String[] args) {
        int cacheMilis = 1000 ;
        LiveCache<Object> liveCache = new LiveCache<>(cacheMilis, new Object()) ;

        liveCache.getElement() ;
        liveCache.getElementIfNecessary() ;

    }

}
