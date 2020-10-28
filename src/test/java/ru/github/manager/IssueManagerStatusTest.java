package ru.github.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.github.domain.Issue;
import ru.github.domain.Status;
import ru.github.repository.IssuesRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssueManagerStatusTest {
    IssuesRepository repository = new IssuesRepository();
    Issue firstIssue = new Issue(1);
    Issue secondIssue = new Issue(2);
    Issue thirdIssue = new Issue(3);
    Issue fourth = new Issue(4);
    IssueManager manager = new IssueManager(repository);

    @BeforeEach
    public void setUp() {
        manager.addIssue(firstIssue);
        manager.addIssue(secondIssue);
        manager.addIssue(thirdIssue);
        manager.addIssue(fourth);
    }

    @Test
    public void testOpenedIssue() {
        List<Issue> issues = manager.getOpenedIssues();
        assertEquals(issues.size(), 4);
    }

    @Test
    public void testReopenIssues() {
        List<Issue> issues = manager.getOpenedIssues();
        assertEquals(issues.size(), 4);
        Issue issue = issues.get(0);
        assertEquals(issue.getStatus(), Status.OPENED);

        manager.updateIssueStatus(issue.getId(), Status.CLOSED);
        assertEquals(issue.getStatus(), Status.CLOSED);

        manager.updateIssueStatus(issue.getId(), Status.WAITING);
        assertEquals(issue.getStatus(), Status.WAITING);

        manager.updateIssueStatus(issue.getId(), Status.OPENED);
        assertEquals(issue.getStatus(), Status.OPENED);
    }

    @Test
    public void testClosedIssue() {
        List<Issue> issues = manager.getClosedIssues();
        assertEquals(issues.size(), 0);
        manager.updateIssueStatus(firstIssue.getId(), Status.CLOSED);
        manager.updateIssueStatus(thirdIssue.getId(), Status.CLOSED);

        issues = manager.getClosedIssues();
        assertEquals(issues.size(), 2);
    }
}
