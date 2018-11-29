package top.i321.service;

import top.i321.bean.ATElementBean;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ATFindElement {

    public static WebElement get(WebDriver driver, ATElementBean elementBean) {
        WebElement element = null;
        List<WebElement> elements;

        elements = driver.findElements(By.name(elementBean.getName()));
        return element;
    }

}
