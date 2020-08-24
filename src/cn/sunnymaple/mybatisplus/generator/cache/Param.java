package cn.sunnymaple.mybatisplus.generator.cache;

import cn.sunnymaple.mybatisplus.generator.component.impl.PackingComponentImpl;
import cn.sunnymaple.mybatisplus.generator.plus.PluginPackageConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

import java.util.List;
import java.util.Map;

/**
 * 用户填写的参数对象参数
 * @author wangzb
 * @date 2019-01-22 14:17
 * @company 矽甲（上海）信息科技有限公司
 */
public class Param {


    /**
     * 项目路径，自动获取
     */
    private String projectPath;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 数据库配置
     */
    private DataSourceConfig dataSourceConfig;
    /**
     * 策略配置
     */
    private StrategyConfig strategyConfig;
    /**
     * 全局配置
     */
    private GlobalConfig globalConfig;
    /**
     * 包配置
     */
    private PluginPackageConfig packageConfig;

    private List<String> items;
    /**
     * 项目包选中
     * key：模块名称 controller、service等
     * value:选中的item机，项目
     */
    private Map<PackingComponentImpl.Model,String> packageModelSelecteds;
    /**
     * src选中
     * key 模块名称 controller、service等
     * value:选中的item，src/main/java或者src/main/resources
     */
    private Map<PackingComponentImpl.Model,String> packageSrcSelecteds;
    /**
     * 包
     * key:模块名称 controller、service等
     * value:输入的包名
     */
    private Map<PackingComponentImpl.Model,String> packages;

    public Param() {
    }

    public Param(String projectPath, String projectName) {
        this.projectPath = projectPath;
        this.projectName = projectName;
    }

    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public StrategyConfig getStrategyConfig() {
        return strategyConfig;
    }

    public void setStrategyConfig(StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public Map<PackingComponentImpl.Model, String> getPackageModelSelecteds() {
        return packageModelSelecteds;
    }

    public void setPackageModelSelecteds(Map<PackingComponentImpl.Model, String> packageModelSelecteds) {
        this.packageModelSelecteds = packageModelSelecteds;
    }

    public Map<PackingComponentImpl.Model, String> getPackageSrcSelecteds() {
        return packageSrcSelecteds;
    }

    public void setPackageSrcSelecteds(Map<PackingComponentImpl.Model, String> packageSrcSelecteds) {
        this.packageSrcSelecteds = packageSrcSelecteds;
    }

    public Map<PackingComponentImpl.Model, String> getPackages() {
        return packages;
    }

    public void setPackages(Map<PackingComponentImpl.Model, String> packages) {
        this.packages = packages;
    }

    public PluginPackageConfig getPackageConfig() {
        return packageConfig;
    }

    public void setPackageConfig(PluginPackageConfig packageConfig) {
        this.packageConfig = packageConfig;
    }

    @Override
    public String toString() {
        return "Param{" +
                "projectPath='" + projectPath + '\'' +
                ", projectName='" + projectName + '\'' +
                ", dataSourceConfig=" + dataSourceConfig +
                ", strategyConfig=" + strategyConfig +
                ", globalConfig=" + globalConfig +
                ", packageConfig=" + packageConfig +
                ", items=" + items +
                ", packageModelSelecteds=" + packageModelSelecteds +
                ", packageSrcSelecteds=" + packageSrcSelecteds +
                ", packages=" + packages +
                '}';
    }
}
