package com.codenvy.client.patch;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@GwtModule("com.codenvy.Module")
public class Presenter3Test extends GwtTestWithMocks {

    @Mock
    private IsWidget   view;
    private Presenter3 presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new Presenter3(view);
    }

    @Test
    public void viewShouldBeShown() throws Exception {
        SimpleLayoutPanel container = mock(SimpleLayoutPanel.class);

        presenter.go(container);

        verify(container).setWidget(view);
    }

}