package ru.github.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.github.domain.Issue;
import ru.github.domain.Status;
import ru.github.domain.Tag;
import ru.github.domain.User;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssuesRepositoryTest {
    IssuesRepository repository = new IssuesRepository();
    User asigneed = new User(1, "asigneed");
    User author = new User(1, "author");
    Tag junit = new Tag(1, "junit");
    Tag automation = new Tag(1, "automation");
    Date date = new Date();
    Issue issue;

    @BeforeEach
    void setUp() {
        issue = new Issue(1, "First issue", Set.of(junit, automation), author, asigneed, date);
    }

    @Test
    void testAddIssue() {
        assertEquals(repository.getAll().size(), 0);
        repository.add(issue);
        assertEquals(repository.getAll().size(), 1);
    }

    @Test
    void testChangeStatus() {
        repository.add(issue);
        assertEquals(issue.getStatus(), Status.OPENED);

        repository.updateStatus(0, Status.WAITING);
        assertEquals(issue.getStatus(), Status.OPENED);

        repository.updateStatus(1, Status.WAITING);
        assertEquals(issue.getStatus(), Status.WAITING);
    }
}