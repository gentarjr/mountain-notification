package com.mountain.notif.domain.form;

import lombok.Data;

@Data
public class RequestMountainForm {

    private String firstName;
    private String status;
    private String reason;
    private String deviceId;
    private String appId;
    private String appKey;
}
