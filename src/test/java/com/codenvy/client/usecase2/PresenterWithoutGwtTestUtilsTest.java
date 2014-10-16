package com.codenvy.client.usecase2;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwtmockito.GwtMockitoTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

// executing is 0.6s
@RunWith(GwtMockitoTestRunner.class)
public class PresenterWithoutGwtTestUtilsTest {

    @Mock
    private IsWidget  view;
    @InjectMocks
    private Presenter presenter;

    @Test
    public void viewShouldBeShown() throws Exception {
        SimpleLayoutPanel container = mock(SimpleLayoutPanel.class);

        presenter.go(container);

        verify(container).setWidget(view);
    }

}