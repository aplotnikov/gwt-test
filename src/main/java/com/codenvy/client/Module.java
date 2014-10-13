package com.codenvy.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Module implements EntryPoint {

    public void onModuleLoad() {
        RootLayoutPanel.get().add(new Label("Hello world!"));
    }

}