package com.bluefish.domain;

/**
 * Created by elaine on 2016/4/3.
 */
public class Article {
    private int m_id;
    private User m_user;
    private String m_title;
    private String m_content;

    public int getId() {
        return m_id;
    }

    public void setId(int id) {
        this.m_id = id;
    }

    public User getUser() {
        return m_user;
    }

    public void setUser(User user) {
        this.m_user = user;
    }

    public String getTitle() {
        return m_title;
    }

    public void setTitle(String title) {
        this.m_title = title;
    }

    public String getContent() {
        return m_content;
    }

    public void setContent(String content) {
        this.m_content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + m_id +
                ", user=" + m_user +
                ", title='" + m_title + '\'' +
                ", content='" + m_content + '\'' +
                '}';
    }
}
