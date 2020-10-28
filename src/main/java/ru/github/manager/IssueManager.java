package ru.github.manager;

import ru.github.domain.Issue;
import ru.github.domain.Status;
import ru.github.domain.Tag;
import ru.github.domain.User;
import ru.github.repository.IssuesRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IssueManager {
    private IssuesRepository repository;

    public IssueManager(IssuesRepository repository) {
        this.repository = repository;
    }

    public void addIssue(Issue issue) {
        repository.add(issue);
    }

    public void updateIssueStatus(int issueId, Status status) {
        repository.updateStatus(issueId, status);
    }

    public List<Issue> sortIssueByReviewsCount() {
        return repository.getAll()
                .stream()
                .sorted(Comparator.comparingInt(Issue::getCommentsCount).reversed())
                .collect(Collectors.toList());
    }

    public List<Issue> sortIssueByDate() {
        return repository.getAll()
                .stream()
                .sorted(Comparator.comparing(Issue::getCreatedDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Issue> getIssuesByAsigneed(User asigneed) {
        return repository.getAll()
                .stream()
                .filter(issue -> issue.getAsigneed().equals(asigneed))
                .collect(Collectors.toList());
    }

    public List<Issue> getIssuesByTag(Tag tag) {
        return repository.getAll()
                .stream()
                .filter(issue -> issue.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    public List<Issue> getIssuesByAuthor(User author) {
        return repository.getAll()
                .stream()
                .filter(issue -> issue.getAutor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Issue> getOpenedIssues() {
        return repository.getAll()
                .stream()
                .filter(issue -> issue.getStatus().equals(Status.OPENED))
                .collect(Collectors.toList());
    }

    public List<Issue> getClosedIssues() {
        return repository.getAll()
                .stream()
                .filter(issue -> issue.getStatus().equals(Status.CLOSED))
                .collect(Collectors.toList());
    }
}
