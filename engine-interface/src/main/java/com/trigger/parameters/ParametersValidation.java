package com.trigger.parameters;

import java.lang.reflect.Field;

abstract class ParametersValidation {

    /* USING REFLECTION TO CHECK IF ALL ATTRIBUTES OF A GIVEN CLASS ARE NOT NULL. RETURNS FALSE IF AN ATTRIBUTE IS NULL*/
    public boolean validateParameters() throws IllegalAccessException {

        for (Field f : this.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(this) == null) {
                f.setAccessible(false);
                return false;
            }
            f.setAccessible(false);
        }
        return true;
    }

}
