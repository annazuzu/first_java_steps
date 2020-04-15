package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.swing.*;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{

    @BeforeTest
    public void skip() throws RemoteException, ServiceException, MalformedURLException {
//       Set<Issue> issues = app.soap().getIssue();
//       skipIfNotFixed(issue.getId());
    }

    @Test
    public void getProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
//        Project oneProject = projects.iterator().next();
//        skipIfNotFixed(oneProject.getId());
        for (Project project : projects) {
            skipIfNotFixed(project.getId());
            System.out.println(project.getName());
        }
    }


    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                .withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }

}
