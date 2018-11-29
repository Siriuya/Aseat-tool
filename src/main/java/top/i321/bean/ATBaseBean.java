package top.i321.bean;

import top.i321.enumBean.DriverType;

import java.util.List;
import java.util.Map;

public class ATBaseBean {
    private DriverType driverType;
    private String localDriverPath;
    private String localDriverVersion;
    private String email;
    private List<Map.Entry<String, String>> totalVarList;
    private List<Map.Entry<String, String>> cacheVarList;
    private List<Map.Entry<String, ATElementBean>> testATElementList;
    private List<ATDateBean> testATDataList;
    private List<Object> dataObjectList;
    private Map<String, ATCaseBean> ATCaseMap;

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public String getLocalDriverPath() {
        return localDriverPath;
    }

    public void setLocalDriverPath(String localDriverPath) {
        this.localDriverPath = localDriverPath;
    }

    public String getLocalDriverVersion() {
        return localDriverVersion;
    }

    public void setLocalDriverVersion(String localDriverVersion) {
        this.localDriverVersion = localDriverVersion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Map.Entry<String, String>> getTotalVarList() {
        return totalVarList;
    }

    public void setTotalVarList(List<Map.Entry<String, String>> totalVarList) {
        this.totalVarList = totalVarList;
    }

    public List<Map.Entry<String, String>> getCacheVarList() {
        return cacheVarList;
    }

    public void setCacheVarList(List<Map.Entry<String, String>> cacheVarList) {
        this.cacheVarList = cacheVarList;
    }

    public List<Map.Entry<String, ATElementBean>> getTestATElementList() {
        return testATElementList;
    }

    public void setTestATElementList(List<Map.Entry<String, ATElementBean>> testATElementList) {
        this.testATElementList = testATElementList;
    }

    public List<Object> getDataObjectList() {
        return dataObjectList;
    }

    public void setDataObjectList(List<Object> dataObjectList) {
        this.dataObjectList = dataObjectList;
    }

    public Map<String, ATCaseBean> getATCaseMap() {
        return ATCaseMap;
    }

    public void setATCaseMap(Map<String, ATCaseBean> ATCaseMap) {
        this.ATCaseMap = ATCaseMap;
    }

    public List<ATDateBean> getTestATDataList() {
        return testATDataList;
    }

    public void setTestATDataList(List<ATDateBean> testATDataList) {
        this.testATDataList = testATDataList;
    }
}
