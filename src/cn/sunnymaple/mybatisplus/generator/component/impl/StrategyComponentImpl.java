package cn.sunnymaple.mybatisplus.generator.component.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.sunnymaple.mybatisplus.generator.cache.Cache;
import cn.sunnymaple.mybatisplus.generator.cache.Param;
import cn.sunnymaple.mybatisplus.generator.component.IComponent;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import javax.swing.*;

/**
 * 策略配置组件
 * @author wangzb
 * @date 2020/8/8 23:20
 */
public class StrategyComponentImpl implements IComponent {

    private JRadioButton swaggerRadioButton;
    private JRadioButton lombokRadioButton;
    private JRadioButton buildRadioButton;

    /**
     * 添加组件
     *
     * @param panel
     * @param row
     * @return
     */
    @Override
    public Integer addComponents(JPanel panel, Integer row) {
        swaggerRadioButton = new JRadioButton();
        addComponent(panel,swaggerRadioButton,row,1,"swagger");
        lombokRadioButton = new JRadioButton();
//        addComponent(panel,lombokRadioButton,row,2,"lombok");

        lombokRadioButton.setText("lombok");
        panel.add(lombokRadioButton, newGridConstraints(row, 2));

        buildRadioButton = new JRadioButton();
        addComponent(panel,buildRadioButton,row++,3,"builder");
        return row;
    }

    /**
     * 初始化操作
     *
     * @param param
     */
    @Override
    public void init(Param param) {
        GlobalConfig globalConfig = param.getGlobalConfig();
        if (ObjectUtil.isNotEmpty(globalConfig)){
            swaggerRadioButton.setSelected(globalConfig.isSwagger2());
        }
        StrategyConfig strategyConfig = param.getStrategyConfig();
        if (ObjectUtil.isNotEmpty(strategyConfig)){
            lombokRadioButton.setSelected(strategyConfig.isEntityLombokModel());
            buildRadioButton.setSelected(strategyConfig.isEntityBuilderModel());
        }
    }

    /**
     * 将输入框内的数据添加到{@link Param}进行缓存
     *
     * @param param
     */
    @Override
    public void setParam(Param param,Integer type) {
        StrategyConfig strategyConfig = param.getStrategyConfig();
        if (ObjectUtil.isEmpty(strategyConfig)){
            strategyConfig = new StrategyConfig();
            param.setStrategyConfig(strategyConfig);
        }
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setControllerMappingHyphenStyle(true);

        strategyConfig.setEntityLombokModel(lombokRadioButton.isSelected());
        strategyConfig.setEntityBuilderModel(buildRadioButton.isSelected());

        GlobalConfig globalConfig = param.getGlobalConfig();
        if (ObjectUtil.isEmpty(globalConfig)){
            globalConfig = new GlobalConfig();
            param.setGlobalConfig(globalConfig);
        }
        globalConfig.setSwagger2(swaggerRadioButton.isSelected());
        Cache.setParam(param.getProjectPath(),param);
    }



    public JRadioButton getSwaggerRadioButton() {
        return swaggerRadioButton;
    }

    public JRadioButton getLombokRadioButton() {
        return lombokRadioButton;
    }
}
