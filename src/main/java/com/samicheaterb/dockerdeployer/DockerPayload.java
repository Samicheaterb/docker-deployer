package com.samicheaterb.dockerdeployer;
public class DockerPayload {
    private Repository repository;

    // getters and setters

    public static class Repository {
        private String repoName;

        public String getRepoName() {
            return repoName;
        }

        public void setRepoName(String repoName) {
            this.repoName = repoName;
        }

        // getters and setters
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
