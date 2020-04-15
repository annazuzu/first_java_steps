package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

    @Test

    public void testCommits() throws IOException {
        Github github = new RtGithub("d126a368016933a564423b0e66f1b7fe7387bbe2"); // строка - токен авторизации
        //читаем историю проекта:
        RepoCommits commits = github.repos().get(new Coordinates.Simple("annazuzu", "first_java_steps")).commits();
        // устраиваем итерацию по коммитам:
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) { // в качестве параметра iterate() нужно передать набор пар, которые устраивают
            // условия отбора коммитов. Мы хотим получить полный список, поэтому
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }

}
