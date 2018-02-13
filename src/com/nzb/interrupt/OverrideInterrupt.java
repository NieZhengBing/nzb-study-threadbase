package com.nzb.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author M
 * @create 2018/2/11
 */
public class OverrideInterrupt extends Thread{
    private final Socket socket;
    private final InputStream in;

    public OverrideInterrupt(Socket socket, InputStream in) {
        this.socket = socket;
        this.in = in;
    }

    private void t() {

    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            super.interrupt();
        }
    }
}
