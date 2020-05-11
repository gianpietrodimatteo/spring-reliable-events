package com.example.reliableevents.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Component
public class SecurityService {
    private static final String CHARACTER_ENCODING = "UTF-8";
    private final Logger log = LoggerFactory.getLogger(SecurityService.class);
    private final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");

    @Autowired
    public SecurityService(@Value("${api.key}") String keyValue) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        try {
            log.info("Initializing security service with key: " + keyValue);
            SecretKeySpec key = new SecretKeySpec(keyValue.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] encrypt(final String value) {
        if (value != null) {
            try {
                return cipher.doFinal(value.getBytes(SecurityService.CHARACTER_ENCODING));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String decrypt(final byte[] value) {
        if (value != null) {
            try {
                return new String(cipher.doFinal(value), SecurityService.CHARACTER_ENCODING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public byte[] encrypt(final BigDecimal value) {
        byte[] result = null;
        if (value != null) {
            result = encrypt(new String(String.valueOf(value).getBytes()));
        }
        return result;
    }

    public BigDecimal decryptBigDecimal(final byte[] value) {
        String decryptedString = decrypt(value);
        if (decryptedString != null) {
            return new BigDecimal(decryptedString);
        }
        return BigDecimal.ZERO;
    }

}

