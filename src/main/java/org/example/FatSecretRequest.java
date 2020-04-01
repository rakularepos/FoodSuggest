package org.example;

import com.fatsecret.platform.services.Request;
import com.fatsecret.platform.services.RequestBuilder;

public class FatSecretRequest extends Request {
    public FatSecretRequest(String APP_KEY, String APP_SECRET) {
        super(APP_KEY, APP_SECRET);
    }
}
