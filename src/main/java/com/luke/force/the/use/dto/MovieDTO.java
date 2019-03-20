package com.luke.force.the.use.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO
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
}