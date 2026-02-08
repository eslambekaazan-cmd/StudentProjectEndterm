package utils;

import java.time.LocalDateTime;

public final class LoggerService {

    private static final LoggerService instance = new LoggerService();

    private LoggerService() {}

    public static LoggerService getInstance() {
        return instance;
    }

    public void info(String msg) {
        System.out.println("[INFO] " + LocalDateTime.now() + " - " + msg);
    }

    public void error(String msg) {
        System.out.println("[ERROR] " + LocalDateTime.now() + " - " + msg);
    }
}
