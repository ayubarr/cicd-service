package org.example.cicdservice.exception;

public class IncorrectPipelineConfigException extends RuntimeException {
    public IncorrectPipelineConfigException(String message) {
        super("Incorrect pipeline config: " + message);
    }
}
