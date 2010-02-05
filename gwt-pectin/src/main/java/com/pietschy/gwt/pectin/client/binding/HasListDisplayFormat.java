/*
 * Copyright 2010 Andrew Pietsch
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.pietschy.gwt.pectin.client.binding;

import com.pietschy.gwt.pectin.client.format.ListDisplayFormat;

/**
 * Created by IntelliJ IDEA.
 * User: andrew
 * Date: Jan 27, 2010
 * Time: 7:16:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HasListDisplayFormat<T>
{
   ListDisplayFormat<T> getFormat();

   void setFormat(ListDisplayFormat<T> format);
}