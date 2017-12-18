package com.common.io;

import com.common.concurrent.CommonThreadPool;
import com.common.concurrent.MultiJob;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * 大文件读取
 *
 * @author subo
 * @create 2017-12-16 15:38
 **/
public class BigFileInput {

    private static final Logger logger = LoggerFactory.getLogger(BigFileInput.class);

    public static String scannerFile(String path) {
        FileInputStream inputStream = null;
        Scanner scanner = null;
        StringBuffer buffer = new StringBuffer();
        try {
            inputStream = new FileInputStream(path);
            scanner = new Scanner(inputStream, "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                appendFile("F:\\app.log", line);
                logger.info(line);
            }
        } catch (Exception e) {
            logger.error("scanner file error", e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException exception) {
                    logger.error(exception.getMessage());
                }
            }
        }
        return buffer.toString();
    }

    public static void appendFile(String path, String content) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path, true);
            // 此处必须添加换行符，否则将认为是同一行，导致一个大数据行，在按行读取的时候可能会出现内存溢出
            fileWriter.append(content).append("\r\n");
        } catch (IOException e) {
            logger.error("append file error", e);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                logger.error("append file error", e);
            }
        }
    }

    public static void main(String[] args) {
        String path = "F:\\app.log";
        Long before = System.currentTimeMillis();
        System.out.println("before:" + before);
        //scannerFile(path);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i= 0; i< 5; i++) {
            CommonThreadPool.asyncExecute(() -> {
                for (int j= 0; j< 512; j++) {
                    scannerFile(path);
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }
        Long after = System.currentTimeMillis();
        System.out.println("after:" + after);
        long ti = after - before;
        logger.info("cost time:" + String.valueOf(ti));
    }
}
