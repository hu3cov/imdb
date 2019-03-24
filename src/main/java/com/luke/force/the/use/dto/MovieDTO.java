package com.luke.force.the.use.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public final class MovieDTO
{
    private Long id;
    
    @ApiModelProperty(value = "Movie title")
    private String name;
    
    @ApiModelProperty(value = "Movie description")
    private String description;
    
    @ApiModelProperty(value = "Realease date of Movie in ISO_LOCAL_DATE format")
    private String releaseDate;
    
    @ApiModelProperty(value = "Movie duration in minutes")
    private Integer duration;
    
    private List<GenreDTO> genres;
    
    private List<ActorDTO> actors;
    
    public MovieDTO() {}
    
    public MovieDTO(Builder builder) 
    { 
        this.id = builder.id; 
        this.name = builder.name;
        this.description = builder.description;
        this.releaseDate = builder.releaseDate;
        this.duration = builder.duration;
        this.genres = builder.genres;
        this.actors = builder.actors;
    }

	public Long getId() 
	{
		return id;
	}

	public String getName() 
	{
		return name;
	}

	public String getDescription() 
	{
		return description;
	}

	public String getReleaseDate() 
	{
		return releaseDate;
	}

	public Integer getDuration() 
	{
		return duration;
	}

	public List<GenreDTO> getGenres() 
	{
		return genres;
	}

	public List<ActorDTO> getActors() 
	{
		return actors;
	}

	// Static class Builder 
    public static class Builder 
    { 
        /// instance fields 
        private Long id; 
        private String name;
        private String description;
        private String releaseDate;
        private Integer duration;
        private List<GenreDTO> genres;
        private List<ActorDTO> actors;
  
        public static Builder newInstance() 
        { 
            return new Builder(); 
        } 
  
        private Builder() {} 
  
        // Setter methods 
        public Builder id(Long id) 
        { 
            this.id = id; 
            return this; 
        } 
        
        public Builder name(String name) 
        { 
            this.name = name; 
            return this; 
        }
        
        public Builder description(String description) 
        { 
            this.description = description; 
            return this; 
        } 
        
        public Builder releaseDate(String releaseDate) 
        { 
            this.releaseDate = releaseDate; 
            return this; 
        } 
        
        public Builder duration(Integer duration) 
        { 
            this.duration = duration; 
            return this; 
        } 
        
        public Builder genres(List<GenreDTO> genres) 
        { 
            this.genres = genres; 
            return this; 
        } 
        
        public Builder actors(List<ActorDTO> actors) 
        { 
            this.actors = actors; 
            return this; 
        } 
  
        // build method to deal with outer class 
        public MovieDTO build() 
        { 
            return new MovieDTO(this); 
        } 
    } 
}