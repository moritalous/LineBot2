
package forest.rice.field.k.linebot.function01.itunes.topchart;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "im:id"
})
public class Attributes________ {

    @JsonProperty("im:id")
    private String imId;

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

}
