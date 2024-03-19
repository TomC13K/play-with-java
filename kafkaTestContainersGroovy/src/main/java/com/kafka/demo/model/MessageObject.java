package com.kafka.demo.model;

import java.io.Serializable;
//import java.util.Objects;

//public record MessageObject(String taskId, String taskName, float percentageComplete)
//        implements Serializable {

//    public MessageObject {
//        Objects.requireNonNull(taskId);
//        Objects.requireNonNull(taskName);
//    }
//}
    public record MessageObject(
            String notificationType,
            String vehicleId,
            Identifiers identifiers,
            String created,
            Status current,
            Status previous
    ) {}
