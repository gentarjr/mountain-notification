package com.mountain.notif.service;

import java.io.IOException;
import java.util.Map;

public interface PushNotificationService {
    Map<String, Object> pushRegisteredClimber(String firstName, String status, String reason, String deviceId, String appId, String appKey) throws IOException;
}
