package br.com.juniorbarbosa.uteis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
 * Classe responsável por converter a data e a hora conforme necessário
 * 
 * @author Joao Carlos Barbosa Junior - 11 de nov de 2016 - 22:14:55
 */
@FacesConverter(value = LocalDateTimeConverter.ID)
public class LocalDateTimeConverter extends DateTimeConverter {

	public static final String ID = "br.com.juniorbarbosa.uteis.LocalDateTimeConverter";

	/**
	 * Método pega a timezone do usuário
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		Date date = null;
		LocalDateTime localDateTime = null;
		Object object = super.getAsObject(facesContext, uiComponent, value);

		if (object instanceof Date) {
			date = (Date) object;

			Instant instant = Instant.ofEpochMilli(date.getTime());
			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			return localDateTime;
		} else {
			throw new IllegalArgumentException(String.format("value=%s Não foi possível converter LocalDateTime, resultado super.getAsObject=%s", value, object));
		}
	}

	/**
	 * Método vai transformar em String esses valores de data e hora
	 */
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value == null) {
			return super.getAsString(facesContext, uiComponent, value);
		}
		if (value instanceof LocalDateTime) {
			LocalDateTime localDateTime = (LocalDateTime) value;
			Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
			Date date = Date.from(instant);
			return super.getAsString(facesContext, uiComponent, date);
		} else {
			throw new IllegalArgumentException(String.format("value=%s não é um LocalDateTime", value));
		}
	}

}
