package cn.sunnymaple.mybatisplus.generator.component.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.sunnymaple.mybatisplus.generator.cache.Cache;
import cn.sunnymaple.mybatisplus.generator.cache.Param;
import cn.sunnymaple.mybatisplus.generator.component.IComponent;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;

import javax.swing.*;

/**
 * 全局配置组件
 * @author wangzb
 * @date 2020/8/8 22:19
 */
public class GlobalComponentImpl implements IComponent{
    /**
     * 作者
     */
    private JTextField authorTextField;

    /**
     * 添加组件
     *
     * @param panel
     * @param row
     * @return
     */
    @Override
    public Integer addComponents(JPanel panel, Integer row) {
        final JLabel authorLabel = new JLabel();
        authorLabel.setText("作者：");
        panel.add(authorLabel, newGridConstraints(row, 0));
        authorTextField = new JTextField();
        authorTextField.setToolTipText("实体类注释中的@author");
        panel.add(authorTextField, newGridConstraints(row++, 1, 1));
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
            authorTextField.setText(globalConfig.getAuthor());
        }
    }

    /**
     * 将输入框内的数据添加到{@link Param}进行缓存
     *
     * @param param
     */
    @Override
    public void setParam(Param param,Integer type) {
        //全局配置
        GlobalConfig globalConfig = param.getGlobalConfig();
        if (ObjectUtil.isEmpty(globalConfig)){
            globalConfig = new GlobalConfig();
            param.setGlobalConfig(globalConfig);
        }
        globalConfig.setAuthor(authorTextField.getText());

        //其他固定配置
        globalConfig.setBaseColumnList(true);
        globalConfig.setFileOverride(false);
        globalConfig.setOpen(false);
        Cache.setParam(param.getProjectPath(),param);
    }

    public JTextField getAuthorTextField() {
        return authorTextField;
    }
}
