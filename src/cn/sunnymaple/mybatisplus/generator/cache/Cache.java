package cn.sunnymaple.mybatisplus.generator.cache;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.intellij.ide.util.PropertiesComponent;

/**
 * @author wangzb
 * @date 2020/8/9 11:48
 */
public class Cache {

    private static PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();

    private Cache(){

    }

    /**
     * 获取当前项目的{@link Param}对象
     * @param currentProjectPath 当前项目
     * @return
     */
    public static Param getParam(String currentProjectPath){
        String value = propertiesComponent.getValue(currentProjectPath);
        if (StrUtil.isBlank(value)){
            return null;
        }
        return JSONUtil.toBean(value,Param.class);
    }

    /**
     * 设置参数{@link Param}
     * @param currentProjectPath 当前项目
     * @param param {@link Param} 参数
     */
    public static void setParam(String currentProjectPath,Param param){
        propertiesComponent.setValue(currentProjectPath,JSONUtil.toJsonStr(param));

    }

    /**
     * 清除
     * @param currentProjectPath
     */
    public static void unSetParam(String currentProjectPath){
        propertiesComponent.unsetValue(currentProjectPath);
    }


}
