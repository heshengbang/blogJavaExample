package com.hsb.java;/**
 * Created by heshengbang on 2018/6/2.
 */

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.Objects;

/**
 * Created by heshengbang on 2018/6/2.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class FileCopy {
    @Before
    public void start() throws IOException {
        String txt = "This is a test String for all operation";
        try (OutputStream os = new FileOutputStream(new File("c:\\test.txt"))) {
            os.write(txt.getBytes(), 0, txt.getBytes().length);
        }
    }

    @Test
    public void copyFileByIO() throws IOException {
        try (InputStream is = new FileInputStream(new File("c:\\test.txt"));
             OutputStream os = new FileOutputStream(new File("c:\\target_io.txt"))) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    @Test
    public void copyFileByNIO() throws IOException {
        try (FileChannel sourceChannel = new FileInputStream(new File("c:\\test.txt")).getChannel();
             FileChannel targetChannel = new FileOutputStream(new File("c:\\test_nio.txt")).getChannel()) {
            for (long count = sourceChannel.size(); count > 0; ) {
                long transferred = sourceChannel.transferTo(sourceChannel.position(), count, targetChannel);
                sourceChannel.position(sourceChannel.position() + transferred);
                count -= transferred;
            }
        }
    }

    @Test
    public void copyFilesByFiles1() throws IOException {
        // copy(Path source, Path target, CopyOption... options)
        System.out.println(Files.copy(Paths.get("c:\\test.txt"), Paths.get("c:\\text_files2.txt"), StandardCopyOption.REPLACE_EXISTING));
    }

    @Test
    public void copyFilesByFiles2() throws IOException {
        // copy(Path source, OutputStream out)
        System.out.println(Files.copy(Paths.get("c:\\test.txt"), new FileOutputStream(new File("c:\\text_files1.txt"))));
    }

    @Test
    public void copyFilesByFiles3() throws IOException {
        // copy(InputStream in, Path target, CopyOption... options)
        System.out.println(Files.copy(new FileInputStream(new File("c:\\test.txt")), Paths.get("d:\\text_files3.txt"), StandardCopyOption.REPLACE_EXISTING));
    }


}
