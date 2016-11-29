
package forest.rice.field.k.linebot.function01.itunes.topchart;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "attributes",
    "im:duration"
})
public class Link_ {

    @JsonProperty("attributes")
    private Attributes_______ attributes;
    @JsonProperty("im:duration")
    private ImDuration imDuration;

    /**
     * 
     * @return
     *     The attributes
     */
    @JsonProperty("attributes")
    public Attributes_______ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    @JsonProperty("attributes")
    public void setAttributes(Attributes_______ attributes) {
        this.attributes = attributes;
    }

    /**
     * 
     * @return
     *     The imDuration
     */
    @JsonProperty("im:duration")
    public ImDuration getImDuration() {
        return imDuration;
    }

    /**
     * 
     * @param imDuration
     *     The im:duration
     */
    @JsonProperty("im:duration")
    public void setImDuration(ImDuration imDuration) {
        this.imDuration = imDuration;
    }

}
