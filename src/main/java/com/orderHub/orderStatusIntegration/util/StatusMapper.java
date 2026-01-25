package com.orderHub.orderStatusIntegration.util;

import java.util.Map;

public class StatusMapper {

    private static final Map<String, String> SYSTEM_A =
            Map.of(
                    "PEND", "Pending",
                    "PROC", "Processing",
                    "SHIP", "Shipped",
                    "COMP", "Completed",
                    "CANC", "Cancelled"
            );

    private static final Map<Integer, String> SYSTEM_B =
            Map.of(
                    1, "Pending",
                    2, "Processing",
                    3, "Shipped",
                    4, "Completed",
                    5, "Cancelled"
            );

    private StatusMapper() {}

    public static String fromSystemA(String status) {
        return SYSTEM_A.getOrDefault(status, "Unknown");
    }

    public static String fromSystemB(int status) {
        return SYSTEM_B.getOrDefault(status, "Unknown");
    }
}