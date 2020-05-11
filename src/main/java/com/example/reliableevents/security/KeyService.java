package com.example.reliableevents.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KeyService {
    @Value("${api.key}")
    private String keyValue;

    @GetMapping("/key")
    public Key getKey() {
        return new Key(keyValue);
    }

    class Key {
        private String key;

        public Key() {
        }

        public Key(final String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public void setKey(final String key) {
            this.key = key;
        }
    }

}
