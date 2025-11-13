package org.example.cicdservice.web.dto.operation;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UploadOperationDTO extends OperationDTO {
    private String globPattern;
    private String hostName;
    private String dstPath;
}
