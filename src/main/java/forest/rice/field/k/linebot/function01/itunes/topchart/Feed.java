
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
    "author",
    "entry",
    "updated",
    "rights",
    "title",
    "icon",
    "link",
    "id"
})
public class Feed {

    @JsonProperty("author")
    private Author author;
    @JsonProperty("entry")
    private List<Entry> entry = new ArrayList<Entry>();
    @JsonProperty("updated")
    private Updated updated;
    @JsonProperty("rights")
    private Rights_ rights;
    @JsonProperty("title")
    private Title_ title;
    @JsonProperty("icon")
    private Icon icon;
    @JsonProperty("link")
    private List<Link__> link = new ArrayList<Link__>();
    @JsonProperty("id")
    private Id_ id;

    /**
     * 
     * @return
     *     The author
     */
    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    @JsonProperty("author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The entry
     */
    @JsonProperty("entry")
    public List<Entry> getEntry() {
        return entry;
    }

    /**
     * 
     * @param entry
     *     The entry
     */
    @JsonProperty("entry")
    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    /**
     * 
     * @return
     *     The updated
     */
    @JsonProperty("updated")
    public Updated getUpdated() {
        return updated;
    }

    /**
     * 
     * @param updated
     *     The updated
     */
    @JsonProperty("updated")
    public void setUpdated(Updated updated) {
        this.updated = updated;
    }

    /**
     * 
     * @return
     *     The rights
     */
    @JsonProperty("rights")
    public Rights_ getRights() {
        return rights;
    }

    /**
     * 
     * @param rights
     *     The rights
     */
    @JsonProperty("rights")
    public void setRights(Rights_ rights) {
        this.rights = rights;
    }

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public Title_ getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(Title_ title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The icon
     */
    @JsonProperty("icon")
    public Icon getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    @JsonProperty("icon")
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The link
     */
    @JsonProperty("link")
    public List<Link__> getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    @JsonProperty("link")
    public void setLink(List<Link__> link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Id_ getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Id_ id) {
        this.id = id;
    }

}
