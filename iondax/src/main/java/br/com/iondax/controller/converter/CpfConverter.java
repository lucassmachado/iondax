package br.com.iondax.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.iondax.controller.utils.CpfCnpjRGUtils;

@FacesConverter(value="CpfConverter")
public class CpfConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return CpfCnpjRGUtils.cpfToLong(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return CpfCnpjRGUtils.cpfToString(value);
	}
	
}
