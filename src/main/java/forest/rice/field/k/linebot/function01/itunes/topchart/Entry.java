
package forest.rice.field.k.linebot.function01.itunes.topchart;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "im:name",
    "im:image",
    "im:collection",
    "im:price",
    "im:contentType",
    "rights",
    "title",
    "link",
    "id",
    "im:artist",
    "category",
    "im:releaseDate"
})
public class Entry {

    @JsonProperty("im:name")
    private ImName imName;
    @JsonProperty("im:image")
    private List<ImImage> imImage = new ArrayList<ImImage>();
    @JsonProperty("im:collection")
    private ImCollection imCollection;
    @JsonProperty("im:price")
    private ImPrice imPrice;
    @JsonProperty("im:contentType")
    private ImContentType__ imContentType;
    @JsonProperty("rights")
    private Rights rights;
    @JsonProperty("title")
    private Title title;
    @JsonProperty("link")
    private List<Link_> link = new ArrayList<Link_>();
    @JsonProperty("id")
    private Id id;
    @JsonProperty("im:artist")
    private ImArtist imArtist;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("im:releaseDate")
    private ImReleaseDate imReleaseDate;

    /**
     * 
     * @return
     *     The imName
     */
    @JsonProperty("im:name")
    public ImName getImName() {
        return imName;
    }

    /**
     * 
     * @param imName
     *     The im:name
     */
    @JsonProperty("im:name")
    public void setImName(ImName imName) {
        this.imName = imName;
    }

    /**
     * 
     * @return
     *     The imImage
     */
    @JsonProperty("im:image")
    public List<ImImage> getImImage() {
        return imImage;
    }

    /**
     * 
     * @param imImage
     *     The im:image
     */
    @JsonProperty("im:image")
    public void setImImage(List<ImImage> imImage) {
        this.imImage = imImage;
    }

    /**
     * 
     * @return
     *     The imCollection
     */
    @JsonProperty("im:collection")
    public ImCollection getImCollection() {
        return imCollection;
    }

    /**
     * 
     * @param imCollection
     *     The im:collection
     */
    @JsonProperty("im:collection")
    public void setImCollection(ImCollection imCollection) {
        this.imCollection = imCollection;
    }

    /**
     * 
     * @return
     *     The imPrice
     */
    @JsonProperty("im:price")
    public ImPrice getImPrice() {
        return imPrice;
    }

    /**
     * 
     * @param imPrice
     *     The im:price
     */
    @JsonProperty("im:price")
    public void setImPrice(ImPrice imPrice) {
        this.imPrice = imPrice;
    }

    /**
     * 
     * @return
     *     The imContentType
     */
    @JsonProperty("im:contentType")
    public ImContentType__ getImContentType() {
        return imContentType;
    }

    /**
     * 
     * @param imContentType
     *     The im:contentType
     */
    @JsonProperty("im:contentType")
    public void setImContentType(ImContentType__ imContentType) {
        this.imContentType = imContentType;
    }

    /**
     * 
     * @return
     *     The rights
     */
    @JsonProperty("rights")
    public Rights getRights() {
        return rights;
    }

    /**
     * 
     * @param rights
     *     The rights
     */
    @JsonProperty("rights")
    public void setRights(Rights rights) {
        this.rights = rights;
    }

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public Title getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The link
     */
    @JsonProperty("link")
    public List<Link_> getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    @JsonProperty("link")
    public void setLink(List<Link_> link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Id getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Id id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The imArtist
     */
    @JsonProperty("im:artist")
    public ImArtist getImArtist() {
        return imArtist;
    }

    /**
     * 
     * @param imArtist
     *     The im:artist
     */
    @JsonProperty("im:artist")
    public void setImArtist(ImArtist imArtist) {
        this.imArtist = imArtist;
    }

    /**
     * 
     * @return
     *     The category
     */
    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The imReleaseDate
     */
    @JsonProperty("im:releaseDate")
    public ImReleaseDate getImReleaseDate() {
        return imReleaseDate;
    }

    /**
     * 
     * @param imReleaseDate
     *     The im:releaseDate
     */
    @JsonProperty("im:releaseDate")
    public void setImReleaseDate(ImReleaseDate imReleaseDate) {
        this.imReleaseDate = imReleaseDate;
    }

}
