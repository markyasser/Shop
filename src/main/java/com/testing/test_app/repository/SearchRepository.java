package com.testing.test_app.repository;

import com.testing.test_app.model.Post;
import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}