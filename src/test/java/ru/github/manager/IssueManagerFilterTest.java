package ru.github.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.github.domain.Issue;
import ru.github.domain.Tag;
import ru.github.domain.User;
import ru.github.repository.IssuesRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IssueManagerFilterTest {
    IssuesRepository repository = new IssuesRepository();
    Issue firstIssue = new Issue(1,
            new User(1, "Bob"),
            Set.of(new Tag(1, "automation")),
            new User(1, "Kelly"));
    Issue secondIssue = new Issue(2,
            new User(2, "Alice"),
            Set.of(new Tag(1, "automation"), new Tag(2, "junit")),
            new User(1, "Kenny"));
    Issue thirdIssue = new Issue(3,
            new User(3, "Alice"),
            Set.of(),
            new User(1, "Ken"));
    Issue fourth = new Issue(4,
            new User(4, "Mike"),
            Set.of(),
            new User(1, "Kenny"));
    IssueManager manager = new IssueManager(repository);

    @BeforeEach
    public void setUp() {
        repository.add(firstIssue);
        repository.add(secondIssue);
        repository.add(thirdIssue);
        repository.add(fourth);
    }

    @Test
    public void testIssuesByAsigneed() {
        User user = new User(4, "Mike");
        List<Issue> issues = manager.getIssuesByAsigneed(user);
        assertEquals(issues.size(), 1);
        assertEquals(issues.get(0).getAsigneed().getName(), "Mike");
        assertEquals(issues.get(0).getAsigneed().getId(), 4);
    }

    @Test
    public void testNotFoundIssuesAsigneed() {
        User user = new User(4, "Jery");
        List<Issue> issues = manager.getIssuesByAsigneed(user);
        assertEquals(issues.size(), 0);
    }

    @Test
    public void testIssuesByTag() {
        Tag tag = new Tag(1, "automation");
        List<Issue> issues = manager.getIssuesByTag(tag);
        assertEquals(issues.size(), 2);
        assertTrue(issues.get(0).getTags().contains(tag));
    }

    @Test
    public void testNotFoundIssue() {
        Tag tag = new Tag(1, "python");
        List<Issue> issues = manager.getIssuesByTag(tag);
        assertEquals(issues.size(), 0);
    }

    @Test
    public void testIssuesByAuthor() {
        User user = new User(1, "Kelly");
        List<Issue> issues = manager.getIssuesByAuthor(user);
        assertEquals(issues.size(), 1);
        assertEquals(issues.get(0).getAutor().getName(), "Kelly");
        assertEquals(issues.get(0).getAutor().getId(), 1);
    }

    @Test
    public void testNotFoundIssuesByAuthor() {
        User user = new User(4, "Alice");
        List<Issue> issues = manager.getIssuesByAsigneed(user);
        assertEquals(issues.size(), 0);
    }
}
