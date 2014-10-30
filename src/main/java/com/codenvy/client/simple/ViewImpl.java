/*
 * CODENVY CONFIDENTIAL
 * __________________
 * 
 * [2012] - [2014] Codenvy, S.A. 
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.client.simple;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author Andrey Plotnikov
 */
public class ViewImpl extends Composite implements View {

    interface ViewImplUiBinder extends UiBinder<Widget, ViewImpl> {
    }

    private static final ViewImplUiBinder ourUiBinder = GWT.create(ViewImplUiBinder.class);

    @UiField
    TextBox textBox;

    private ActionDelegate delegate;

    @Inject
    public ViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setText(String text) {
        textBox.setText(text);
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    @UiHandler("okBtn")
    public void onOkButtonClicked(@SuppressWarnings("UnusedParameters") ClickEvent event) {
        delegate.onOkButtonClicked();
    }

    @UiHandler("cancelBtn")
    public void onCancelButtonClicked(@SuppressWarnings("UnusedParameters") ClickEvent event) {
        delegate.onCancelButtonClicked();
    }

    @UiHandler("infoBtn")
    public void onInfoButtonClicked(@SuppressWarnings("UnusedParameters") ClickEvent event) {
        delegate.onInfoButtonClicked();
    }

    @UiHandler("applyBtn")
    public void onApplyButtonClicked(@SuppressWarnings("UnusedParameters") ClickEvent event) {
        delegate.onApplyButtonClicked();
    }

    @UiHandler("textBox")
    public void onTextChanged(@SuppressWarnings("UnusedParameters") KeyUpEvent event) {
        delegate.onTextChanged();
    }

}