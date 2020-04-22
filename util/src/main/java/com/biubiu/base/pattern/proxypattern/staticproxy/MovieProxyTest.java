package com.biubiu.base.pattern.proxypattern.staticproxy;

/**
 * 客户端
 */
public class MovieProxyTest {

    public static void main(String[] args) {
        Movie movie = new RealMovieImplProxy(new RealMovieImpl());
        movie.play();
    }

}
