package com.kafka.demo.model;

import java.util.Map;

public record Status(
        Map<String, AlertEvent> alerts
) {}