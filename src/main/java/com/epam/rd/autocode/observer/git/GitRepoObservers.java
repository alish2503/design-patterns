package com.epam.rd.autocode.observer.git;

import java.util.*;

public class GitRepoObservers {
    public static Repository newRepository(){
        return new RepositoryImpl();
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return new WebHookImpl(Event.Type.MERGE, branchName);
    }

    public static WebHook commitToBranchWebHook(String branchName){
        return new WebHookImpl(Event.Type.COMMIT, branchName);
    }

    private static class RepositoryImpl implements Repository {
        private final ArrayList<WebHook> webHooks;
        private final HashMap<String, ArrayList<Commit>> branches;

        public RepositoryImpl() {
            webHooks = new ArrayList<>();
            branches = new HashMap<>();
        }
        @Override
        public void addWebHook(WebHook webHook) {
            webHooks.add(webHook);
        }

        @Override
        public Commit commit(String branch, String author, String[] changes) {
            Commit newCommit = new Commit(author, changes);
            branches.computeIfAbsent(branch, b -> new ArrayList<>()).add(newCommit);
            for (WebHook webHook : webHooks) {
                webHook.onEvent(new Event(Event.Type.COMMIT, branch, Collections.singletonList(newCommit)));
            }
            return newCommit;
        }

        @Override
        public void merge(String sourceBranch, String targetBranch) {
            List<Commit> sourceCommits = branches.getOrDefault(sourceBranch, new ArrayList<>());
            List<Commit> targetCommits = branches.computeIfAbsent(targetBranch, k -> new ArrayList<>());
            List<Commit> newCommits = new ArrayList<>();
            Set<Commit> SetOfCommits = new HashSet<>(targetCommits);
            for (Commit commit : sourceCommits) {
                if (!SetOfCommits.contains(commit)) {
                    newCommits.add(commit);
                }
            }
            targetCommits.addAll(newCommits);
            if (!newCommits.isEmpty()) {
                for (WebHook webHook : webHooks) {
                    webHook.onEvent(new Event(Event.Type.MERGE, targetBranch, newCommits));
                }
            }
        }
    }
    private static class WebHookImpl implements WebHook {
        private final Event.Type type;
        private final String branch;
        private final ArrayList<Event> events;

        public WebHookImpl(Event.Type type, String branch) {
            this.type = type;
            this.branch = branch;
            events = new ArrayList<>();
        }

        @Override
        public void onEvent(Event event) {
            if (event.type() == type && event.branch().equals(branch)) {
                events.add(event);
            }
        }

        @Override
        public List<Event> caughtEvents() {
            return events;
        }

        @Override
        public String branch() {
            return branch;
        }

        @Override
        public Event.Type type() {
            return type;
        }
    }
}
