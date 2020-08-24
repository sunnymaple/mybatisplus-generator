package cn.sunnymaple.mybatisplus.generator.listener;

import cn.sunnymaple.mybatisplus.generator.action.MyBatisPlusGenerator;
import cn.sunnymaple.mybatisplus.generator.cache.Cache;
import cn.sunnymaple.mybatisplus.generator.cache.Param;
import cn.sunnymaple.mybatisplus.generator.util.ActionListenerUtils;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static cn.sunnymaple.mybatisplus.generator.util.ActionListenerUtils.getErrorInfoFromException;

/**
 * {@link MyBatisPlusGenerator}窗口事件
 * @author wangzb
 * @date 2020/8/9 10:57
 */
public class MyBatisPlusGeneratorWindowAdapter extends WindowAdapter {

    /**
     * 窗口关闭之前
     * @param windowEvent
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        super.windowClosing(windowEvent);
        try {
            MyBatisPlusGenerator.GeneratorComponent generatorComponent = MyBatisPlusGenerator.getInstance();
            String currentProjectPath = generatorComponent.getCurrentProjectPath();
            Param param = Cache.getParam(currentProjectPath);
            generatorComponent.getComponents().forEach((k,v)->v.setParam(param,1));
        }catch (Exception e){
            ActionListenerUtils.println(getErrorInfoFromException(e));
        }

    }
}
