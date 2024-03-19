package com.kafka.demo.model;

public record Identifiers(
        String vehicleId,
        VehicleIdType vehicleIdType
) {}