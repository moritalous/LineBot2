
package forest.rice.field.k.linebot.function01.itunes.topchart;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "im:name",
    "link",
    "im:contentType"
})
public class ImCollection {

    @JsonProperty("im:name")
    private ImName_ imName;
    @JsonProperty("link")
    private Link link;
    @JsonProperty("im:contentType")
    private ImContentType imContentType;

    /**
     * 
     * @return
     *     The imName
     */
    @JsonProperty("im:name")
    public ImName_ getImName() {
        return imName;
    }

    /**
     * 
     * @param imName
     *     The im:name
     */
    @JsonProperty("im:name")
    public void setImName(ImName_ imName) {
        this.imName = imName;
    }

    /**
     * 
     * @return
     *     The link
     */
    @JsonProperty("link")
    public Link getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    @JsonProperty("link")
    public void setLink(Link link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The imContentType
     */
    @JsonProperty("im:contentType")
    public ImContentType getImContentType() {
        return imContentType;
    }

    /**
     * 
     * @param imContentType
     *     The im:contentType
     */
    @JsonProperty("im:contentType")
    public void setImContentType(ImContentType imContentType) {
        this.imContentType = imContentType;
    }

}
