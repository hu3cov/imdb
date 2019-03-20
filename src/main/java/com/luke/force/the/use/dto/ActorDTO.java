package com.luke.force.the.use.dto;

import io.swagger.annotations.ApiModelProperty;
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
    
    @ApiModelProperty(value = "Full name of Actor")
    private String fullName;
    
    @ApiModelProperty(value = "Birth date of Actor")
    private String birthDate;
}
