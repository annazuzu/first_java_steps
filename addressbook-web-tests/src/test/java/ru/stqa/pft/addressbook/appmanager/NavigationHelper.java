package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void groupPage1() {
        wd.findElement(By.cssSelector(String.format("a[href='group.php']")));
//        wd.findElement(By.cssSelector(String.format("a[href='group.php']")));
        click(By.linkText("groups"));
    }

    public void homePage() {
        if(isElementPresent(By.id("maintable"))) {
            return;
        }
//        click(By.linkText("home page"));
        click(By.linkText("home"));
    }
}

