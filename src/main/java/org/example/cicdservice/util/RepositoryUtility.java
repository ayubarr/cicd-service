package org.example.cicdservice.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RepositoryUtility {
    public String extractRepoName(String repoUrl) {
        return repoUrl.substring(repoUrl.lastIndexOf('/') + 1);
    }
}
