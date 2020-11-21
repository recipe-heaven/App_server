package no.twct.recipeheaven.resources.control;

import no.twct.recipeheaven.lib.CreatableBase;
import org.eclipse.persistence.indirection.IndirectList;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;
import javax.json.bind.annotation.JsonbTypeSerializer;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UserStatusStarsAdapter implements JsonbAdapter<List<CreatableBase>, JsonArray> {


    /**
     * This method is used on serialization only. It contains a conversion logic from type Original to type Adapted.
     * After conversion Adapted type will be mapped to JSON the standard way.
     *
     * @param obj Object to convert.
     *
     * @return Converted object which will be serialized to JSON.
     * @throws Exception if there is an error during the conversion.
     */
    @Override
    public JsonArray adaptToJson(List<CreatableBase> obj) throws Exception {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        obj.forEach(creatableBase -> builder.add(creatableBase.getId()));
        return builder.build();
    }

    /**
     * This method is used on deserialization only. It contains a conversion logic from type Adapted to type Original.
     *
     * @param obj Object to convert.
     *
     * @return Converted object representing pojo to be set into object graph.
     * @throws Exception if there is an error during the conversion.
     */
    @Override
    public List<CreatableBase> adaptFromJson(JsonArray obj) throws Exception {
        return null;
    }
}
