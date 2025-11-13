package org.example.cicdservice.web.dto.operation;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GradleOperationDTO.class, name = "gradle"),
        @JsonSubTypes.Type(value = ScriptOperationDTO.class, name = "script"),
        @JsonSubTypes.Type(value = UploadOperationDTO.class, name = "upload")
})
@Data
public class OperationDTO {
    private String type;
}
