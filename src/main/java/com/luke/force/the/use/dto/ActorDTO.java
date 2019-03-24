package com.luke.force.the.use.dto;

import io.swagger.annotations.ApiModelProperty;

public final class ActorDTO
{
    private Long id;
    
    @ApiModelProperty(value = "Full name of Actor")
    private String fullName;
    
    @ApiModelProperty(value = "Birth date of Actor")
    private String birthDate;
    
    public ActorDTO() {}
    
    public ActorDTO(Builder builder) 
    { 
        this.id = builder.id; 
        this.fullName = builder.fullName; 
        this.birthDate = builder.birthDate; 
    }
    
    public Long getId() 
    {
		return id;
	}

	public String getFullName() 
	{
		return fullName;
	}

	public String getBirthDate() 
	{
		return birthDate;
	}

	// Static class Builder 
    public static class Builder 
    { 
        /// instance fields 
        private Long id; 
        private String fullName; 
        private String birthDate; 
  
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
        
        public Builder fullName(String fullName) 
        { 
            this.fullName = fullName; 
            return this; 
        } 
        
        public Builder birthDate(String birthDate) 
        { 
            this.birthDate = birthDate; 
            return this; 
        } 
  
        // build method to deal with outer class 
        public ActorDTO build() 
        { 
            return new ActorDTO(this); 
        } 
    } 
}
