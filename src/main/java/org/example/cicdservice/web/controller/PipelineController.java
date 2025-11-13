package org.example.cicdservice.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.example.cicdservice.data.service.PipelineService;
import org.example.cicdservice.exception.IncorrectPipelineConfigException;
import org.example.cicdservice.web.dto.PipelineExecuteRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@RestController
public class PipelineController {

    private final PipelineService pipelineService;

    @PostMapping("/api/pipeline/execute")
    public void executePipeline(@RequestBody PipelineExecuteRequestDTO body) throws GitAPIException, IOException, IncorrectPipelineConfigException, InterruptedException {
        pipelineService.executeIfBranchPipelineExist(body.getRepositoryUrl(), body.getBranch());
    }
}
