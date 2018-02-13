package com.nzb;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author M
 * @create 2018/2/11
 */
public class PipeTransfer {
    private static class Print implements Runnable {
        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int received = 0;
            try {
                while ((received = in.read()) != -1) {
                    System.out.println((char) received);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();

        out.connect(in);
        Thread t1 = new Thread(new Print(in), "PrintThreaad");
        t1.start();
        int received = 0;
        try {
            while ((received = System.in.read()) != -1) {
                out.write(received);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

    }
}
