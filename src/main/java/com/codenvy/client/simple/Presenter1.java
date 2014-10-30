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
package com.codenvy.client.simple;

import com.codenvy.client.LocaleConstant;
import com.codenvy.client.Service;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import javax.inject.Inject;

/**
 * This class is some abstract presenter which has some business logic that user wants to test.
 *
 * @author Andrey Plotnikov
 */
public class Presenter1 implements View.ActionDelegate {

    private final View           view;
    private final Service        service;
    private final LocaleConstant localeConstant;

    @Inject
    public Presenter1(View view, LocaleConstant localeConstant, Service service) {
        this.view = view;
        this.service = service;
        this.view.setDelegate(this);

        this.localeConstant = localeConstant;
    }

    @Override
    public void onOkButtonClicked() {
        view.setText(localeConstant.okTitle());
    }

    @Override
    public void onCancelButtonClicked() {
        view.setText(localeConstant.cancelTitle());
    }

    @Override
    public void onApplyButtonClicked() {
        try {
            service.doSomething();
        } catch (RequestException e) {
            view.setText(localeConstant.failTitle());
        }
    }

    @Override
    public void onInfoButtonClicked() {
        service.doSomething(new AsyncRequestCallback<String>() {
            @Override
            protected void onSuccess(String result) {
                view.setText(result);
            }

            @Override
            protected void onFailure(Throwable exception) {
                view.setText(localeConstant.failTitle());
            }
        });
    }

    @Override
    public void onTextChanged() {
        service.doSomething(new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                view.setText(localeConstant.failTitle());
            }

            @Override
            public void onSuccess(String result) {
                view.setText(result);
            }
        });
    }

    public void go(AcceptsOneWidget container) {
        container.setWidget(view);
    }

}