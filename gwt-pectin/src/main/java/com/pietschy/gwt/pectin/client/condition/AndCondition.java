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

package com.pietschy.gwt.pectin.client.condition;

import com.pietschy.gwt.pectin.client.value.ValueModelFunction;
import com.pietschy.gwt.pectin.client.value.ValueModel;

/**
 * Created by IntelliJ IDEA.
 * User: andrew
 * Date: Jul 20, 2009
 * Time: 11:59:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class AndCondition
extends ValueModelFunction<Boolean, Boolean>
implements Condition
{
   public AndCondition()
   {
      super(new AndFunction());
   }
   
   public Condition and(ValueModel<Boolean> condition, ValueModel<Boolean>... others)
   {
      return Conditions.and(this, condition, others);
   }

   public Condition or(ValueModel<Boolean> condition, ValueModel<Boolean>... others)
   {
      return Conditions.or(this, condition, others);
   }

   public Condition not()
   {
      return Conditions.not(this);
   }
}