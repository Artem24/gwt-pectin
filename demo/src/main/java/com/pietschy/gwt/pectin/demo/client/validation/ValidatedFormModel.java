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

package com.pietschy.gwt.pectin.demo.client.validation;

import com.google.gwt.core.client.GWT;
import com.pietschy.gwt.pectin.client.bean.BeanModelProvider;
import com.pietschy.gwt.pectin.client.command.AbstractUiCommand;
import com.pietschy.gwt.pectin.client.command.UiCommand;
import com.pietschy.gwt.pectin.client.form.FieldModel;
import com.pietschy.gwt.pectin.client.form.FormModel;
import com.pietschy.gwt.pectin.client.form.FormattedFieldModel;
import com.pietschy.gwt.pectin.client.form.ListFieldModel;
import com.pietschy.gwt.pectin.client.form.validation.FieldValidator;
import com.pietschy.gwt.pectin.client.form.validation.message.ErrorMessage;
import com.pietschy.gwt.pectin.client.form.validation.validator.NotEmptyValidator;
import com.pietschy.gwt.pectin.client.form.validation.validator.NotNullValidator;
import com.pietschy.gwt.pectin.demo.client.domain.Gender;
import com.pietschy.gwt.pectin.demo.client.domain.Person;
import com.pietschy.gwt.pectin.demo.client.domain.Wine;
import com.pietschy.gwt.pectin.demo.client.misc.AgeFormat;

import static com.pietschy.gwt.pectin.client.form.metadata.MetadataPlugin.enable;
import static com.pietschy.gwt.pectin.client.form.validation.ValidationPlugin.getValidationManager;
import static com.pietschy.gwt.pectin.client.form.validation.ValidationPlugin.validateField;

/**
 *
 */
public class ValidatedFormModel extends FormModel
{
   
   public static abstract class PersonProvider extends BeanModelProvider<Person> {}
   private PersonProvider personProvider = GWT.create(PersonProvider.class);

   protected final FieldModel<String> givenName;
   protected final FieldModel<String> surname;
   protected final FieldModel<String> nickName;
   protected final FieldModel<Boolean> hasNickName;
   protected final FieldModel<Gender> gender;
   protected final FormattedFieldModel<Integer> age;
   protected final FieldModel<Boolean> wineLover;
   protected final ListFieldModel<Wine> favoriteWines;

   // click handlers for our form (since we like passive views).
   protected final UiCommand validateCommand = new ValidateCommand();
   protected final UiCommand clearCommand = new ClearValidationCommand();
   protected final UiCommand fakeServerErrorCommand = new FakeServerErrorCommand();

   public ValidatedFormModel()
   {
      givenName = fieldOfType(String.class).boundTo(personProvider, "givenName");
      surname = fieldOfType(String.class).boundTo(personProvider, "surname");
      age = formattedFieldOfType(Integer.class)
         .using(new AgeFormat())
         .boundTo(personProvider, "age");
      gender = fieldOfType(Gender.class).boundTo(personProvider, "gender");

      hasNickName = fieldOfType(Boolean.class).createWithValue(false);
      nickName = fieldOfType(String.class).boundTo(personProvider, "nickName");

      wineLover = fieldOfType(Boolean.class).boundTo(personProvider, "wineLover");
      favoriteWines = listOfType(Wine.class).boundTo(personProvider, "favoriteWines");

      // configure our validation rules
      validateField(givenName).using(new NotEmptyValidator("Given name is required"));
      validateField(surname).using(new NotEmptyValidator("Surname is required"));
      validateField(gender).using(new NotNullValidator("Gender is required"));

      // we only validate the nickname if the user has clicked that they have one.
      validateField(nickName)
         .using(new NotEmptyValidator("Please enter your nick name"))
         .when(hasNickName);

      // we use the AgeFormat as the first line of validation
      validateField(age).usingFieldFormat();
      validateField(age).using(new NotNullValidator("Age is required"));

      // we only validate
      validateField(favoriteWines)
         .using(new WineListValidator())
         .when(wineLover);

      // configure our metadata
      enable(nickName).when(hasNickName);
      enable(favoriteWines).when(wineLover);
   }

   public boolean validate()
   {
      return getValidationManager(this).validate();
   }

   public void commit()
   {
      personProvider.commit();
   }

   public void setPerson(Person person)
   {
      personProvider.setValue(person);
   }

   private class ValidateCommand extends AbstractUiCommand
   {
      @Override
      protected void doExecute()
      {
         validate();
      }
   }

   private class ClearValidationCommand extends AbstractUiCommand
   {
      @Override
      protected void doExecute()
      {
         getValidationManager(ValidatedFormModel.this).clear();
      }
   }

   private class FakeServerErrorCommand extends AbstractUiCommand
   {
      @Override
      protected void doExecute()
      {
         FieldValidator givenNameValidator = getValidationManager(ValidatedFormModel.this).getValidator(givenName);
         // normally this would come back async from the server and get injected.
         givenNameValidator.addExternalMessage(new ErrorMessage("The server didn't like your name.."));
      }
   }
}