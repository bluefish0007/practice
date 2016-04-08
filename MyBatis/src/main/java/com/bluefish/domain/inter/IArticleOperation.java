package com.bluefish.domain.inter;

import com.bluefish.domain.Article;
import com.bluefish.domain.User;

import java.util.List;

/**
 * Created by elaine on 2016/4/3.
 */
public interface IArticleOperation {

    List<Article> getAllArticle();
    List<Article> getArticleByUserId(int userId);

}
