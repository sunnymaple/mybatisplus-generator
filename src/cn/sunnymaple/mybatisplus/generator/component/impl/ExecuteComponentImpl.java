package cn.sunnymaple.mybatisplus.generator.component.impl;

import cn.sunnymaple.mybatisplus.generator.listener.CleanActionListener;
import cn.sunnymaple.mybatisplus.generator.listener.GeneratorActionListener;
import cn.sunnymaple.mybatisplus.generator.component.IComponent;
import com.intellij.uiDesigner.core.GridConstraints;

import javax.swing.*;
import java.awt.*;

/**
 * 执行区域组件
 * @author wangzb
 * @date 2020/8/8 23:36
 */
public class ExecuteComponentImpl implements IComponent{
    /**
     * 清空消息区按钮
     */
    private JButton clearButton;

    private JTextArea messageTextArea;

    public ExecuteComponentImpl() {
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
        clearButton = new JButton();
        clearButton.setText("清空消息区");
        panel.add(clearButton, new GridConstraints(row, 1, 1, 1, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


        JButton generatorButton = new JButton();
        generatorButton.setText("一键生成代码");
        panel.add(generatorButton, new GridConstraints(row++, 2, 1, 2, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


        messageTextArea = new JTextArea("",1, TextArea.SCROLLBARS_VERTICAL_ONLY);
        messageTextArea.setEditable(false);
        messageTextArea.setEnabled(true);
        JScrollPane scrollpane=new JScrollPane();//创建滚动条面板
        scrollpane.setViewportView(messageTextArea);
        panel.add(scrollpane, new GridConstraints(row++, 0, 1, 5, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null,
                new Dimension(150, 50), null, 0, false));

        //为清除按钮添加事件
        clearButton.addActionListener(new CleanActionListener(messageTextArea));
        //为一键生成代码按钮添加事件
        generatorButton.addActionListener(new GeneratorActionListener(messageTextArea));
        return row;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JTextArea getMessageTextArea() {
        return messageTextArea;
    }
}
