package ru.github.repository;

import ru.github.domain.Issue;
import ru.github.domain.Status;

import java.util.ArrayList;
import java.util.List;

public class IssuesRepository {
    List<Issue> issues = new ArrayList<>();

    public List<Issue> getAll() {
        return issues;
    }

    public void add(Issue issue) {
        issues.add(issue);
    }

    public void updateStatus(int issueId, Status newStatus) {
        for (Issue issue : issues) {
            if (issue.getId() == issueId) {
                issue.setStatus(newStatus);
                return;
            }
        }
    }
}
