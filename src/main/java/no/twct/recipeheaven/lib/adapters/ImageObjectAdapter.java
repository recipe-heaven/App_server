package no.twct.recipeheaven.lib.adapters;

import no.twct.recipeheaven.lib.resource.Image;

import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.bind.adapter.JsonbAdapter;

/**
 * Adapter to perform conversion on Image objects to/from JSON.
 */
public class ImageObjectAdapter implements JsonbAdapter<Set<Image>, JsonArray> {

	/**
	 * Extracts the ID of all images in a set to a JSON array containing all the
	 * Id´s
	 */
	@Override
	public JsonArray adaptToJson(Set<Image> mos) throws Exception {
		JsonArrayBuilder result = Json.createArrayBuilder();
		
		if (mos == null) {
			return result.build();
		}
		mos.forEach(mo -> result.add(mo.getId()));
		return result.build();
	}

	/**
	 * Not implemented
	 */
	@Override
	public Set<Image> adaptFromJson(JsonArray mediaid) throws Exception {
		return null;
	}
}
