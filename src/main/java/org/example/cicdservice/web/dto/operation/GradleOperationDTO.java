package org.example.cicdservice.web.dto.operation;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class GradleOperationDTO extends OperationDTO {
    private String task;
}
