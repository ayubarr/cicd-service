package org.example.cicdservice.web.dto;

import lombok.Data;
import org.example.cicdservice.web.dto.operation.OperationDTO;

import java.util.List;

@Data
public class PipelineDTO {
    private String branchName;
    private List<OperationDTO> operations;
}
