package br.com.iondax.controller.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "DecimalConverter")
public class DecimalConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		return value;
		// return DecimalUtils.convert(value);
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		if (value instanceof BigDecimal) {
			// return DecimalUtils.format((BigDecimal) value);
		}

		return null;
	}

}
