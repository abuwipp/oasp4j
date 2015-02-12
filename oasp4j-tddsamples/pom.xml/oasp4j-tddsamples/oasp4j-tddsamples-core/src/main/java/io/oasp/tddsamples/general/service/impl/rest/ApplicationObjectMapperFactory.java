package io.oasp.tddsamples.general.service.impl.rest;

import io.oasp.tddsamples.general.common.api.datatype.Money;




import io.oasp.module.rest.service.impl.json.ObjectMapperFactory;

import javax.inject.Named;

import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * The MappingFactory class to resolve polymorphic conflicts within the restaurant application.
 *
 * @author agreul
 */
@Named("ApplicationObjectMapperFactory")
public class ApplicationObjectMapperFactory extends ObjectMapperFactory {

  /**
   * The constructor.
   */
  public ApplicationObjectMapperFactory() {

    super();
    // register polymorphic base classes
    /*
    setBaseClasses(ProductEto.class);
    */

    NamedType[] subtypes;
    // register mapping for polymorphic sub-classes
    /*
    subtypes =
        new NamedType[] { new NamedType(MealEto.class, "Meal"), new NamedType(DrinkEto.class, "Drink"),
        new NamedType(SideDishEto.class, "SideDish") };
    setSubtypes(subtypes);
    */

    // register (de)serializers for custom datatypes
    SimpleModule module = getExtensionModule();
    module.addDeserializer(Money.class, new MoneyJsonDeserializer());
    module.addSerializer(Money.class, new MoneyJsonSerializer());
  }
}
