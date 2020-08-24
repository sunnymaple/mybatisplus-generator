package cn.sunnymaple.mybatisplus.generator.plus;

import com.baomidou.mybatisplus.generator.config.PackageConfig;

/**
 * @author wangzb
 * @date 2020/8/12 00:00
 */
public class PluginPackageConfig extends PackageConfig{
    /**
     * xml的输出目录
     */
    private String xmlOutputDir;
    /**
     * xmlSrc目录
     */
    private String xmlSrcDir;
    /**
     * 总路径
     */
    private String parentOutputDir;

    /**
     * 实体类是否覆盖
     */
    private boolean entityFileOverride = false;
    /**
     * xml是否覆盖
     */
    private boolean xmlFileOverride = false;

    public String getXmlOutputDir() {
        return xmlOutputDir;
    }

    public void setXmlOutputDir(String xmlOutputDir) {
        this.xmlOutputDir = xmlOutputDir;
    }

    public boolean isEntityFileOverride() {
        return entityFileOverride;
    }

    public void setEntityFileOverride(boolean entityFileOverride) {
        this.entityFileOverride = entityFileOverride;
    }

    public boolean isXmlFileOverride() {
        return xmlFileOverride;
    }

    public void setXmlFileOverride(boolean xmlFileOverride) {
        this.xmlFileOverride = xmlFileOverride;
    }

    public String getXmlSrcDir() {
        return xmlSrcDir;
    }

    public void setXmlSrcDir(String xmlSrcDir) {
        this.xmlSrcDir = xmlSrcDir;
    }

    public String getParentOutputDir() {
        return parentOutputDir;
    }

    public void setParentOutputDir(String parentOutputDir) {
        this.parentOutputDir = parentOutputDir;
    }
}
