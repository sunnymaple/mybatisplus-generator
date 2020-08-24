package cn.sunnymaple.mybatisplus.generator.util;

import cn.hutool.core.util.StrUtil;
import cn.sunnymaple.mybatisplus.generator.action.MyBatisPlusGenerator;
import cn.sunnymaple.mybatisplus.generator.component.impl.ExecuteComponentImpl;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 事件监听对象公共工具类
 * @author wangzb
 * @date 2019-01-22 14:10
 * @company 矽甲（上海）信息科技有限公司
 */
public class ActionListenerUtils {

    /**
     * 换行符号
     */
    public final static String LINE_FEED_SYMBOL = "\n";


    /**
     * 打印
     * @param msg
     * @param message
     */
    public static void println(String msg,JTextArea message){
        message.append(ActionListenerUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        message.append(StrUtil.COLON);
        message.append(msg);
        message.append(LINE_FEED_SYMBOL);
    }

    /**
     * 打印
     * @param msg
     */
    public static void println(String msg){
        MyBatisPlusGenerator.GeneratorComponent generatorComponent = MyBatisPlusGenerator.getInstance();
        ExecuteComponentImpl component = (ExecuteComponentImpl) generatorComponent.getComponent(ExecuteComponentImpl.class);
        println(msg,component.getMessageTextArea());
    }

    /**
     * 打印
     * @param msg
     * @param e
     */
    public static void println(String msg,Exception e){
        println(msg + getErrorInfoFromException(e));
    }

    /**
     * 将Date时间格式化成format格式的字符串
     * @param date 日期Dte对象     *
     * @param format 日期格式
     * @return 日期字符串形式
     */
    public static String format(Date date, String format) {
        String datex = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        datex = dateFormat.format(date);
        return datex;
    }

    /**
     * 获取文件夹下都所有文件
     * @param dir 目标文件夹
     * @return
     */
    public static File[] getAllFiles(String dir){
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)){
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            return new File[0];
        }
        return dirFile.listFiles();
    }

    /**
     * 获取异常堆栈信息
     * @param e 异常
     * @return
     */
    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }
}
