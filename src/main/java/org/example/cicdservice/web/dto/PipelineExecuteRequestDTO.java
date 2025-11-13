package org.example.cicdservice.web.dto;

import lombok.Data;

@Data
public class PipelineExecuteRequestDTO {

    private String repositoryUrl;
    private String branch;
}
