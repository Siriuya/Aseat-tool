package top.i321.method;

import top.i321.bean.ATBaseBean;
import top.i321.bean.ATElementBean;
import top.i321.client.ATClient;
import top.i321.enumBean.TimeUnit;
import top.i321.service.ATFindElement;
import top.i321.service.ATFindVar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATParser {
    private ATClient atClient;

    public ATParser(ATClient atClient) {
        this.atClient = atClient;
    }

    /**
     * 解析method
     *
     * @param str：sendKeys(@{passWord},${USER.PW})
     *           getAttribute(@{passWord},"value")
     *           assertNotEquals(text,"123")
     * @return boolean
     */
    public Object parserMethod(String str) {
        if (str.startsWith("assert")) {
            return parserAssertMethod(str); //返回boolean
        } else if (str.startsWith("get")) {
            return parserGetMethod(str); //返回String
        } else if (str.startsWith("is")) {
            return parserGetMethod(str); //返回String
        } else {
            return parserRunMethod(str); //返回void
        }
    }

    /**
     * 解析var
     *
     * @param str: @{passWord}
     *             ${USER.PW}
     *             "zhangsan"
     * @return Object
     */
    public Object parserParameter(String str) {
        if (str.charAt(0) == '$') {
            return parserVar(this.atClient.getAtBaseBean(), str.substring(2, str.length() - 1)); //返回String
        } else if (str.charAt(0) == '"') {
            return str.substring(1, str.length() - 1); //返回String
        } else if (str.charAt(0) == '@') {
            return parserElement(str.substring(2, str.length() - 1)); //返回WebElement
        } else {
            return str;
        }
    }

    /**
     * 运行run函数，进行操作
     *
     * @param str:sendKeys(@{passWord},${USER.PW})
     * @return boolean
     */
    private boolean parserRunMethod(String str) {
        try {
            String type = str.substring(0, str.indexOf("("));
            String[] args = str.substring(str.indexOf("(") + 1, str.indexOf(")")).split(",");
            switch (type) {
                case "sendKeys":
                    ATRunMethod.sendKeys((WebElement) this.parserParameter(args[0]), (String) this.parserParameter(args[1]));
                    break;
                case "click":
                    ATRunMethod.click((WebElement) this.parserParameter(args[0]));
                    break;
                case "clear":
                    ATRunMethod.clear((WebElement) this.parserParameter(args[0]));
                    break;
                case "get":
                    ATRunMethod.get(this.atClient.getDriver(), (String) this.parserParameter(args[0]));
                    break;
                case "submit":
                    ATRunMethod.submit((WebElement) this.parserParameter(args[0]));
                    break;
                case "wait":
                    if (args.length == 2) {
                        ATRunMethod.wait((String) this.parserParameter(args[0]), (TimeUnit) this.parserParameter(args[1]), this.atClient.getDriver());
                    } else {
                        ATRunMethod.wait((String) this.parserParameter(args[0]), (TimeUnit) this.parserParameter(args[1]), (WebElement) this.parserParameter(args[2]));
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 运行get函数，获取对象值用于后期校验
     *
     * @param str:getAttribute(@{passWord},"value")
     * @return
     */
    private Object parserGetMethod(String str) {
        String result = null;
        try {
            String type = str.substring(0, str.indexOf("("));
            String[] args = str.substring(str.indexOf("(") + 1, str.indexOf(")")).split(",");
            switch (type) {
                case "getText":
                    result = ATGetMethod.getText(this.parserElement(args[0]));
                    break;
                case "getTagName":
                    result = ATGetMethod.getTagName(this.parserElement(args[0]));
                    break;
                case "getAttribute":
                    result = ATGetMethod.getAttribute(this.parserElement(args[0]), (String) this.parserParameter(args[1]));
                    break;
                case "getSize":
                    result = ATGetMethod.getSize(this.parserElement(args[0]));
                    break;
                case "getCssValue":
                    result = ATGetMethod.getCssValue(this.parserElement(args[0]), (String) this.parserParameter(args[1]));
                    break;
                case "isSelected":
                    return ATGetMethod.isSelected(this.parserElement(args[0]));
                case "isEnabled":
                    return ATGetMethod.isEnabled(this.parserElement(args[0]));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERR";
        }
        return result;
    }

    /**
     * @param str:assertEquals(text,"123")
     * @return
     */
    private boolean parserAssertMethod(String str) {
        boolean result = true;
        try {
            String type = str.substring(0, str.indexOf("("));
            String[] args = str.substring(str.indexOf("(") + 1, str.indexOf(")")).split(",");
            switch (type) {
                case "assertEquals":
                    result = ATAssertMethod.assertEquals((String) this.parserParameter(args[0]), (String) this.parserParameter(args[1]));
                    break;
                case "assertNotEquals":
                    result = ATAssertMethod.assertNotEquals((String) this.parserParameter(args[0]), (String) this.parserParameter(args[1]));
                    break;
                case "assertNull":
                    result = ATAssertMethod.assertNull((String) this.parserParameter(args[0]));
                    break;
                case "assertNotNull":
                    result = ATAssertMethod.assertNotNull((String) this.parserParameter(args[0]));
                    break;
                case "assertTrue":
                    result = ATAssertMethod.assertTrue(this.parserParameter(args[0]) == "true" ? true : false);
                    break;
                case "assertFalse":
                    result = ATAssertMethod.assertFalse(this.parserParameter(args[0]) == "true" ? true : false);
                    break;
                case "assertInclude":
                    result = ATAssertMethod.assertInclude((String) this.parserParameter(args[0]), (String) this.parserParameter(args[1]));
                    break;
                case "assertExclude":
                    result = ATAssertMethod.assertExclude((String) this.parserParameter(args[0]), (String) this.parserParameter(args[1]));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return result;
    }

    /**
     * 获取变量值并返回String
     *
     * @param atBaseBean
     * @param str
     * @return String
     */
    private String parserVar(ATBaseBean atBaseBean, String str) {
        Map<String, List<Map.Entry<String, String>>> varMap = null;
        varMap.put("TotalVarList", atBaseBean.getTotalVarList());
        varMap.put("CacheVarList", atBaseBean.getCacheVarList());
        varMap.put("CaseVarList", atBaseBean.getATCaseMap().get(atClient.getNowRunID()).getVarTemporary());
        return ATFindVar.get(varMap, str);
    }

    /**
     * 根据Element的名字查找WebElement
     *
     * @param str: text
     * @return WebElement
     */
    private WebElement parserElement(String str) {
        WebDriver driver = this.atClient.getDriver();
        List<Map.Entry<String, ATElementBean>> atElementBeanList = this.atClient.getAtBaseBean().getTestATElementList();
        ATElementBean at = null;
        for (Map.Entry entry : atElementBeanList) {
            if (entry.getKey() == str) {
                at = (ATElementBean) entry.getValue();
            }
        }
        return ATFindElement.get(driver, at);
    }

    public static void main(String[] args) {
        Object obj = "$.assertEquals(text,sss,\"123\")";
        ATParser atp = new ATParser(null);
        Map<String,String> map = new HashMap<>();
        map.put("text","123");
        String str = ((String) obj).substring(((String) obj).indexOf("(")+1, ((String) obj).indexOf(","));
        System.out.println(((String) obj).replaceAll(str, map.get(str)));
    }
}
