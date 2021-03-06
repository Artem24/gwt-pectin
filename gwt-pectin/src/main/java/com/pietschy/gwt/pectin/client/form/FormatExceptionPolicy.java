/*
 * Copyright 2009 Andrew Pietsch 
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

package com.pietschy.gwt.pectin.client.form;

import com.pietschy.gwt.pectin.client.format.FormatException;

/**
 * Created by IntelliJ IDEA.
 * User: andrew
 * Date: Aug 5, 2009
 * Time: 2:57:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FormatExceptionPolicy<T>
{
   void onFormatException(FormattedFieldModel<T> model, FormatException e);
}
