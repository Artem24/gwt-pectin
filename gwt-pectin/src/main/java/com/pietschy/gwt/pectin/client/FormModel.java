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

package com.pietschy.gwt.pectin.client;

import com.pietschy.gwt.pectin.client.value.ValueModel;
import com.pietschy.gwt.pectin.client.list.ListModel;
import com.pietschy.gwt.pectin.client.format.Format;

import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;


/**
 * 
 */
public class FormModel
{
   private HashMap<Object, Object> properties = new HashMap<Object, Object>(); 
   private ArrayList<PluginCallback> mixinCallbacks = new ArrayList<PluginCallback>();

   public <T> FieldBuilder<T> fieldOfType(Class<T> type)
   {
      return new FieldBuilder<T>(this, type);
   }

   public <T> FormattedFieldBuilder<T> formattedFieldOfType(Class<T> type)
   {
      return new FormattedFieldBuilder<T>(this, type);
   }
   
   public <T> ListFieldBuilder<T> listOfType(Class<T> type)
   {
      return new ListFieldBuilder<T>(this, type);
   }
   
   /**
    * Factory method for creating field model instances.  This method is invoked by the field model
    * builder and is provided so subclasses can override the specific type that is returned.
    * 
    * @param model the source value model. @return a new field model instance.
    * @return a new field model 
    */
   protected <T> FieldModel<T> createFieldModel(ValueModel<T> model)
   {
      return new FieldModelImpl<T>(this, model);
   }
   
   protected <T> FormattedFieldModel<T> createFormattedFieldModel(ValueModel<T> model, Format<T> format)
   {
      return new FormattedFieldModelImpl<T>(this, model, format);
   }
   
   protected <T> ListFieldModel<T> createListModel(ListModel<T> source)
   {
      return new ListFieldModelImpl<T>(this, source);
   }

   public void putProperty(Object key, Object value)
   {
      properties.put(key, value);
   }

   public Object getProperty(Object key)
   {
      return properties.get(key);
   }

   public void addMixinCallback(PluginCallback callback)
   {
      mixinCallbacks.add(callback);
   }

   public Collection<PluginCallback> getMixinCallbacks()
   {
      return Collections.unmodifiableCollection(mixinCallbacks);
      
   }
}