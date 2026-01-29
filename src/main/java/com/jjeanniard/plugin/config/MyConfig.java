package com.jjeanniard.plugin.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class MyConfig {
    private String welcomeMessage = "Welcome to the server :";
    // Les clés KeyedCodec doivent commencer par une majuscule (PascalCase). Les clés comme "serverName" lanceront une IllegalArgumentException.
    public static final BuilderCodec<MyConfig> CODEC = BuilderCodec.builder(MyConfig.class, MyConfig::new)
            .append(new KeyedCodec<>("WelcomeMessage", Codec.STRING),
                    (config, value) -> config.welcomeMessage = value,
                    config -> config.welcomeMessage)
            .add()
            .build();

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
