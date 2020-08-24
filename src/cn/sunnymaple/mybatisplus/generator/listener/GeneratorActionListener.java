package cn.sunnymaple.mybatisplus.generator.listener;


import cn.sunnymaple.mybatisplus.generator.action.MyBatisPlusGenerator;
import cn.sunnymaple.mybatisplus.generator.cache.Cache;
import cn.sunnymaple.mybatisplus.generator.cache.Param;
import cn.sunnymaple.mybatisplus.generator.component.IComponent;
import cn.sunnymaple.mybatisplus.generator.plus.PluginAutoGenerator;
import cn.sunnymaple.mybatisplus.generator.plus.PluginVelocityTemplateEngine;
import cn.sunnymaple.mybatisplus.generator.util.ActionListenerUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static cn.sunnymaple.mybatisplus.generator.util.ActionListenerUtils.getErrorInfoFromException;

/**
 * 一键生成代码监听事件
 * @author wangzb
 * @date 2020-08-09 14:40
 */
public class GeneratorActionListener implements ActionListener {

    /**
     * 消息提示框
     */
    private JTextArea messageTextArea;

    public GeneratorActionListener(JTextArea messageTextArea) {
        this.messageTextArea = messageTextArea;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        try {
            MyBatisPlusGenerator.GeneratorComponent generatorComponent = MyBatisPlusGenerator.getInstance();
            //获取当前项目的Param
            Param param = Cache.getParam(generatorComponent.getCurrentProjectPath());
            Map<Class<? extends IComponent>, IComponent> components = generatorComponent.getComponents();
            components.forEach((k,v)->v.setParam(param,2));
            // 代码生成器
            PluginAutoGenerator autoGenerator = new PluginAutoGenerator();

            autoGenerator.setGlobalConfig(param.getGlobalConfig());
            autoGenerator.setDataSource(param.getDataSourceConfig());
            autoGenerator.setPluginPackageConfig(param.getPackageConfig());
            autoGenerator.setStrategy(param.getStrategyConfig());

            //
            final ClassLoader oldContextClassLoader = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(GeneratorActionListener.class.getClassLoader());

            autoGenerator.execute();

            Thread.currentThread().setContextClassLoader(oldContextClassLoader);

        } catch (IllegalArgumentException e){
            ActionListenerUtils.println(e.getMessage(),messageTextArea);
        } catch (Exception e) {
            ActionListenerUtils.println(getErrorInfoFromException(e),messageTextArea);
        }


    }
}
