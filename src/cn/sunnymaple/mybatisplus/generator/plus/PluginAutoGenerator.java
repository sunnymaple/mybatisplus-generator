package cn.sunnymaple.mybatisplus.generator.plus;

import cn.sunnymaple.mybatisplus.generator.util.ActionListenerUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;

/**
 * 插件生成文件
 * @author wangzb
 * @date 2020/8/12 00:08
 */
public class PluginAutoGenerator extends AutoGenerator {

    private PluginPackageConfig pluginPackageConfig;

    /**
     * 生成代码
     */
    @Override
    public void execute() {
        PluginVelocityTemplateEngine templateEngine = new PluginVelocityTemplateEngine(pluginPackageConfig);
        ActionListenerUtils.println("==========================准备生成文件...==========================");
        // 初始化配置
        if (null == config) {
            config = new ConfigBuilder(getPluginPackageConfig(), getDataSource(), getStrategy(), getTemplate(), getGlobalConfig());
            if (null != injectionConfig) {
                injectionConfig.setConfig(config);
            }
        }
        // 模板引擎初始化执行文件输出
        templateEngine.init(this.pretreatmentConfigBuilder(config)).mkdirs().batchOutput().open();
        ActionListenerUtils.println("==========================文件生成完成！！！==========================");
    }

    public PluginPackageConfig getPluginPackageConfig() {
        return pluginPackageConfig;
    }

    public void setPluginPackageConfig(PluginPackageConfig pluginPackageConfig) {
        this.pluginPackageConfig = pluginPackageConfig;
        setPackageInfo(pluginPackageConfig);
    }
}
