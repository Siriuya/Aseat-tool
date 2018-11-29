package top.i321.method;
import top.i321.enumBean.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ATRunMethod {

    public static void get(WebDriver driver, String url) {
        driver.get(url);
    }

    public static void sendKeys(WebElement element, String sendkeys) {
        element.sendKeys(sendkeys);
    }

    public static void clear(WebElement element) {
        element.clear();
    }

    public static void click(WebElement element) {
        element.click();
    }

    public static void submit(WebElement element) {
        element.submit();
    }

    public static void wait(String number, TimeUnit timeUnit, WebDriver driver) {
        long times = Integer.valueOf(number);
        switch (timeUnit) {
            case MIN:
                times = times * 6000;
            case MSEC:
                times = times;
            case SECOND:
                times = times * 100;
        }
        try {
            driver.wait(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void wait(String number, TimeUnit timeUnit, WebElement element) {
        long times = Integer.valueOf(number);
        switch (timeUnit) {
            case MIN:
                times = times * 6000;
            case MSEC:
                times = times;
            case SECOND:
                times = times * 100;
        }
        try {
            element.wait(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}