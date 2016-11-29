
package forest.rice.field.k.linebot.function01.itunes.topchart;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "feed"
})
public class Music {

    @JsonProperty("feed")
    private Feed feed;

    /**
     * 
     * @return
     *     The feed
     */
    @JsonProperty("feed")
    public Feed getFeed() {
        return feed;
    }

    /**
     * 
     * @param feed
     *     The feed
     */
    @JsonProperty("feed")
    public void setFeed(Feed feed) {
        this.feed = feed;
    }

}
