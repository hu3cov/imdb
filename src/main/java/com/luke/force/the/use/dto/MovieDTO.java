package com.luke.force.the.use.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO
{
    private Long id;
    
    private String name;
    
    private String description;
    
    private String releaseDate;
    
    private Integer duration;
    
    private List<GenreDTO> genres;
    
    private List<ActorDTO> actors;
}