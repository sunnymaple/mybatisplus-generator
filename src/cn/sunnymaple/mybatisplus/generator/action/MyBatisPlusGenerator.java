package cn.sunnymaple.mybatisplus.generator.action;

import cn.hutool.core.util.StrUtil;
import cn.sunnymaple.mybatisplus.generator.cache.Cache;
import cn.sunnymaple.mybatisplus.generator.cache.Param;
import cn.sunnymaple.mybatisplus.generator.component.IComponent;
import cn.sunnymaple.mybatisplus.generator.component.impl.*;
import cn.sunnymaple.mybatisplus.generator.util.ActionListenerUtils;
import cn.sunnymaple.mybatisplus.generator.listener.MyBatisPlusGeneratorWindowAdapter;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzb
 * @date 2020/8/8 14:37
 */
public class MyBatisPlusGenerator extends AnAction{

    private static final String FRAME_NAME = "MybatisPlus代码生成器";

    /**
     * 定义面板
     */
    private JPanel panel;

    private volatile JFrame frame = new JFrame(FRAME_NAME);

    private static GeneratorComponent generatorComponent;

    public static GeneratorComponent getInstance(){
        return generatorComponent;
    }


    public MyBatisPlusGenerator() {
        createUIComponents();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //窗口大小
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.pack();
        //居中
        frame.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/mybatis_32_32.png").getPath());
        frame.setIconImage(icon.getImage());
        //设置窗口事件
        frame.addWindowListener(new MyBatisPlusGeneratorWindowAdapter());
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        //获取项目路径
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        String projectName = project.getName();
        String projectPath = project.getPresentableUrl();
        generatorComponent.setCurrentProjectPath(projectPath);
        Param param = Cache.getParam(projectPath);
        if (param == null){
            param = new Param(projectPath,projectName);
            Cache.setParam(projectPath, param);
        }else {
            if (StrUtil.isBlank(param.getProjectPath())){
                param.setProjectPath(projectPath);
                Cache.setParam(projectPath, param);
            }
            final Param currentParam = param;
            //初始化操作
            try {
                Map<Class<? extends IComponent>, IComponent> components = generatorComponent.getComponents();
                components.forEach((k,v)->v.init(currentParam));
            }catch (Exception e){
                ActionListenerUtils.println("初始化失败：" ,e);
            }

        }
        frame.setVisible(true);
    }



    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void createUIComponents() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(18, 5, new Insets(0, 0, 0, 0), -1, -1));
        //数据库源相关组件
        Integer row = 0;
        DataSourceComponentImpl dataSourceComponent = new DataSourceComponentImpl();
        row = dataSourceComponent.addComponents(panel,row);

        //全局
        GlobalComponentImpl globalComponent = new GlobalComponentImpl();
        row = globalComponent.addComponents(panel,row);
        //包
        PackingComponentImpl packingComponent = new PackingComponentImpl();
        row = packingComponent.addComponents(panel,row);
        //策略
        StrategyComponentImpl strategyComponent = new StrategyComponentImpl();
        row = strategyComponent.addComponents(panel,row);

        //执行区
        ExecuteComponentImpl executeComponent = new ExecuteComponentImpl();
        row = executeComponent.addComponents(panel,row);

        generatorComponent = new GeneratorComponent( dataSourceComponent,
                globalComponent,  packingComponent,
                strategyComponent,executeComponent);

    }


    public static class GeneratorComponent{
        /**
         * 当前的项目路径
         */
        private String currentProjectPath;
        /**
         * 组件
         */
        private Map<Class<? extends IComponent>, IComponent> components = new HashMap<>();

        public GeneratorComponent(IComponent... componentArr) {
            for (IComponent component : componentArr){
                components.put(component.getClass(),component);
            }
        }

        public Map<Class<? extends IComponent>, IComponent> getComponents() {
            return components;
        }

        /**
         * 获取组件
         * @param tClass
         * @param <T>
         * @return
         */
        public <T extends IComponent> IComponent getComponent(Class<T> tClass){
            return components.get(tClass);
        }

        public String getCurrentProjectPath() {
            return currentProjectPath;
        }

        public void setCurrentProjectPath(String currentProjectPath) {
            this.currentProjectPath = currentProjectPath;
        }

        public void setComponents(Map<Class<? extends IComponent>, IComponent> components) {
            this.components = components;
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
