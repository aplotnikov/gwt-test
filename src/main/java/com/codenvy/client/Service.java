/*
 * Copyright 2014 Codenvy, S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codenvy.client;

import com.codenvy.ide.rest.AsyncRequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * This class represents class that execute requests to server part of application.
 *
 * @author Andrey Plotnikov
 */
@Singleton
public class Service {

    @Inject
    public Service() {
    }

    public void doSomething(AsyncCallback<String> callback) {
        callback.onSuccess("OK");
    }

    public void doSomething(AsyncRequestCallback<String> callback) {
        // do nothing
    }

    public void doSomething() throws RequestException {
        // do nothing
    }

}