package ru.github.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.github.domain.Issue;
import ru.github.repository.IssuesRepository;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssueManagerSortedTest {
    IssuesRepository repository = new IssuesRepository();
    Issue firstIssue = new Issue(1, 10,
            new GregorianCalendar(2014, Calendar.NOVEMBER, 11).getTime());
    Issue secondIssue = new Issue(2, 5,
            new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());
    Issue thirdIssue = new Issue(3, 3,
            new GregorianCalendar(2021, Calendar.MARCH, 11).getTime());
    Issue fourth = new Issue(4, 20,
            new GregorianCalendar(2019, Calendar.MARCH, 11).getTime());
    IssueManager manager = new IssueManager(repository);

    @BeforeEach
    public void setUp() {
        repository.add(firstIssue);
        repository.add(secondIssue);
        repository.add(thirdIssue);
        repository.add(fourth);
    }

    @Test
    public void testSortedReviewsCount() {
        List<Issue> issues = manager.sortIssueByReviewsCount();
        assertEquals(issues.size(), 4);
        assertEquals(issues.get(0).getId(), 4);
        assertEquals(issues.get(1).getId(), 1);
        assertEquals(issues.get(2).getId(), 2);
        assertEquals(issues.get(3).getId(), 3);
    }

    @Test
    public void testSortedByDate() {
        List<Issue> issues = manager.sortIssueByDate();
        assertEquals(issues.size(), 4);
        assertEquals(issues.get(0).getId(), 3);
        assertEquals(issues.get(1).getId(), 2);
        assertEquals(issues.get(2).getId(), 4);
        assertEquals(issues.get(3).getId(), 1);
    }

}
