package cn.sunnymaple.mybatisplus.generator.component;

import cn.sunnymaple.mybatisplus.generator.cache.Param;
import com.intellij.uiDesigner.core.GridConstraints;

import javax.swing.*;
import java.awt.*;

/**
 * @author wangzb
 * @date 2020/8/8 18:31
 */
public interface IComponent {

    /**
     * 添加组件
     * @param panel
     * @param row
     * @return
     */
    Integer addComponents(JPanel panel,Integer row);

    /**
     * 初始化操作
     * @param param
     */
    default void init(Param param){}

    /**
     * 将输入框内的数据添加到{@link Param}进行缓存
     * @param param
     * @param type
     */
    default void setParam(Param param,Integer type){}

    default GridConstraints newGridConstraints(Integer row, Integer column, Integer rowSpan){
        return newGridConstraints(row,column,rowSpan,150);
    }

    default GridConstraints newGridConstraints(Integer row, Integer column, Integer rowSpan,Integer i){
        return new GridConstraints(row++, column, rowSpan, 1, GridConstraints.ANCHOR_WEST,
                GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null,
                new Dimension(i, -1), null, 0, false);
    }

    default GridConstraints newGridConstraints(Integer row,Integer column){
        return new GridConstraints(row, column, 1, 1, GridConstraints.ANCHOR_EAST,
                GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null,
                new Dimension(44, 17), null, 0, false);
    }

    /**
     * 添加组件
     * @param panel
     * @param jRadioButton
     * @param row
     * @param column
     * @param text
     * @return
     */
    default Integer addComponent(JPanel panel, JRadioButton jRadioButton,Integer row,Integer column,String text){
//        final JLabel swaggerLabel = new JLabel();
//        swaggerLabel.setText(text);
//        panel.add(swaggerLabel, newGridConstraints(row, column++));
        jRadioButton.setText(text);
        panel.add(jRadioButton, newGridConstraints(row, column++,1,10));

        return row;
    }
}
