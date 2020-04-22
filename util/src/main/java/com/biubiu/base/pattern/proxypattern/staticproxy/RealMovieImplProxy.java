package com.biubiu.base.pattern.proxypattern.staticproxy;

/**
 * 代理类：播放电影之前/之后干点其他的事
 **/
public class RealMovieImplProxy implements Movie {

    private RealMovieImpl realMovieImpl;

    public RealMovieImplProxy(RealMovieImpl realMovieImpl) {
        super();
        this.realMovieImpl = realMovieImpl;
    }

    @Override
    public void play() {
        this.playAdvertisement(true);
        realMovieImpl.play();//代理类把业务委托给目标类，并没有直接实现
        this.playAdvertisement(false);
    }

    private void playAdvertisement(boolean isStart) {
        if (isStart) {
            System.out.println("电影开始前，播放广告");
        } else {
            System.out.println("电影结束了，播放离场音乐");
        }
    }
}
