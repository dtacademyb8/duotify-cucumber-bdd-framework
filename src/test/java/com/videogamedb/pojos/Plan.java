
package com.videogamedb.pojos;

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
@JsonPropertyOrder({
    "name",
    "space",
    "collaborators",
    "private_repos"
})
@Generated("jsonschema2pojo")
public class Plan {

    @JsonProperty("name")
    private String name;
    @JsonProperty("space")
    private Integer space;
    @JsonProperty("collaborators")
    private Integer collaborators;
    @JsonProperty("private_repos")
    private Integer privateRepos;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Plan() {
    }

    /**
     * 
     * @param privateRepos
     * @param name
     * @param collaborators
     * @param space
     */
    public Plan(String name, Integer space, Integer collaborators, Integer privateRepos) {
        super();
        this.name = name;
        this.space = space;
        this.collaborators = collaborators;
        this.privateRepos = privateRepos;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("space")
    public Integer getSpace() {
        return space;
    }

    @JsonProperty("space")
    public void setSpace(Integer space) {
        this.space = space;
    }

    @JsonProperty("collaborators")
    public Integer getCollaborators() {
        return collaborators;
    }

    @JsonProperty("collaborators")
    public void setCollaborators(Integer collaborators) {
        this.collaborators = collaborators;
    }

    @JsonProperty("private_repos")
    public Integer getPrivateRepos() {
        return privateRepos;
    }

    @JsonProperty("private_repos")
    public void setPrivateRepos(Integer privateRepos) {
        this.privateRepos = privateRepos;
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
