package cn.sunnymaple.mybatisplus.generator.component.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.sunnymaple.mybatisplus.generator.plus.PluginPackageConfig;
import cn.sunnymaple.mybatisplus.generator.util.ActionListenerUtils;
import cn.sunnymaple.mybatisplus.generator.cache.Cache;
import cn.sunnymaple.mybatisplus.generator.cache.Param;
import cn.sunnymaple.mybatisplus.generator.component.IComponent;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;

import javax.swing.*;
import java.io.File;
import java.util.*;

import cn.sunnymaple.mybatisplus.generator.util.Optional;

import static cn.sunnymaple.mybatisplus.generator.component.impl.PackingComponentImpl.Model.*;

/**
 * 包相关配置组件
 * @author wangzb
 * @date 2020/8/8 22:31
 */
public class PackingComponentImpl implements IComponent{

    private Component parentComponent;

//    private Component controllerComponent;
    private Component serviceComponent;
    private Component serviceImplComponent;
    private Component entityComponent;
    private Component mapperComponent;
    private Component xmlComponent;

    private JRadioButton entityRadioButton;
    private JRadioButton xmlRadioButton;


    public enum Model{
        PARENT("Parent"),
        CONTROLLER("Controller"),
        SERVICE("Service"),
        SERVICE_IMPL("ServiceImpl"),
        ENTITY("Entity"),
        MAPPER("Mapper"),
        MAPPER_XML("Xml");
        /**
         * 模块
         */
        private String model;

        Model(String model) {
            this.model = model;
        }

        public String getModel() {
            return model;
        }
    }



    /**
     * 添加组件
     *
     * @param panel
     * @param row
     * @return
     */
    @Override
    public Integer addComponents(JPanel panel, Integer row) {
        parentComponent = new Component();
        addComponent(panel,row++,0,PARENT,"父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名",parentComponent);

//        controllerComponent = new Component();
//        addComponent(panel,row,0,CONTROLLER,"默认为{parent}.controller",controllerComponent);

        serviceComponent = new Component();
        addComponent(panel,row, 0,SERVICE,"默认为{parent}.service",serviceComponent);

        serviceImplComponent = new Component();
        addComponent(panel,row++,2, SERVICE_IMPL,"默认为{parent}.service.impl",serviceImplComponent);

        mapperComponent = new Component();
        addComponent(panel,row,0, MAPPER,"默认为{parent}.mapper",mapperComponent);

        entityComponent = new Component();
        addComponent(panel,row,2, ENTITY,"默认为{parent}.entity",entityComponent);

        entityRadioButton = new JRadioButton();
        addComponment(entityRadioButton,panel,row++);

        xmlComponent = new Component();
        addComponent(panel,row,1,MAPPER_XML,"默认为{parent}.mapper.xml",xmlComponent);
        xmlRadioButton = new JRadioButton();
        addComponment(xmlRadioButton,panel,row++);

        return row;
    }


    private void addComponment(JRadioButton jRadioButton,JPanel panel,Integer row){
        jRadioButton.setText("覆盖");
        jRadioButton.setToolTipText("是否覆盖已有文件，注意可能会丢失已有代码");
        panel.add(jRadioButton, newGridConstraints(row++, 4,1,10));
    }

    /**
     * 初始化操作
     *
     * @param param
     */
    @Override
    public void init(Param param) {
        List<String> items = param.getItems();
        if (CollectionUtil.isEmpty(items)){
            items = new ArrayList<>();
            String projectName = param.getProjectName();
            String projectPath = param.getProjectPath();
            getAllProject(projectName, projectName, projectName, projectPath, items);
            param.setItems(items);
        }
        addItems(items);
        //项目选中
        Map<Model, String> packageModelSelecteds = param.getPackageModelSelecteds();
        if (packageModelSelecteds != null){
            Optional.ofNullable(packageModelSelecteds.get(PARENT)).ifPresent(item->parentComponent.getProject().setSelectedItem(item));
//            Optional.ofNullable(packageModelSelecteds.get(CONTROLLER)).ifPresent(item->controllerComponent.getProject().setSelectedItem(item));
//            Optional.ofNullable(packageModelSelecteds.get(SERVICE)).ifPresent(item->serviceComponent.getProject().setSelectedItem(item));
//            Optional.ofNullable(packageModelSelecteds.get(SERVICE_IMPL)).ifPresent(item->serviceImplComponent.getProject().setSelectedItem(item));
//            Optional.ofNullable(packageModelSelecteds.get(MAPPER)).ifPresent(item->mapperComponent.getProject().setSelectedItem(item));
//            Optional.ofNullable(packageModelSelecteds.get(MAPPER_XML)).ifPresent(item->xmlComponent.getProject().setSelectedItem(item));
        }

        //src选中
        Map<Model, String> packageSrcSelecteds = param.getPackageSrcSelecteds();
        if (packageSrcSelecteds != null){
            Optional.ofNullable(packageSrcSelecteds.get(PARENT)).ifPresent(item->parentComponent.getSrcProject().setText(item));
//            Optional.ofNullable(packageSrcSelecteds.get(CONTROLLER)).ifPresent(item->controllerComponent.getSrcProject().setSelectedItem(item));
//            Optional.ofNullable(packageSrcSelecteds.get(SERVICE)).ifPresent(item->serviceComponent.getSrcProject().setSelectedItem(item));
//            Optional.ofNullable(packageSrcSelecteds.get(SERVICE_IMPL)).ifPresent(item->serviceImplComponent.getSrcProject().setSelectedItem(item));
//            Optional.ofNullable(packageSrcSelecteds.get(MAPPER)).ifPresent(item->mapperComponent.getSrcProject().setSelectedItem(item));
            Optional.ofNullable(packageSrcSelecteds.get(MAPPER_XML)).ifPresent(item->xmlComponent.getSrcProject().setText(item));
        }
        //包
        Map<Model, String> packages = param.getPackages();
        if (packages != null){
            Optional.ofNullable(packages.get(PARENT)).ifPresent(item->parentComponent.getPacking().setText(item));
//            Optional.ofNullable(packages.get(CONTROLLER)).ifPresent(item->controllerComponent.getPacking().setText(item));
            Optional.ofNullable(packages.get(SERVICE)).ifPresent(item->{
                if (!Objects.equals("service",item)){
                    serviceComponent.getPacking().setText(item);
                }
            });
            Optional.ofNullable(packages.get(SERVICE_IMPL)).ifPresent(item->{
                if (!Objects.equals("service.impl",item)){
                    serviceImplComponent.getPacking().setText(item);
                }
            });
            Optional.ofNullable(packages.get(ENTITY)).ifPresent(item->{
                if (!Objects.equals("entity",item)){
                    entityComponent.getPacking().setText(item);
                }
            });
            Optional.ofNullable(packages.get(MAPPER)).ifPresent(item->{
                if (!Objects.equals("mapper",item)){
                    mapperComponent.getPacking().setText(item);
                }
            });
            Optional.ofNullable(packages.get(MAPPER_XML)).ifPresent(item->{
                if (!Objects.equals("mapper.xml",item)){
                    xmlComponent.getPacking().setText(item);
                }
            });
        }
        PluginPackageConfig packageConfig = param.getPackageConfig();
        if (ObjectUtil.isNotEmpty(packageConfig)){
            //覆盖
            entityRadioButton.setSelected(packageConfig.isEntityFileOverride());
            xmlRadioButton.setSelected(packageConfig.isXmlFileOverride());
        }

    }

    /**
     * 将输入框内的数据添加到{@link Param}进行缓存
     *
     * @param param
     */
    @Override
    public void setParam(Param param,Integer type) {
        Map<PackingComponentImpl.Model, String> packageModelSelecteds = param.getPackageModelSelecteds();
        if (packageModelSelecteds == null){
            packageModelSelecteds = new HashMap<>();
            param.setPackageModelSelecteds(packageModelSelecteds);
        }
        setModelSelected(packageModelSelecteds);
        Map<Model, String> packageSrcSelecteds = param.getPackageSrcSelecteds();
        if (packageSrcSelecteds == null){
            packageSrcSelecteds = new HashMap<>();
            param.setPackageSrcSelecteds(packageSrcSelecteds);
        }
        setSrcSelected(packageSrcSelecteds);
        //package
        Map<Model, String> packages = param.getPackages();
        if (packages == null){
            packages = new HashMap<>();
            param.setPackages(packages);
        }
        packages.put(PARENT,parentComponent.getPacking().getText());
//        packages.put(CONTROLLER,controllerComponent.getPacking().getText());
        packages.put(SERVICE,StrUtil.blankToDefault(serviceComponent.getPacking().getText(),"service"));
        packages.put(SERVICE_IMPL,StrUtil.blankToDefault(serviceImplComponent.getPacking().getText(),"service.impl"));
        packages.put(ENTITY,StrUtil.blankToDefault(entityComponent.getPacking().getText(),"entity"));
        packages.put(MAPPER,StrUtil.blankToDefault(mapperComponent.getPacking().getText(),"mapper"));
        packages.put(MAPPER_XML,StrUtil.blankToDefault(xmlComponent.getPacking().getText(),"mapper.xml"));

        //
        PluginPackageConfig packageConfig = getPackageConfig(param);

        Optional.ofNullable(packages.get(PARENT)).ifPresent(value->packageConfig.setParent(value));
//        Optional.ofNullable(packages.get(CONTROLLER)).ifPresent(value->packageConfig.setController(value));
        Optional.ofNullable(packages.get(SERVICE)).ifPresent(value->packageConfig.setService(value));
        Optional.ofNullable(packages.get(SERVICE_IMPL)).ifPresent(value->packageConfig.setServiceImpl(value));
        Optional.ofNullable(packages.get(ENTITY)).ifPresent(value->packageConfig.setEntity(value));
        Optional.ofNullable(packages.get(MAPPER)).ifPresent(value->packageConfig.setMapper(value));
        Optional.ofNullable(packages.get(MAPPER_XML)).ifPresent(value->packageConfig.setXml(value));

        //文件是否覆盖
        packageConfig.setEntityFileOverride(entityRadioButton.isSelected());
        packageConfig.setXmlFileOverride(xmlRadioButton.isSelected());

        GlobalConfig globalConfig = param.getGlobalConfig();
        if (ObjectUtil.isEmpty(globalConfig)){
            globalConfig = new GlobalConfig();
            param.setGlobalConfig(globalConfig);
        }

        //输出目录
        String path = param.getProjectPath();
        String packageModelDir = packageModelSelecteds.get(PARENT);
        if (!path.endsWith(packageModelDir)){
            path += File.separator + packageModelDir;
        }
        String packageSrcDir = packageSrcSelecteds.get(PARENT);
        String outputDir = path + File.separator + packageSrcDir;
        globalConfig.setOutputDir(outputDir);
        //xml输出目录
        String xmlSrcDir = StrUtil.blankToDefault(packageSrcSelecteds.get(MAPPER_XML),packageSrcDir);
        String xmlOutputDir = path + File.separator + xmlSrcDir;
        packageConfig.setXmlOutputDir(xmlOutputDir);
        packageConfig.setXmlSrcDir(xmlSrcDir);
        packageConfig.setParentOutputDir(outputDir);

        Cache.setParam(param.getProjectPath(),param);
    }


    /**
     * 获取PluginPackageConfig，无则创建
     * @param param
     * @return
     */
    private PluginPackageConfig getPackageConfig(Param param){
        PluginPackageConfig packageConfig = param.getPackageConfig();
        if (ObjectUtil.isEmpty(packageConfig)){
            packageConfig = new PluginPackageConfig();
            param.setPackageConfig(packageConfig);
        }
        return packageConfig;
    }

    /**
     * 设置Model选中
     * @param packageModelSelecteds
     */
    private void setModelSelected(Map<PackingComponentImpl.Model, String> packageModelSelecteds){
        setSelected(parentComponent.getProject(),PARENT,packageModelSelecteds);
//        setSelected(controllerComponent.getProject(),CONTROLLER,packageModelSelecteds);
//        setSelected(serviceComponent.getProject(),SERVICE,packageModelSelecteds);
//        setSelected(serviceImplComponent.getProject(),SERVICE_IMPL,packageModelSelecteds);
//        setSelected(mapperComponent.getProject(),MAPPER,packageModelSelecteds);
//        setSelected(xmlComponent.getProject(),MAPPER_XML,packageModelSelecteds);
    }

    /**
     * 设置src选中
     * @param packageSrcSelecteds
     */
    private void setSrcSelected(Map<PackingComponentImpl.Model, String> packageSrcSelecteds){
        setSelected(parentComponent.getSrcProject(),PARENT,packageSrcSelecteds);
//        setSelected(controllerComponent.getSrcProject(),CONTROLLER,packageSrcSelecteds);
//        setSelected(serviceComponent.getSrcProject(),SERVICE,packageSrcSelecteds);
//        setSelected(serviceImplComponent.getSrcProject(),SERVICE_IMPL,packageSrcSelecteds);
//        setSelected(mapperComponent.getSrcProject(),MAPPER,packageSrcSelecteds);
        setSelected(xmlComponent.getSrcProject(),MAPPER_XML,packageSrcSelecteds);
    }

    /**
     * 设置选中的值
     * @param project
     * @param model
     * @param packageSelecteds
     */
    private void setSelected(JComboBox project,Model model,Map<PackingComponentImpl.Model, String> packageSelecteds){
        Object selectedItem = project.getSelectedItem();
        packageSelecteds.put(model,selectedItem.toString());
    }

    private void setSelected(JTextField project,Model model,Map<PackingComponentImpl.Model, String> packageSelecteds){
        Object selectedItem = project.getText();
        packageSelecteds.put(model,selectedItem.toString());
    }


    /**
     * 添加组件
     * @param panel
     * @param row
     * @param model
     * @param tipText
     * @param component
     * @return
     */
    private void addComponent(JPanel panel, Integer row, Integer column,Model model, String tipText, Component component){
        final JLabel controllerLabel = new JLabel();
        controllerLabel.setText(model.getModel() + StrUtil.COLON);
        panel.add(controllerLabel, newGridConstraints(row, column++));

        if (model == PARENT){
            JComboBox project = component.getProject();
            panel.add(project, newGridConstraints(row, column++,1));

//            JComboBox srcProject = component.getSrcProject();
            JTextField srcProject = component.getSrcProject();

//            srcProject.addItem("src/main/java");
            srcProject.setText("src/main/java");
            panel.add(srcProject, newGridConstraints(row, column++,1));
        }

        if (model == MAPPER_XML){
            JTextField srcProject = component.getSrcProject();
//            srcProject.addItem("src/main/java");
//            srcProject.addItem("src/main/resources");
            srcProject.setText("src/main/java");
            panel.add(srcProject, newGridConstraints(row, column++,1));
        }

        JTextField packing = component.getPacking();
        packing.setToolTipText(tipText);
        panel.add(packing, newGridConstraints(row, column++, 1));
    }

    /**
     * 添加节点
     * @param items 节点集合
     */
    private void addItems(List<String> items) {
        removeAllItem();
        items.forEach(this::addItem);
    }

    /**
     * 添加项目节点
     *
     * @param item 项目节点
     */
    private void addItem(String item) {
        parentComponent.addItem(item);
//        controllerComponent.addItem(item);
//        serviceComponent.addItem(item);
//        serviceImplComponent.addItem(item);
//        mapperComponent.addItem(item);
//        xmlComponent.addItem(item);
    }

    /**
     * 移除所有节点
     */
    private void removeAllItem() {
        parentComponent.removeAllItem();
//        controllerComponent.removeAllItem();
//        serviceComponent.removeAllItem();
//        serviceImplComponent.removeAllItem();
//        mapperComponent.removeAllItem();
//        xmlComponent.removeAllItem();
    }

    /**
     * 递归获取所有项目
     *
     * @param rootProject
     * @param parentProject
     * @param projectName
     * @param projectPath
     * @param files
     */
    private void getAllProject(String rootProject, String parentProject, String projectName, String projectPath, java.util.List<String> files) {
        //获取该工程下的所有项目
        File[] subFiles = ActionListenerUtils.getAllFiles(projectPath);
        boolean flg = true;
        for (File file : subFiles) {
            if (file.isDirectory()) {
                String fileName = file.getName();
                if (Objects.equals("src", fileName)) {
                    flg = false;
                    break;
                }
            }
        }
        if (flg) {
            for (File file : subFiles) {
                String fileName = file.getName();
                if (!Objects.equals(".idea", fileName) && !Objects.equals(".svn", fileName)) {
                    getAllProject(rootProject, projectName, fileName, projectPath + File.separator + fileName, files);
                }
            }
        } else {
            if (Objects.equals(rootProject, parentProject)) {
                files.add(projectName);
            } else {
                files.add(parentProject + File.separator + projectName);
            }
        }
    }


    class Component{
        private JComboBox project;
        private JTextField srcProject;
        private JTextField packing;

        public Component() {
            this.project = new JComboBox();
            this.srcProject = new JTextField();
            this.packing = new JTextField();
        }

        public JComboBox getProject() {
            return project;
        }

        public JTextField getPacking() {
            return packing;
        }

        public JTextField getSrcProject() {
            return srcProject;
        }

        public void addItem(String item){
            project.addItem(item);
        }

        public void removeAllItem(){
            project.removeAllItems();
        }
    }

    public Component getParentComponent() {
        return parentComponent;
    }

    public Component getServiceComponent() {
        return serviceComponent;
    }

    public Component getServiceImplComponent() {
        return serviceImplComponent;
    }

    public Component getMapperComponent() {
        return mapperComponent;
    }

    public Component getXmlComponent() {
        return xmlComponent;
    }
}
