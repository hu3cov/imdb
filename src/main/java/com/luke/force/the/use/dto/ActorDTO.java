package com.luke.force.the.use.dto;

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
public class ActorDTO
{
    private Long id;
    
    private String fullName;
    
    private String birthDate;
}