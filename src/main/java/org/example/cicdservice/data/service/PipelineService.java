package org.example.cicdservice.data.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.example.cicdservice.exception.IncorrectPipelineConfigException;
import org.example.cicdservice.web.dto.PipelineDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class PipelineService {

    private final GitRepositoryService repositoryService;

    public void executeIfBranchPipelineExist(String repoUrl, String branch)
            throws GitAPIException, IOException, IncorrectPipelineConfigException {

        Path repositoryPath = repositoryService.download(repoUrl, branch);
        List<PipelineDTO> pipelineList = repositoryService.getPipelineConfig(repositoryPath);

        Optional<PipelineDTO> optional = pipelineList.stream()
                .filter(p -> p.getBranchName().equals(branch)).findFirst();

        if (optional.isEmpty()) {
            log.info("No pipeline found for branch {}", branch);
            return;
        }

        PipelineDTO pipeline = optional.get();
        executePipeline(pipeline, repositoryPath);
    }

    private void executePipeline(PipelineDTO pipeline, Path repositoryPath) {

    }
}
