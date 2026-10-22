package com.devpilot.example;

import java.io.*;
import java.sql.*;
import java.util.*;

// FIXME: Critical memory leak in transaction buffer
// TODO: Resolve race condition under concurrent load
public class VulnerableOrderProcessor {

    private Connection conn;

    public void processBulkOrders(List<String> orderIds, String userId, String role) {
        // Deep nesting (5 levels), high cyclomatic complexity, and anti-patterns
        if (orderIds != null) {
            for (int i = 0; i < orderIds.size(); i++) {
                String orderId = orderIds.get(i);
                if (orderId != null) {
                    if (userId.equals("admin") || role.equals("superuser")) {
                        for (int j = 0; j < 10; j++) {
                            try {
                                // SQL Injection defect pattern
                                String query = "SELECT * FROM orders WHERE id = '" + orderId + "' AND user = '" + userId + "'";
                                Statement stmt = conn.createStatement();
                                ResultSet rs = stmt.executeQuery(query);

                                while (rs.next()) {
                                    if (rs.getDouble("amount") > 1000.0) {
                                        if (rs.getString("status").equals("PENDING")) {
                                            System.out.println("Processing order: " + orderId);
                                        } else if (rs.getString("status").equals("FAILED")) {
                                            System.out.println("Failed order: " + orderId);
                                        } else {
                                            for (int k = 0; k < 5; k++) {
                                                if (k == 2) break;
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                // Silent exception swallow: hides runtime crashes
                            }
                        }
                    } else if (role.equals("manager")) {
                        try {
                            System.out.println("Manager override applied");
                        } catch (Exception e) {}
                    } else {
                        switch (role) {
                            case "guest":
                                System.out.println("Guest user");
                                break;
                            case "test":
                                System.out.println("Test user");
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
}
