package cn.sunnymaple.mybatisplus.generator.component.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.sunnymaple.mybatisplus.generator.cache.Cache;
import cn.sunnymaple.mybatisplus.generator.cache.Param;
import cn.sunnymaple.mybatisplus.generator.component.IComponent;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * 数据库/源相关组件
 * @author wangzb
 * @date 2020/8/8 18:34
 */
public class DataSourceComponentImpl implements IComponent {

    /**
     * 数据库类型
     */
    private JComboBox dbTypeComboBox;
    /**
     * 用户名
     */
    private JTextField userNameTextField;
    /**
     * url
     */
    private JTextField urLTextField;

    /**
     * 密码
     */
    private JPasswordField passwordJPasswordField;

    /**
     * 表前缀
     */
    private JTextField tablePrefixTextField;
    /**
     * 数据表
     */
    private JTextArea tablesTextArea;


    enum Driver{
        MYSQL(DbType.MYSQL,"com.mysql.cj.jdbc.Driver"),
        SQL_SERVER(DbType.SQL_SERVER,"com.microsoft.sqlserver.jdbc.SQLServerDriver"),
        ;
        private DbType dbType;
        private String driverName;

        Driver(DbType dbType, String driverName) {
            this.dbType = dbType;
            this.driverName = driverName;
        }

        public String getDriverName() {
            return driverName;
        }

        public DbType getDbType() {
            return dbType;
        }

        public static String getDriverName(DbType dbType){
            return Arrays.stream(Driver.values())
                    .filter(driver -> driver.getDbType() == dbType)
                    .findFirst().get().getDriverName();
        }
    }

    private void setDataSourceText(){
        userNameTextField.setText("");
        urLTextField.setText("");
        passwordJPasswordField.setText("");
    }

    private void setStrategyText(){
        tablePrefixTextField.setText("");
        tablesTextArea.setText("");
    }


    /**
     * 添加一行组件
     *
     * @param panel
     */
    @Override
    public Integer addComponents(JPanel panel,Integer row) {
        final JLabel label1 = new JLabel();
        //数据库类型
        Integer column = 0;
        label1.setText("数据库:");
        panel.add(label1, newGridConstraints(row,column++));
        dbTypeComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement(DbType.MYSQL.getDb());
        defaultComboBoxModel1.addElement(DbType.SQL_SERVER.getDb());
        dbTypeComboBox.setModel(defaultComboBoxModel1);
        panel.add(dbTypeComboBox, newGridConstraints(row,column++,1));


        //URL
        final JLabel urlLabel = new JLabel();
        urlLabel.setText("URL:");
        panel.add(urlLabel, newGridConstraints(row,column++));
        urLTextField = new JTextField();
        urLTextField.setToolTipText("必填");
        panel.add(urLTextField, newGridConstraints(row++,column,1));

        column = 0;
        //用户名
        final JLabel userNameLabel = new JLabel();
        userNameLabel.setText("用户名:");
        panel.add(userNameLabel, newGridConstraints(row, column++));
        userNameTextField = new JTextField();
        userNameTextField.setToolTipText("必填");
        panel.add(userNameTextField, newGridConstraints(row, column++, 1));

        //密码
        final JLabel passwordLabel = new JLabel();
        passwordLabel.setText("密码:");
        panel.add(passwordLabel, newGridConstraints(row, column++));
        passwordJPasswordField = new JPasswordField();
        passwordJPasswordField.setToolTipText("必填");
        panel.add(passwordJPasswordField, newGridConstraints(row++, column++,1));

        column = 0;
        //表的前缀
        final JLabel label7 = new JLabel();
        label7.setText("表前缀:");
        panel.add(label7, newGridConstraints(row, column++));
        tablePrefixTextField = new JTextField();
        tablePrefixTextField.setToolTipText("输入表前缀");
        panel.add(tablePrefixTextField, newGridConstraints(row, column++,  1));

        //数据/表
        final JLabel label9 = new JLabel();
        label9.setText("数据/表:");
        panel.add(label9, newGridConstraints(row, column++));

        tablesTextArea = new JTextArea("",1, TextArea.SCROLLBARS_VERTICAL_ONLY);
        tablesTextArea.setEditable(true);
        tablesTextArea.setEnabled(true);
        tablesTextArea.setToolTipText("输入要生成POJO实体类的表名，多个逗号隔开，不填默认为所有的表");

        JScrollPane scrollpane = new JScrollPane();//创建滚动条面板
        scrollpane.setViewportView(tablesTextArea);
        panel.add(scrollpane, new GridConstraints(row++, column++, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null,
                new Dimension(150, 20), null, 0, false));

        return row;
    }

    /**
     * 初始化操作
     *
     * @param param
     */
    @Override
    public void init(Param param) {
        DataSourceConfig dataSourceConfig = param.getDataSourceConfig();
        if (ObjectUtil.isNotEmpty(dataSourceConfig)){
            DbType dbType = dataSourceConfig.getDbType();
            dbTypeComboBox.setSelectedItem(dbType.getDb());
            String username = dataSourceConfig.getUsername();
            userNameTextField.setText(username);
            urLTextField.setText(dataSourceConfig.getUrl());
            passwordJPasswordField.setText(dataSourceConfig.getPassword());
        }else {
            setDataSourceText();
        }
        StrategyConfig strategyConfig = param.getStrategyConfig();
        if (ObjectUtil.isNotEmpty(strategyConfig)){
            tablePrefixTextField.setText(StrUtil.join(StrUtil.COMMA,strategyConfig.getTablePrefix()));
            tablesTextArea.setText(StrUtil.join(StrUtil.COMMA,strategyConfig.getInclude()));
        }else {
            setStrategyText();
        }
    }

    /**
     * 将输入框内的数据添加到{@link Param}进行缓存
     *
     * @param param
     */
    @Override
    public void setParam(Param param,Integer type) {
        //数据库配置
        DataSourceConfig dataSourceConfig = param.getDataSourceConfig();
        if (ObjectUtil.isEmpty(dataSourceConfig)){
            dataSourceConfig = new DataSourceConfig();
            param.setDataSourceConfig(dataSourceConfig);
        }
        DbType dbType = DbType.getDbType(dbTypeComboBox.getSelectedItem().toString());
        dataSourceConfig.setDbType(dbType);
        if (dbType == DbType.MYSQL){
            dataSourceConfig.setDriverName(Driver.getDriverName(dbType));
        }
        //URL
        String urlText = urLTextField.getText();
        illegalArgument(type, urlText, "请输入URL");

        dataSourceConfig.setUrl(urlText);
        //userName
        String userNameText = userNameTextField.getText();
        illegalArgument(type, userNameText, "请输入用户名");
        dataSourceConfig.setUsername(userNameText);

        dataSourceConfig.setPassword(passwordJPasswordField.getText());
        //策略配置
        StrategyConfig strategyConfig = param.getStrategyConfig();
        if (ObjectUtil.isEmpty(strategyConfig)){
            strategyConfig = new StrategyConfig();
            param.setStrategyConfig(strategyConfig);
        }
        String tablePrefixStr = tablePrefixTextField.getText();
        if (StrUtil.isNotBlank(tablePrefixStr)){
            strategyConfig.setTablePrefix(tablePrefixStr.split(StrUtil.COMMA));
        }
        String tableStr = tablesTextArea.getText();
        if (StrUtil.isNotBlank(tableStr)){
            strategyConfig.setInclude(tableStr.split(StrUtil.COMMA ));
        }
        Cache.setParam(param.getProjectPath(),param);
    }

    private void illegalArgument(Integer type,String context,String message){
        if (type == 2 && StrUtil.isBlank(context)){
            Messages.showMessageDialog(message,"提示消息",Messages.getErrorIcon());
            throw new IllegalArgumentException(message);
        }
    }

    public JComboBox getDbTypeComboBox() {
        return dbTypeComboBox;
    }

    public JTextField getUserNameTextField() {
        return userNameTextField;
    }

    public JTextField getUrLTextField() {
        return urLTextField;
    }

    public JPasswordField getPasswordJPasswordField() {
        return passwordJPasswordField;
    }

    public JTextField getTablePrefixTextField() {
        return tablePrefixTextField;
    }

    public JTextArea getTablesTextArea() {
        return tablesTextArea;
    }
}
