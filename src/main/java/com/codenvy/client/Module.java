package com.codenvy.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Module implements EntryPoint {

    public void onModuleLoad() {
        RootLayoutPanel rootLayoutPanel = RootLayoutPanel.get();

        rootLayoutPanel.add(new Label("Hello world!"));

        Button button = new Button("button");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Window.alert("Hello");
            }
        });
        rootLayoutPanel.add(button);
    }

}