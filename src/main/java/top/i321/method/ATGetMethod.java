package top.i321.method;

import org.openqa.selenium.WebElement;

public class ATGetMethod {

    public static String getText(WebElement element) {
        return element.getText();
    }

    public static String getTagName(WebElement element) {
        return element.getTagName();
    }

    public static String getAttribute(WebElement element, String key) {
        return element.getAttribute(key);
    }

    public static String getSize(WebElement element) {
        return element.getSize().toString();
    }

    public static String getCssValue(WebElement element, String csskey) {
        return element.getCssValue(csskey);
    }

    public static boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    public static boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    public static boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }
}
