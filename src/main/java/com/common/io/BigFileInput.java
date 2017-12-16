package com.common.io;

import com.common.concurrent.CommonThreadPool;
import com.common.concurrent.MultiJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.Scanner;

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
                //appendFile("F:\\app.log", line);
                System.out.println(line);
                //logger.info(line);
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
            fileWriter.write(content);
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
        String path = "F:\\app.log.2017-09-12";
        Instant instantbefore = Clock.systemUTC().instant();
        scannerFile(path);
        Instant instantAfter = Clock.systemUTC().instant();
        long ti = instantAfter.getLong(ChronoField.MILLI_OF_SECOND) - instantbefore.getLong(ChronoField.MILLI_OF_SECOND);
        logger.info(String.valueOf(ti));
        /*for (int i= 0; i< 300; i++) {
            CommonThreadPool.asyncExecute(() -> scannerFile(path));
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }*/
    }
}
