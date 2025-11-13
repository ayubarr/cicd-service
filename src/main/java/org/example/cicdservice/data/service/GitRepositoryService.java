package org.example.cicdservice.data.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.example.cicdservice.exception.IncorrectPipelineConfigException;
import org.example.cicdservice.util.DirectoryUtility;
import org.example.cicdservice.util.RepositoryUtility;
import org.example.cicdservice.web.dto.PipelineDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
public class GitRepositoryService {
    private final Path gitRepositoriesPath;
    private final JsonMapper jsonMapper = new JsonMapper();

    public GitRepositoryService(@Value("${application.repositories.path}") String gitRepositoriesPath) {
        this.gitRepositoriesPath = Path.of(gitRepositoriesPath);
    }

    public Path download(String repoUrl, String branch) throws GitAPIException, IOException {
        Path repositoryPath = gitRepositoriesPath.resolve(RepositoryUtility.extractRepoName(repoUrl));
        DirectoryUtility.deleteDirectory(repositoryPath.toFile());
        Files.createDirectories(repositoryPath);

        log.info("Downloading repository from {}", repoUrl);
        Git git = Git.cloneRepository()
                .setURI(repoUrl)
                .setBranch(branch)
                .setDirectory(repositoryPath.toFile())
                .call();

        git.close();
        log.info("Repository successfully {} downloaded", repoUrl);

        return repositoryPath;
    }

    public List<PipelineDTO> getPipelineConfig(Path repoPath) throws IncorrectPipelineConfigException {
        Path pipelineConfigPath = repoPath.resolve("pipeline.json");

        try {
            return jsonMapper.readValue(pipelineConfigPath.toFile(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new IncorrectPipelineConfigException(e.getMessage());
        }
    }
}
