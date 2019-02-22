package org.apache.solr.bootstrap;

import java.io.IOException;

import org.apache.lucene.util.Constants;

public class BootstrapMain {

    public static void main(String[] args) throws IOException {
        while (true) {
            if (Constants.WINDOWS) {
                Natives.tryVirtualLock();
            } else {
                Natives.tryMlockall();
            }
            System.out.println("Virtual locked!!");
            System.in.read();
        }
    }
}
