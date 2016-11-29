
package forest.rice.field.k.linebot.function01.itunes.topchart;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "im:contentType",
    "attributes"
})
public class ImContentType__ {

    @JsonProperty("im:contentType")
    private ImContentType___ imContentType;
    @JsonProperty("attributes")
    private Attributes______ attributes;

    /**
     * 
     * @return
     *     The imContentType
     */
    @JsonProperty("im:contentType")
    public ImContentType___ getImContentType() {
        return imContentType;
    }

    /**
     * 
     * @param imContentType
     *     The im:contentType
     */
    @JsonProperty("im:contentType")
    public void setImContentType(ImContentType___ imContentType) {
        this.imContentType = imContentType;
    }

    /**
     * 
     * @return
     *     The attributes
     */
    @JsonProperty("attributes")
    public Attributes______ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    @JsonProperty("attributes")
    public void setAttributes(Attributes______ attributes) {
        this.attributes = attributes;
    }

}
