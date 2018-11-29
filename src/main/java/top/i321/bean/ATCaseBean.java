package top.i321.bean;

import java.util.List;
import java.util.Map;

public class ATCaseBean {
    private boolean isRun; //是否运行
    private String testCaseId; //案例ID
    private String testCaseName; //案例名称
    private List<Object> runStep; //运行步骤
    private List<Map.Entry<String, String>> dateGet; //数据获取
    private List<Object> dateCheck; //数据检查
    private List<Map.Entry<String, String>> dateCache; //数据缓存
    private List<Map.Entry<String, String>> varTemporary; //临时变量
    private List<Object> checkTrue; //验证为真
    private List<Object> checkFalse; //验证为假
    private String runStartTime; //运行开始时间
    private String runStopTime; //运行结束时间
    private String result; //运行结果

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public List<Object> getRunStep() {
        return runStep;
    }

    public void setRunStep(List<Object> runStep) {
        this.runStep = runStep;
    }

    public List<Map.Entry<String, String>> getDateGet() {
        return dateGet;
    }

    public void setDateGet(List<Map.Entry<String, String>> dateGet) {
        this.dateGet = dateGet;
    }

    public List<Map.Entry<String, String>> getDateCache() {
        return dateCache;
    }

    public void setDateCache(List<Map.Entry<String, String>> dateCache) {
        this.dateCache = dateCache;
    }

    public List<Map.Entry<String, String>> getVarTemporary() {
        return varTemporary;
    }

    public void setVarTemporary(List<Map.Entry<String, String>> varTemporary) {
        this.varTemporary = varTemporary;
    }

    public List<Object> getDateCheck() {
        return dateCheck;
    }

    public void setDateCheck(List<Object> dateCheck) {
        this.dateCheck = dateCheck;
    }

    public List<Object> getCheckTrue() {
        return checkTrue;
    }

    public void setCheckTrue(List<Object> checkTrue) {
        this.checkTrue = checkTrue;
    }

    public List<Object> getCheckFalse() {
        return checkFalse;
    }

    public void setCheckFalse(List<Object> checkFalse) {
        this.checkFalse = checkFalse;
    }

    public String getRunStartTime() {
        return runStartTime;
    }

    public void setRunStartTime(String runStartTime) {
        this.runStartTime = runStartTime;
    }

    public String getRunStopTime() {
        return runStopTime;
    }

    public void setRunStopTime(String runStopTime) {
        this.runStopTime = runStopTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
