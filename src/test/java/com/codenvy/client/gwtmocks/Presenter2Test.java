package com.codenvy.client.gwtmocks;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

// starting is 6s + executing is 2s
@GwtModule("com.codenvy.Module")
public class Presenter2Test extends GwtTestWithMocks {

    @Mock
    private IsWidget   view;
    private Presenter2 presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new Presenter2(view);
    }

    @Test
    public void viewShouldBeShown() throws Exception {
        SimpleLayoutPanel container = mock(SimpleLayoutPanel.class);

        presenter.go(container);

        verify(container).setWidget(view);
    }

}