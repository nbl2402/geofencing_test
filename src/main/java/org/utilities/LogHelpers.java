package org.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LogHelpers {

    private static final List<String> steps = new ArrayList<>();
    protected static final Logger logger = LoggerFactory.getLogger(LogHelpers.class);

    public static void logStep(String message) {
        steps.add(message);
        logger.info(message);
    }

    public static List<String> getSteps() {
        return new ArrayList<>(steps);
    }

    public static void clear() {
        steps.clear();
    }
}
