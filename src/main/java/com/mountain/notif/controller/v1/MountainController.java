package com.mountain.notif.controller.v1;

import com.mountain.notif.domain.form.RequestMountainForm;
import com.mountain.notif.service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import winterfell.domain.ErrCode;
import winterfell.domain.ResponseEnvelope;
import winterfell.exceptions.WinterfellException;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/v1/notification")
@RequiredArgsConstructor
@Slf4j
public class MountainController {

    private final PushNotificationService pushNotificationService;

    @PostMapping(value = "/registered-mountain")
    public HttpEntity<ResponseEnvelope> registeredClimber(@RequestBody RequestMountainForm form) throws IOException {
        ErrCode errCode = ErrCode.SUCCESS;
        ResponseEnvelope rm = new ResponseEnvelope(errCode.getCode(), "Sukses");
        HttpStatus status = HttpStatus.OK;
        try {
            Map<String, Object> jsonResponse;

            String firstName = form.getFirstName();
            String stat = form.getStatus();
            String reason = form.getReason();
            String deviceId = form.getDeviceId();
            String appId = form.getAppId();
            String appKey = form.getAppKey();

            jsonResponse = pushNotificationService.pushRegisteredClimber(firstName, stat, reason, deviceId, appId, appKey);

            log.info("notification registered mountain {} success", jsonResponse);
        } catch (WinterfellException e) {
            rm.setCode(e.getErrCode().getCode());
            rm.setMessage(e.getMessage());
            log.warn("Exception Caught :", e);
        } catch (Exception e) {
            rm.setCode(ErrCode.ERR_UNKNOWN.getCode());
            rm.setMessage(ErrCode.ERR_UNKNOWN.getMessage());

            log.warn("Exception Caught :", e);
        }
        return ResponseEntity.status(status).body(rm);
    }

    @PostMapping(value = "/edit-status-mountain")
    public HttpEntity<ResponseEnvelope> editStatusClimber(@RequestBody RequestMountainForm form) throws IOException {
        ErrCode errCode = ErrCode.SUCCESS;
        ResponseEnvelope rm = new ResponseEnvelope(errCode.getCode(), "Sukses");
        HttpStatus status = HttpStatus.OK;
        try {
            Map<String, Object> jsonResponse;

            String firstName = form.getFirstName();
            String stat = form.getStatus();
            String reason = form.getReason();
            String deviceId = form.getDeviceId();
            String appId = form.getAppId();
            String appKey = form.getAppKey();

            jsonResponse = pushNotificationService.pushRegisteredClimber(firstName, stat, reason, deviceId, appId, appKey);

            log.info("notification registered mountain {} success", jsonResponse);
        } catch (WinterfellException e) {
            rm.setCode(e.getErrCode().getCode());
            rm.setMessage(e.getMessage());
            log.warn("Exception Caught :", e);
        } catch (Exception e) {
            rm.setCode(ErrCode.ERR_UNKNOWN.getCode());
            rm.setMessage(ErrCode.ERR_UNKNOWN.getMessage());

            log.warn("Exception Caught :", e);
        }
        return ResponseEntity.status(status).body(rm);
    }

    @PostMapping(value = "/edit-status-transaction")
    public HttpEntity<ResponseEnvelope> editStatusTransaction(@RequestBody RequestMountainForm form) throws IOException {
        ErrCode errCode = ErrCode.SUCCESS;
        ResponseEnvelope rm = new ResponseEnvelope(errCode.getCode(), "Sukses");
        HttpStatus status = HttpStatus.OK;
        try {
            Map<String, Object> jsonResponse;

            String firstName = form.getFirstName();
            String stat = form.getStatus();
            String reason = form.getReason();
            String deviceId = form.getDeviceId();
            String appId = form.getAppId();
            String appKey = form.getAppKey();

            jsonResponse = pushNotificationService.pushRegisteredClimber(firstName, stat, reason, deviceId, appId, appKey);

            log.info("notification registered mountain {} success", jsonResponse);
        } catch (WinterfellException e) {
            rm.setCode(e.getErrCode().getCode());
            rm.setMessage(e.getMessage());
            log.warn("Exception Caught :", e);
        } catch (Exception e) {
            rm.setCode(ErrCode.ERR_UNKNOWN.getCode());
            rm.setMessage(ErrCode.ERR_UNKNOWN.getMessage());

            log.warn("Exception Caught :", e);
        }
        return ResponseEntity.status(status).body(rm);
    }
}
