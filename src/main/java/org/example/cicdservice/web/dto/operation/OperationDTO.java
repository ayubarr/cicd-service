package org.example.cicdservice.web.dto.operation;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.example.cicdservice.util.PipelineOperationType;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GradleOperationDTO.class, name = "GRADLE"),
        @JsonSubTypes.Type(value = ScriptOperationDTO.class, name = "SCRIPT"),
        @JsonSubTypes.Type(value = UploadOperationDTO.class, name = "UPLOAD")
})
@Data
public class OperationDTO {
    private PipelineOperationType type;
}
