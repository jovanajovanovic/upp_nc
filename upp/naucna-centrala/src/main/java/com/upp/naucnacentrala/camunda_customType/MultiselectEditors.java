package com.upp.naucnacentrala.camunda_customType;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.impl.form.type.SimpleFormFieldType;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiselectEditors  extends SimpleFormFieldType {
    public final static String TYPE_NAME = "multiselectE";

    protected Map<String, String> values;

    public MultiselectEditors() {
        this.values = new HashMap<>();
    }

    public MultiselectEditors(Map<String, String> values) {
        this.values = values;
    }

    @Override
    protected TypedValue convertValue(TypedValue typedValue) {
        Object value = typedValue.getValue();
        if(value == null || List.class.isInstance(value)){
            validateValue(value);
            return Variables.untypedValue(value, typedValue.isTransient());
        }else {
            throw new ProcessEngineException("Value '"+value+"' is not of type list of strings.");

        }
    }

    private void validateValue(Object value) {
        if(value != null){
            ArrayList.class.cast(value);
            for (String s: (ArrayList<String> )value ) {
                if (values != null && !values.containsKey(s)){
                    throw new ProcessEngineException("Invalid value for multiselect form property: " + value);
                }
            }
        }
    }

    @Override
    public String getName() {
        return TYPE_NAME;
    }

    @Override
    public Object convertFormValueToModelValue(Object o) {
        validateValue(o);
        return o;
    }

    @Override
    public String convertModelValueToFormValue(Object o) {
        if (o != null){
            if (!(o instanceof String)){
                throw new ProcessEngineException("Model value should be a String");
            }
            validateValue(o);
        }
        return (String)o;
    }

    public Map<String, String> getValues() {
        return values;
    }
}
