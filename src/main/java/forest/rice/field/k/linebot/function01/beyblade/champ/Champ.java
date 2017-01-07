package forest.rice.field.k.linebot.function01.beyblade.champ;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "sEcho", "iTotalRecords", "iTotalDisplayRecords", "aaData" })
public class Champ {

	@JsonProperty("sEcho")
	private String sEcho;
	@JsonProperty("iTotalRecords")
	private String iTotalRecords;
	@JsonProperty("iTotalDisplayRecords")
	private String iTotalDisplayRecords;
	@JsonProperty("aaData")
	private List<List<String>> aaData = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("sEcho")
	public String getSEcho() {
		return sEcho;
	}

	@JsonProperty("sEcho")
	public void setSEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	@JsonProperty("iTotalRecords")
	public String getITotalRecords() {
		return iTotalRecords;
	}

	@JsonProperty("iTotalRecords")
	public void setITotalRecords(String iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	@JsonProperty("iTotalDisplayRecords")
	public String getITotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	@JsonProperty("iTotalDisplayRecords")
	public void setITotalDisplayRecords(String iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	@JsonProperty("aaData")
	public List<List<String>> getAaData() {
		return aaData;
	}

	@JsonProperty("aaData")
	public void setAaData(List<List<String>> aaData) {
		this.aaData = aaData;
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