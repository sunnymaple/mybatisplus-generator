package cn.sunnymaple.mybatisplus.generator.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 清楚消息提示框内容
 * @author wangzb
 * @date 2020-08-09 13:12
 */
public class CleanActionListener implements ActionListener {

    /**
     * 执行信息显示框
     */
    private JTextArea messageTextArea;

    public CleanActionListener(JTextArea messageTextArea) {
        this.messageTextArea = messageTextArea;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        messageTextArea.setText("");
    }
}
