package org.example.cicdservice.web.dto.operation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScriptOperationDTO extends OperationDTO {
    private String command;
    private String directory;
}
