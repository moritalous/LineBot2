
package forest.rice.field.k.linebot.function01.itunes.tracksearch;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "wrapperType",
    "kind",
    "artistId",
    "collectionId",
    "trackId",
    "artistName",
    "collectionName",
    "trackName",
    "collectionCensoredName",
    "trackCensoredName",
    "artistViewUrl",
    "collectionViewUrl",
    "trackViewUrl",
    "previewUrl",
    "artworkUrl30",
    "artworkUrl60",
    "artworkUrl100",
    "collectionPrice",
    "trackPrice",
    "releaseDate",
    "collectionExplicitness",
    "trackExplicitness",
    "discCount",
    "discNumber",
    "trackCount",
    "trackNumber",
    "trackTimeMillis",
    "country",
    "currency",
    "primaryGenreName",
    "isStreamable"
})
public class Result {

    @JsonProperty("wrapperType")
    private String wrapperType;
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("artistId")
    private Integer artistId;
    @JsonProperty("collectionId")
    private Integer collectionId;
    @JsonProperty("trackId")
    private Integer trackId;
    @JsonProperty("artistName")
    private String artistName;
    @JsonProperty("collectionName")
    private String collectionName;
    @JsonProperty("trackName")
    private String trackName;
    @JsonProperty("collectionCensoredName")
    private String collectionCensoredName;
    @JsonProperty("trackCensoredName")
    private String trackCensoredName;
    @JsonProperty("artistViewUrl")
    private String artistViewUrl;
    @JsonProperty("collectionViewUrl")
    private String collectionViewUrl;
    @JsonProperty("trackViewUrl")
    private String trackViewUrl;
    @JsonProperty("previewUrl")
    private String previewUrl;
    @JsonProperty("artworkUrl30")
    private String artworkUrl30;
    @JsonProperty("artworkUrl60")
    private String artworkUrl60;
    @JsonProperty("artworkUrl100")
    private String artworkUrl100;
    @JsonProperty("collectionPrice")
    private Integer collectionPrice;
    @JsonProperty("trackPrice")
    private Integer trackPrice;
    @JsonProperty("releaseDate")
    private String releaseDate;
    @JsonProperty("collectionExplicitness")
    private String collectionExplicitness;
    @JsonProperty("trackExplicitness")
    private String trackExplicitness;
    @JsonProperty("discCount")
    private Integer discCount;
    @JsonProperty("discNumber")
    private Integer discNumber;
    @JsonProperty("trackCount")
    private Integer trackCount;
    @JsonProperty("trackNumber")
    private Integer trackNumber;
    @JsonProperty("trackTimeMillis")
    private Integer trackTimeMillis;
    @JsonProperty("country")
    private String country;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("primaryGenreName")
    private String primaryGenreName;
    @JsonProperty("isStreamable")
    private Boolean isStreamable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The wrapperType
     */
    @JsonProperty("wrapperType")
    public String getWrapperType() {
        return wrapperType;
    }

    /**
     * 
     * @param wrapperType
     *     The wrapperType
     */
    @JsonProperty("wrapperType")
    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    /**
     * 
     * @return
     *     The kind
     */
    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    /**
     * 
     * @param kind
     *     The kind
     */
    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * 
     * @return
     *     The artistId
     */
    @JsonProperty("artistId")
    public Integer getArtistId() {
        return artistId;
    }

    /**
     * 
     * @param artistId
     *     The artistId
     */
    @JsonProperty("artistId")
    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    /**
     * 
     * @return
     *     The collectionId
     */
    @JsonProperty("collectionId")
    public Integer getCollectionId() {
        return collectionId;
    }

    /**
     * 
     * @param collectionId
     *     The collectionId
     */
    @JsonProperty("collectionId")
    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    /**
     * 
     * @return
     *     The trackId
     */
    @JsonProperty("trackId")
    public Integer getTrackId() {
        return trackId;
    }

    /**
     * 
     * @param trackId
     *     The trackId
     */
    @JsonProperty("trackId")
    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    /**
     * 
     * @return
     *     The artistName
     */
    @JsonProperty("artistName")
    public String getArtistName() {
        return artistName;
    }

    /**
     * 
     * @param artistName
     *     The artistName
     */
    @JsonProperty("artistName")
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * 
     * @return
     *     The collectionName
     */
    @JsonProperty("collectionName")
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * 
     * @param collectionName
     *     The collectionName
     */
    @JsonProperty("collectionName")
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * 
     * @return
     *     The trackName
     */
    @JsonProperty("trackName")
    public String getTrackName() {
        return trackName;
    }

    /**
     * 
     * @param trackName
     *     The trackName
     */
    @JsonProperty("trackName")
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    /**
     * 
     * @return
     *     The collectionCensoredName
     */
    @JsonProperty("collectionCensoredName")
    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    /**
     * 
     * @param collectionCensoredName
     *     The collectionCensoredName
     */
    @JsonProperty("collectionCensoredName")
    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    /**
     * 
     * @return
     *     The trackCensoredName
     */
    @JsonProperty("trackCensoredName")
    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    /**
     * 
     * @param trackCensoredName
     *     The trackCensoredName
     */
    @JsonProperty("trackCensoredName")
    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    /**
     * 
     * @return
     *     The artistViewUrl
     */
    @JsonProperty("artistViewUrl")
    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    /**
     * 
     * @param artistViewUrl
     *     The artistViewUrl
     */
    @JsonProperty("artistViewUrl")
    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    /**
     * 
     * @return
     *     The collectionViewUrl
     */
    @JsonProperty("collectionViewUrl")
    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    /**
     * 
     * @param collectionViewUrl
     *     The collectionViewUrl
     */
    @JsonProperty("collectionViewUrl")
    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    /**
     * 
     * @return
     *     The trackViewUrl
     */
    @JsonProperty("trackViewUrl")
    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    /**
     * 
     * @param trackViewUrl
     *     The trackViewUrl
     */
    @JsonProperty("trackViewUrl")
    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    /**
     * 
     * @return
     *     The previewUrl
     */
    @JsonProperty("previewUrl")
    public String getPreviewUrl() {
        return previewUrl;
    }

    /**
     * 
     * @param previewUrl
     *     The previewUrl
     */
    @JsonProperty("previewUrl")
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /**
     * 
     * @return
     *     The artworkUrl30
     */
    @JsonProperty("artworkUrl30")
    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    /**
     * 
     * @param artworkUrl30
     *     The artworkUrl30
     */
    @JsonProperty("artworkUrl30")
    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    /**
     * 
     * @return
     *     The artworkUrl60
     */
    @JsonProperty("artworkUrl60")
    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    /**
     * 
     * @param artworkUrl60
     *     The artworkUrl60
     */
    @JsonProperty("artworkUrl60")
    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    /**
     * 
     * @return
     *     The artworkUrl100
     */
    @JsonProperty("artworkUrl100")
    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    /**
     * 
     * @param artworkUrl100
     *     The artworkUrl100
     */
    @JsonProperty("artworkUrl100")
    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    /**
     * 
     * @return
     *     The collectionPrice
     */
    @JsonProperty("collectionPrice")
    public Integer getCollectionPrice() {
        return collectionPrice;
    }

    /**
     * 
     * @param collectionPrice
     *     The collectionPrice
     */
    @JsonProperty("collectionPrice")
    public void setCollectionPrice(Integer collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    /**
     * 
     * @return
     *     The trackPrice
     */
    @JsonProperty("trackPrice")
    public Integer getTrackPrice() {
        return trackPrice;
    }

    /**
     * 
     * @param trackPrice
     *     The trackPrice
     */
    @JsonProperty("trackPrice")
    public void setTrackPrice(Integer trackPrice) {
        this.trackPrice = trackPrice;
    }

    /**
     * 
     * @return
     *     The releaseDate
     */
    @JsonProperty("releaseDate")
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * 
     * @param releaseDate
     *     The releaseDate
     */
    @JsonProperty("releaseDate")
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 
     * @return
     *     The collectionExplicitness
     */
    @JsonProperty("collectionExplicitness")
    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    /**
     * 
     * @param collectionExplicitness
     *     The collectionExplicitness
     */
    @JsonProperty("collectionExplicitness")
    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    /**
     * 
     * @return
     *     The trackExplicitness
     */
    @JsonProperty("trackExplicitness")
    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    /**
     * 
     * @param trackExplicitness
     *     The trackExplicitness
     */
    @JsonProperty("trackExplicitness")
    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }

    /**
     * 
     * @return
     *     The discCount
     */
    @JsonProperty("discCount")
    public Integer getDiscCount() {
        return discCount;
    }

    /**
     * 
     * @param discCount
     *     The discCount
     */
    @JsonProperty("discCount")
    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }

    /**
     * 
     * @return
     *     The discNumber
     */
    @JsonProperty("discNumber")
    public Integer getDiscNumber() {
        return discNumber;
    }

    /**
     * 
     * @param discNumber
     *     The discNumber
     */
    @JsonProperty("discNumber")
    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }

    /**
     * 
     * @return
     *     The trackCount
     */
    @JsonProperty("trackCount")
    public Integer getTrackCount() {
        return trackCount;
    }

    /**
     * 
     * @param trackCount
     *     The trackCount
     */
    @JsonProperty("trackCount")
    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    /**
     * 
     * @return
     *     The trackNumber
     */
    @JsonProperty("trackNumber")
    public Integer getTrackNumber() {
        return trackNumber;
    }

    /**
     * 
     * @param trackNumber
     *     The trackNumber
     */
    @JsonProperty("trackNumber")
    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     * 
     * @return
     *     The trackTimeMillis
     */
    @JsonProperty("trackTimeMillis")
    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

    /**
     * 
     * @param trackTimeMillis
     *     The trackTimeMillis
     */
    @JsonProperty("trackTimeMillis")
    public void setTrackTimeMillis(Integer trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    /**
     * 
     * @return
     *     The country
     */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The currency
     */
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    /**
     * 
     * @param currency
     *     The currency
     */
    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 
     * @return
     *     The primaryGenreName
     */
    @JsonProperty("primaryGenreName")
    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    /**
     * 
     * @param primaryGenreName
     *     The primaryGenreName
     */
    @JsonProperty("primaryGenreName")
    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    /**
     * 
     * @return
     *     The isStreamable
     */
    @JsonProperty("isStreamable")
    public Boolean getIsStreamable() {
        return isStreamable;
    }

    /**
     * 
     * @param isStreamable
     *     The isStreamable
     */
    @JsonProperty("isStreamable")
    public void setIsStreamable(Boolean isStreamable) {
        this.isStreamable = isStreamable;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
