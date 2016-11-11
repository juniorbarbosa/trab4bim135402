package br.com.juniorbarbosa.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Classe que converte a data para persistencia e consulta no BD
 * 
 * @author Joao Carlos Barbosa Junior - 10 de nov de 2016 - 17:19:26
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	/**
	 * Método responsável por transformar em Timestamp no momento de persistir no BD
	 */
	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
		if (localDateTime != null) {
			return Timestamp.valueOf(localDateTime);
		}
		return null;
	}

	/**
	 * Método responsável por transformar um Timestamp em LocalDateTime quando retornar do banco para a entidade
	 */
	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
		if (timestamp != null) {
			return timestamp.toLocalDateTime();
		}
		return null;
	}
}
