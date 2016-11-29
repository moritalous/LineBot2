
package forest.rice.field.k.linebot.function01.itunes.topchart;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "im:id",
    "term",
    "scheme",
    "label"
})
public class Attributes__________ {

    @JsonProperty("im:id")
    private String imId;
    @JsonProperty("term")
    private String term;
    @JsonProperty("scheme")
    private String scheme;
    @JsonProperty("label")
    private String label;

    /**
     * 
     * @return
     *     The imId
     */
    @JsonProperty("im:id")
    public String getImId() {
        return imId;
    }

    /**
     * 
     * @param imId
     *     The im:id
     */
    @JsonProperty("im:id")
    public void setImId(String imId) {
        this.imId = imId;
    }

    /**
     * 
     * @return
     *     The term
     */
    @JsonProperty("term")
    public String getTerm() {
        return term;
    }

    /**
     * 
     * @param term
     *     The term
     */
    @JsonProperty("term")
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 
     * @return
     *     The scheme
     */
    @JsonProperty("scheme")
    public String getScheme() {
        return scheme;
    }

    /**
     * 
     * @param scheme
     *     The scheme
     */
    @JsonProperty("scheme")
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    /**
     * 
     * @return
     *     The label
     */
    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

}
