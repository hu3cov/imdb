package com.luke.force.the.use.dto;

import io.swagger.annotations.ApiModelProperty;

public final class GenreDTO
{
    private Long id;
    
    @ApiModelProperty(value = "Name of Genre")
    private String name;
    
    public GenreDTO() {}
    
    public GenreDTO(Builder builder) 
    { 
        this.id = builder.id; 
        this.name = builder.name; 
    }
    
    public Long getId() 
    {
		return id;
	}

	public String getName() 
	{
		return name;
	}

	// Static class Builder 
    public static class Builder 
    { 
        /// instance fields 
        private Long id; 
        private String name; 
  
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
  
        // build method to deal with outer class 
        public GenreDTO build() 
        { 
            return new GenreDTO(this); 
        } 
    } 
}
