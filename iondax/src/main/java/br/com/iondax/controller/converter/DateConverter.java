package br.com.iondax.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.iondax.controller.utils.DataUtils;

@FacesConverter(value = "DataConverter")
public class DateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return DataUtils.dataToDate(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return DataUtils.dataToString(value);
	}

}
