package ml.hadiya.conferencedemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    @Value("${app.version}")
    private String appVersion;
    @GetMapping("/")
    public ResponseEntity<Map<String,String>> getRootContext() {
        Map<String, String> map = new HashMap<>();
        map.put("project", "conference-demo");
        map.put("app-version", appVersion);
        return ResponseEntity.ok(map);
    }
}
