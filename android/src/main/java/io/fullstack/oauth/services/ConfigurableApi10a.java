package com.github.scribejava.apis;

import android.util.Log;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.model.OAuth1RequestToken;

public class ConfigurableApi10a extends DefaultApi10a {

    private String accessTokenEndpoint;

    private String authorizationBaseUrl;

    private String requestTokenEndpoint;

    private Verb accessTokenVerb = Verb.POST;

    protected ConfigurableApi10a() {
    }

    private static class InstanceHolder {
        private static final ConfigurableApi10a INSTANCE = new ConfigurableApi10a();
    }

    public static ConfigurableApi10a instance() {
        return InstanceHolder.INSTANCE;
    }

    public ConfigurableApi10a setAccessTokenEndpoint(String endpoint) {
        accessTokenEndpoint = endpoint;
        return this;
    }

    public ConfigurableApi10a setAuthorizationBaseUrl(String baseUrl) {
        authorizationBaseUrl = baseUrl;
        return this;
    }

    public ConfigurableApi10a setRequestTokenEndpoint(String endpoint) {
        requestTokenEndpoint = endpoint;
        return this;
    }

    public ConfigurableApi10a setAccessTokenVerb(String verb) {
        if (verb.equalsIgnoreCase("GET")) {
            accessTokenVerb = Verb.GET;
        } else if (verb.equalsIgnoreCase("POST")) {
            accessTokenVerb = Verb.POST;
        } else {
            Log.e("ConfigurableApi", "Expected GET or POST string values for accessTokenVerb.");
        }

        return this;
    }

    @Override
    public Verb getAccessTokenVerb() {
        return accessTokenVerb;
    }

    @Override
    public Verb getRequestTokenVerb() {
        return Verb.POST;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return accessTokenEndpoint;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return requestTokenEndpoint;
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        // TODO: GET THE OAUTH CALLBACK
        return authorizationBaseUrl + "?oauth_token=" + requestToken.getToken() + "&oauth_callback=http%3A%2F%2Flocalhost%2Fmapedit";
    }
}
