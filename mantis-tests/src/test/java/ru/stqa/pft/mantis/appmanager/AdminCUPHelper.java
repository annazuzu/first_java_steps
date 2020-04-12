package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminCUPHelper extends HelperBase {

    public AdminCUPHelper(ApplicationManager app) {
        super(app);
    }

//    driver.get("http://localhost/mantisbt-2.24.0/mantisbt-2.24.0/login_page.php?return=%2Fmantisbt-2.24.0%2Fmantisbt-2.24.0%2Fmanage_user_page.php");
//    driver.findElement(By.id("username")).click();
//    driver.findElement(By.id("username")).clear();
//    driver.findElement(By.id("username")).sendKeys("administrator");
//    driver.findElement(By.xpath("//input[@value='Войти']")).click();
//    driver.findElement(By.id("password")).click();
//    driver.findElement(By.id("password")).clear();
//    driver.findElement(By.id("password")).sendKeys("root");
//    driver.findElement(By.xpath("//input[@value='Войти']")).click();
//    driver.findElement(By.linkText("usertestone")).click();
    public void goToManageUserPage () {
        wd.get("http://localhost/mantisbt-2.24.0/mantisbt-2.24.0/manage_user_page.php");
        wd.findElement(By.id("password")).sendKeys("root");
        wd.findElement(By.xpath("//input[@value='Войти']")).click();
    }

    public void clickOnUserName (int id) {
        wd.findElement(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id))).click();
    }

    public void clickButtonResetPassword() {
        wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
    }

}
