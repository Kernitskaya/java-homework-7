package ru.github.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Issue {
    private int id;
    private String title;
    private String description = "";
    private Set<Tag> tags = new HashSet<>();
    private Status status = Status.OPENED;
    private User autor;
    private User asigneed;
    private Date createdDate;
    private int commentsCount = 0;

    public Issue(int id) {
        this.id = id;
    }

    public Issue(int id, String title, Set<Tag> tags, User autor, User asigneed, Date createdDate) {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.autor = autor;
        this.asigneed = asigneed;
        this.createdDate = createdDate;
    }

    public Issue(int id, int commentsCount, Date createdDate) {
        this.id = id;
        this.commentsCount = commentsCount;
        this.createdDate = createdDate;
    }

    public Issue(int id, User asigneed, Set<Tag> tags, User autor) {
        this.id = id;
        this.asigneed = asigneed;
        this.tags = tags;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public User getAutor() {
        return autor;
    }

    public User getAsigneed() {
        return asigneed;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

}
