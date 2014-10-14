package com.codenvy.client.usecase1;

import com.codenvy.client.LocaleConstant;
import com.codenvy.client.Service;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PresenterTest {

    private static final String TEXT = "some text";

    @Captor
    private ArgumentCaptor<AsyncCallback<String>> callbackCaptor;

    @Mock
    private View           view;
    @Mock
    private LocaleConstant localeConstant;
    @Mock
    private Service        service;
    @InjectMocks
    private Presenter      presenter;

    @Test
    public void delegateShouldBeSet() throws Exception {
        verify(view).setDelegate(presenter);
    }

    @Test
    public void textShouldBeChangedWhenOkButtonIsClicked() throws Exception {
        when(localeConstant.okTitle()).thenReturn(TEXT);

        presenter.onOkButtonClicked();

        verify(localeConstant).okTitle();
        verify(view).setText(TEXT);
    }

    @Test
    public void textShouldBeChangedWhenCancelButtonIsClicked() throws Exception {
        when(localeConstant.cancelTitle()).thenReturn(TEXT);

        presenter.onCancelButtonClicked();

        verify(localeConstant).cancelTitle();
        verify(view).setText(TEXT);
    }

    @Test
    public void viewShouldBeShown() throws Exception {
        AcceptsOneWidget container = mock(AcceptsOneWidget.class);

        presenter.go(container);

        verify(container).setWidget(view);
    }

    @Test
    public void textShouldBeChangedWhenAsyncCallbackIsFailed() throws Exception {
        when(localeConstant.failTitle()).thenReturn(TEXT);
        doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();

                //noinspection unchecked
                AsyncCallback<String> callback = (AsyncCallback<String>)arguments[0];
                callback.onFailure(mock(Throwable.class));

                return null;
            }
        }).when(service).doSomething(Matchers.<AsyncCallback<String>>anyObject());

        presenter.onTextChanged();

        verify(service).doSomething(Matchers.<AsyncCallback<String>>anyObject());
        verify(localeConstant).failTitle();
        verify(view).setText(TEXT);
    }

    @Test
    public void textShouldBeChangedWhenAsyncCallbackIsSuccess() throws Exception {
        presenter.onTextChanged();

        verify(service).doSomething(callbackCaptor.capture());

        AsyncCallback<String> callback = callbackCaptor.getValue();
        callback.onSuccess(TEXT);

        verify(view).setText(TEXT);
    }

}