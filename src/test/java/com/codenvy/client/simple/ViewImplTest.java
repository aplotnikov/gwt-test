package com.codenvy.client.simple;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.codenvy.client.simple.View.ActionDelegate;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class ViewImplTest {

    @Mock
    private ActionDelegate delegate;
    @Mock
    private ClickEvent     clickEvent;
    @Mock
    private KeyUpEvent     keyUpEvent;
    @InjectMocks
    private ViewImpl       view;

    @Before
    public void setUp() throws Exception {
        view.setDelegate(delegate);
    }

    @Test
    public void delegateActionShouldBeExecutedWhenUserIsClickingOnOkButton() throws Exception {
        view.onOkButtonClicked(clickEvent);

        verify(delegate).onOkButtonClicked();
    }

    @Test
    public void delegateActionShouldBeExecutedWhenUserIsClickingOnCancelButton() throws Exception {
        view.onCancelButtonClicked(clickEvent);

        verify(delegate).onCancelButtonClicked();
    }

    @Test
    public void delegateActionShouldBeExecutedWhenUserIsClickingOnInfoButton() throws Exception {
        view.onInfoButtonClicked(clickEvent);

        verify(delegate).onInfoButtonClicked();
    }

    @Test
    public void delegateActionShouldBeExecutedWhenUserIsChangingTextBox() throws Exception {
        view.onTextChanged(keyUpEvent);

        verify(delegate).onTextChanged();
    }

    @Test
    public void textBoxShouldBeChangedWhenUserExecutedMethodSetText() throws Exception {
        String text = "text";

        view.setText(text);

        verify(view.textBox).setText(text);
    }

}