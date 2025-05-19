package com.email.utils;

import java.util.Arrays;

/**
 * A simple logging utility that provides different log levels and includes file location information.
 */
public class LoggerUtils {
    
    public enum LogLevel {
        DEBUG(1), INFO(2), WARN(3), ERROR(4);
        
        private final int level;
        
        LogLevel(int level) {
            this.level = level;
        }
        
        public int getLevel() {
            return level;
        }
    }
    
    private final Class<?> clazz;
    private LogLevel currentLevel = LogLevel.INFO; // Default level
    
    /**
     * Create a logger for the specified class
     * 
     * @param clazz the class this logger is for
     */
    private LoggerUtils(Class<?> clazz) {
        this.clazz = clazz;
    }
    
    /**
     * Get a logger instance for the specified class
     * 
     * @param clazz the class requesting a logger
     * @return LoggerUtil instance
     */
    public static LoggerUtils getLogger(Class<?> clazz) {
        return new LoggerUtils(clazz);
    }
    
    /**
     * Set the logging level for this logger
     * 
     * @param level the minimum level to log
     */
    public void setLevel(LogLevel level) {
        this.currentLevel = level;
    }
    
    /**
     * Log a debug message
     * 
     * @param message the message to log
     */
    public void debug(String message) {
        log(LogLevel.DEBUG, message, null);
    }
    
    /**
     * Log an info message
     * 
     * @param message the message to log
     */
    public void info(String message) {
        log(LogLevel.INFO, message, null);
    }
    
    /**
     * Log a warning message
     * 
     * @param message the message to log
     */
    public void warn(String message) {
        log(LogLevel.WARN, message, null);
    }
    
    /**
     * Log a warning message with exception
     * 
     * @param message the message to log
     * @param throwable the exception to log
     */
    public void warn(String message, Throwable throwable) {
        log(LogLevel.WARN, message, throwable);
    }
    
    /**
     * Log an error message
     * 
     * @param message the message to log
     */
    public void error(String message) {
        log(LogLevel.ERROR, message, null);
    }
    
    /**
     * Log an error message with exception
     * 
     * @param message the message to log
     * @param throwable the exception to log
     */
    public void error(String message, Throwable throwable) {
        log(LogLevel.ERROR, message, throwable);
    }
    
    /**
     * Internal logging method
     * 
     * @param level the log level
     * @param message the message to log
     * @param throwable optional exception to log
     */
    private void log(LogLevel level, String message, Throwable throwable) {
        if (level.getLevel() < currentLevel.getLevel()) {
            return;
        }
        
        // Get caller information
        StackTraceElement caller = getCaller();
        String location = caller != null ? 
            String.format("%s.%s(%s:%d)", 
                caller.getClassName(), 
                caller.getMethodName(), 
                caller.getFileName(), 
                caller.getLineNumber()) : 
            "Unknown location";
        
        // Format and print the log message
        String formattedMessage = String.format(
            "[%s] [%s] [%s] - %s", 
            level, 
            Thread.currentThread().getName(),
            location,
            message
        );
        
        System.out.println(formattedMessage);
        
        // Print stack trace if there's an exception
        if (throwable != null) {
            System.out.println("Exception details:");
            throwable.printStackTrace(System.out);
        }
    }
    
    /**
     * Get the caller of the logging method
     * 
     * @return the StackTraceElement for the caller
     */
    private StackTraceElement getCaller() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        
        // Find the first element that is not from this class or Thread
        for (int i = 1; i < stackTrace.length; i++) {
            StackTraceElement element = stackTrace[i];
            if (!element.getClassName().equals(getClass().getName()) && 
                !element.getClassName().equals(Thread.class.getName())) {
                return element;
            }
        }
        
        return null;
    }
}