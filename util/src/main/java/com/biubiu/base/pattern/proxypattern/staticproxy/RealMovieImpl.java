package com.biubiu.base.pattern.proxypattern.staticproxy;

/**
 * 目标类：真正的播放电影的实现
 **/
public class RealMovieImpl implements Movie {

    @Override
    public void play() {
        System.out.println("您正在观看电影 《肖申克的救赎》");
    }
}
