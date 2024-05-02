package com.example.web_banhang.Utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Getter
@Component
public class RSAKeyProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSAKeyProperties(){
        KeyPair pair = KeyGeneratorUtility.GenerateRSAKey();
        this.publicKey = (RSAPublicKey) pair.getPublic();
        this.privateKey = (RSAPrivateKey) pair.getPrivate();
    }

    public void setPublicKey(RSAPublicKey rsaPublicKey){
        this.publicKey = rsaPublicKey;
    }

    public  void setPrivateKey(RSAPrivateKey privateKey){
        this.privateKey = privateKey;
    }
}
