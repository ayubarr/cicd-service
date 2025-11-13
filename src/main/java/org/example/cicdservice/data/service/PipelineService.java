package org.example.cicdservice.data.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.example.cicdservice.exception.IncorrectPipelineConfigException;
import org.example.cicdservice.util.PipelineOperationType;
import org.example.cicdservice.util.ProcessUtility;
import org.example.cicdservice.web.dto.PipelineDTO;
import org.example.cicdservice.web.dto.operation.GradleOperationDTO;
import org.example.cicdservice.web.dto.operation.OperationDTO;
import org.example.cicdservice.web.dto.operation.ScriptOperationDTO;
import org.example.cicdservice.web.dto.operation.UploadOperationDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class PipelineService {

    private final GitRepositoryService repositoryService;

    public void executeIfBranchPipelineExist(String repoUrl, String branch)
            throws GitAPIException, IOException, IncorrectPipelineConfigException, InterruptedException {

        Path repositoryPath = repositoryService.download(repoUrl, branch);
        List<PipelineDTO> pipelineList = repositoryService.getPipelineConfig(repositoryPath);

        Optional<PipelineDTO> optional = pipelineList.stream()
                .filter(p -> p.getBranchName().equals(branch)).findFirst();

        if (optional.isEmpty()) {
            log.info("No pipeline found for branch {}", branch);
            return;
        }

        PipelineDTO pipeline = optional.get();
        executePipeline(pipeline.getOperations(), repositoryPath);
    }

    private void executePipeline(List<OperationDTO> pipelineOperations, Path repositoryPath) throws IOException, InterruptedException {
        for (OperationDTO pipelineOperation : pipelineOperations) {
            PipelineOperationType type = pipelineOperation.getType();

            if(pipelineOperation instanceof GradleOperationDTO gradleOperationDTO) {
                runGradleOperation(gradleOperationDTO);
            } else if(pipelineOperation instanceof ScriptOperationDTO scriptOperationDTO) {
                runScriptOperation(scriptOperationDTO);
            } else if(pipelineOperation instanceof UploadOperationDTO uploadOperationDTO) {
                runUploadOperation(uploadOperationDTO);
            }
        }
    }

    private void runGradleOperation(GradleOperationDTO gradleOperation) throws InterruptedException, IOException {
        String gradleCommand =  "./gradlew";
        String taskName = gradleOperation.getTask();
        ProcessUtility.runProcess(gradleCommand, taskName);
    }

    private void runScriptOperation(ScriptOperationDTO operation){

    }

    private void runUploadOperation(UploadOperationDTO operation){

    }
}
