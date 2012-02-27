/*
 * Copyright (C) 2010 David Wursteisen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.commons;

import com.google.gson.Gson;
import org.scribe.model.OAuthRequest;

import java.io.IOException;

public class RequestManager {

    private final Gson gson = new Gson();
    private final ProviderRequestGenerator provider;

    public RequestManager(ProviderRequestGenerator provider) {
        this.provider = provider;
    }


    public <RESPONSE extends Response> RESPONSE call(Request request, Class<RESPONSE> clazz) throws IOException {

        OAuthRequest httpRequest = provider.createHttpRequest(request);
        provider.addRequestParameters(httpRequest, request);

        if (request.isOAuth()) {
            provider.signRequest(httpRequest, request);
        }
        org.scribe.model.Response response = httpRequest.send();

        String responseBody = response.getBody();
        if (!response.isSuccessful()) {
            throw new IOException("Oups ! Problem occur with your request " + request
                    + " ! The called webservice respond with " + responseBody);
        }
        String json = provider.validateResponse(responseBody);
        RESPONSE responseObject = gson.fromJson(json, clazz);
        responseObject.setRawResponse(json);
        return responseObject;

    }


}
