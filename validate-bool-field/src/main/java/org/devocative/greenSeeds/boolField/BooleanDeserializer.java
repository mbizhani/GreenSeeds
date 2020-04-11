package org.devocative.greenSeeds.boolField;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class BooleanDeserializer extends JsonDeserializer<Boolean> {

	@Override
	public Boolean deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		final String value = parser.readValueAs(String.class);

		switch (value.trim().toLowerCase()) {
			case "1":
			case "true":
				return true;

			case "0":
			case "false":
				return false;

			default:
				//TIP: only throwing following class results in 400 (bad request), otherwise 500 (internal server error)
				throw new InvalidFormatException(parser, "Invalid Boolean value: " + value, value, Boolean.class);
		}

	}
}
