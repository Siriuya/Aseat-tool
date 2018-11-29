package top.i321.client;

import top.i321.bean.ATBaseBean;
import top.i321.bean.ATCaseBean;
import top.i321.method.ATParser;
import top.i321.service.ATCheckExcel;
import top.i321.service.ATLoaderDriver;
import top.i321.service.ATWebDriver;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.*;

public class ATClient {
    private ATBaseBean atBaseBean;
    private WebDriver driver;
    private Map<String, String> dataGet;
    private int NowRunID;

    public ATClient(String pathname) {
        ATCheckExcel.checkExcel(new File(pathname)); //检查文档
        this.atBaseBean = ATLoaderDriver.loader(pathname); //通过之后加载文档
        this.driver = ATWebDriver.getWebDriver(this.atBaseBean);
    }

    public ATBaseBean getAtBaseBean() {
        return atBaseBean;
    }

    public void setAtBaseBean(ATBaseBean atBaseBean) {
        this.atBaseBean = atBaseBean;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public int getNowRunID() {
        return NowRunID;
    }

    public void setNowRunID(int nowRunID) {
        NowRunID = nowRunID;
    }

    public Map<String, String> getDataGet() {
        return dataGet;
    }

    public void setDataGet(Map<String, String> dataGet) {
        this.dataGet = dataGet;
    }

    public void start() {
        this.setNowRunID(1);
        Map<String, ATCaseBean> atCaseMap = this.getAtBaseBean().getATCaseMap();
        ATParser atp = new ATParser(this);
        ATCaseBean as;
        while (this.getNowRunID() != -1) {
            as = atCaseMap.get(String.valueOf(this.getNowRunID()));
            boolean result = true;
            if(as.getRunStep().size() != 0){
                for (Object obj : as.getRunStep()) { //步骤运行
                    atp.parserMethod((String) obj);
                }
            }

            if(as.getDateGet() != null){
                for (Map.Entry<String, String> entry : as.getDateGet()) { //数据获取
                    this.getDataGet().put(entry.getKey(), (String) atp.parserMethod(entry.getValue()));
                }
            }

            if(as.getDateCheck() != null){
                for (Object obj : as.getDateCheck()) { //数据比对,使用getDataGet中的数据进行替换然后通过parserMethod进行检查
                    String str = ((String) obj).substring(((String) obj).indexOf("(") + 1, ((String) obj).indexOf(","));
                    result = result & ((boolean) atp.parserMethod(((String) obj).replaceAll(str, this.getDataGet().get(str))));
                }
            }

            if (result) {
                //验证成功
                this.setNowRunID(this.getNowRunID()+1);
            } else {
                //验证失败
                this.setNowRunID(-1);
            }
        }
    }

    public static void main(String[] args) {
        ATClient client = new ATClient("");
        ATBaseBean atb = new ATBaseBean();
        ATCaseBean atc= new ATCaseBean();
        List<Object> ls= new ArrayList<>();
        ls.add("$.assertEquals(text,\"123\")");
       // atc.setRunStep(ls);
        atc.setDateCheck(ls);
        //atc.setDateGet(null);
        Map<String,ATCaseBean> map = new HashMap<>();
        map.put("1",atc);
        atb.setATCaseMap(map);
        client.setAtBaseBean(atb);
        System.out.println(client.getAtBaseBean().getATCaseMap().get("1").getRunStep().get(0));
        client.start();
    //  System.out.println("1");

    }
}
