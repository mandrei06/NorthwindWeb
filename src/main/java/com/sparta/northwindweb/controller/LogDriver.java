package com.sparta.northwindweb.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogDriver {
    public static Logger logger = LogManager.getLogger("Logger");

    public static void main(String[] args) {
        logger.info("Program started");
    }
}
