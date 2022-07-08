package com.luguosong.server;

import java.io.OutputStream;

/**
 * 自定义servlet接口
 * @author luguosong
 * @date 2022/7/8
 */
public interface Servlet {
    public void init()throws Exception;
    public void service(byte[] requestBuffer, OutputStream out)throws Exception;
}
