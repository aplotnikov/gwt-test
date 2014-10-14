/*
 * Copyright 2014 Codenvy, S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codenvy.client.usecase1;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

/**
 * @author Andrey Plotnikov
 */
@ImplementedBy(ViewImpl.class)
public interface View extends IsWidget {

    void setText(String text);

    void setDelegate(ActionDelegate delegate);

    interface ActionDelegate {
        void onOkButtonClicked();

        void onCancelButtonClicked();

        void onTextChanged();
    }

}