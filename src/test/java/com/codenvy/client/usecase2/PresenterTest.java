package com.codenvy.client.usecase2;

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
public class PresenterTest extends GwtTestWithMocks {

    @Mock
    private IsWidget  view;
    private Presenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new Presenter(view);
    }

    @Test
    public void viewShouldBeShown() throws Exception {
        SimpleLayoutPanel container = mock(SimpleLayoutPanel.class);

        presenter.go(container);

        verify(container).setWidget(view);
    }

}