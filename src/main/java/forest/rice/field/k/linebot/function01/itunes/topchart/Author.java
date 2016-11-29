
package forest.rice.field.k.linebot.function01.itunes.topchart;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "name",
    "uri"
})
public class Author {

    @JsonProperty("name")
    private Name name;
    @JsonProperty("uri")
    private Uri uri;

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public Name getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The uri
     */
    @JsonProperty("uri")
    public Uri getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *     The uri
     */
    @JsonProperty("uri")
    public void setUri(Uri uri) {
        this.uri = uri;
    }

}
