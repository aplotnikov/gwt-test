package com.codenvy.client.simple;

import com.codenvy.client.LocaleConstant;
import com.codenvy.client.Service;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.gwt.test.utils.GwtReflectionUtils;

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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Presenter1Test {

    private static final String TEXT = "some text";

    @Captor
    private ArgumentCaptor<AsyncCallback<String>>        callbackCaptor;
    @Captor
    private ArgumentCaptor<AsyncRequestCallback<String>> asyncRequestCallbackCaptor;

    @Mock
    private View           view;
    @Mock
    private LocaleConstant localeConstant;
    @Mock
    private Service        service;
    @InjectMocks
    private Presenter1     presenter;

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

    @Test
    public void textShouldBeChangedWhenAsyncRequestCallbackIsFailed() throws Exception {
        when(localeConstant.failTitle()).thenReturn(TEXT);

        presenter.onInfoButtonClicked();

        verify(service).doSomething(asyncRequestCallbackCaptor.capture());

        AsyncRequestCallback<String> callback = asyncRequestCallbackCaptor.getValue();

        //noinspection NonJREEmulationClassesInClientCode
        Method onFailure = callback.getClass().getDeclaredMethod("onFailure", Throwable.class);
        onFailure.invoke(callback, mock(Throwable.class));

        verify(localeConstant).failTitle();
        verify(view).setText(TEXT);
    }

    @Test
    public void textShouldBeChangedWhenAsyncRequestCallbackIsSuccess() throws Exception {
        presenter.onInfoButtonClicked();

        verify(service).doSomething(asyncRequestCallbackCaptor.capture());

        AsyncRequestCallback<String> callback = asyncRequestCallbackCaptor.getValue();

        //noinspection NonJREEmulationClassesInClientCode
        Method onSuccess = GwtReflectionUtils.getMethod(callback.getClass(), "onSuccess");
        onSuccess.invoke(callback, TEXT);

        verify(view).setText(TEXT);
    }

    @Test
    public void textShouldBeChangedWhenAsyncRequestCallbackIsSuccess2() throws Throwable {
        presenter.onInfoButtonClicked();

        verify(service).doSomething(asyncRequestCallbackCaptor.capture());

        AsyncRequestCallback<String> callback = asyncRequestCallbackCaptor.getValue();

        //noinspection NonJREEmulationClassesInClientCode
        MethodType methodType = MethodType.methodType(void.class, String.class);
        //noinspection NonJREEmulationClassesInClientCode
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(callback.getClass(), "onSuccess", methodType);
        methodHandle.invoke(callback, TEXT);

        verify(view).setText(TEXT);
    }

}