package com.tensionsoft.buber.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.util.Collection;


public class BeansUtil<T> {
    public T copyNonNullProperties(T to, T from) {
        if (from == null || to == null || to.getClass() != from.getClass()) return null;

        final BeanWrapper beanFrom = new BeanWrapperImpl(from);
        final BeanWrapper beanTo = new BeanWrapperImpl(to);

        for (final Field property : to.getClass().getDeclaredFields()) {
            Object providedObject = beanFrom.getPropertyValue(property.getName());
            if (providedObject != null && !(providedObject instanceof Collection<?>)) {
                beanTo.setPropertyValue(
                        property.getName(),
                        providedObject);
            }
        }
        return to;
    }
}
