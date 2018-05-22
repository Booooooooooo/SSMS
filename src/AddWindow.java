import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddWindow extends JDialog implements ActionListener {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JButton confirmBtn;
    private JButton cancelBtn;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private int index;
    private int count;
    private Font font = new Font("Dialog", Font.PLAIN, 15);

    public AddWindow(Frame owner, boolean modal,int index){
        super(owner, "添加", modal);
        this.index = index;
        //this.count = count + 1;
        switch (index){
            case 0:
                label1 = new JLabel("学号");
                label2 = new JLabel("姓名");
                label3 = new JLabel("性别");
                label4 = new JLabel("院系");break;
            case 1:
                label1 = new JLabel("课程号");
                label2 = new JLabel("课程名");
                label3 = new JLabel("学分");
                label4 = new JLabel("授课老师");break;
            case 2:
                label1 = new JLabel("成绩");
                label2 = new JLabel("学号");
                label3 = new JLabel("课程号");
                label4 = new JLabel("重修标记");break;
        }
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        text1 = new JTextField(10);
        text2 = new JTextField(10);
        text3 = new JTextField(10);
        text4 = new JTextField(10);
        text1.setFont(font);
        text2.setFont(font);
        text3.setFont(font);
        text4.setFont(font);
        confirmBtn = new JButton("确认");
        cancelBtn = new JButton("取消");
        confirmBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        confirmBtn.setFont(font);
        cancelBtn.setFont(font);

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel1.setLayout(new GridLayout(4, 1));
        panel2.setLayout(new GridLayout(4, 1));
        panel3.add(confirmBtn);
        panel3.add(cancelBtn);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(label4);
        panel2.add(text1);
        panel2.add(text2);
        panel2.add(text3);
        panel2.add(text4);
        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);
        setSize(300, 200);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == confirmBtn){
            Connection ct = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try{
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost/studentsystem";
                String user = "root";
                String passwd = "wyb980401";
                ct = DriverManager.getConnection(url, user, passwd);
                System.out.println("修改连接成功");

                String strsql = "";
                switch (index){
                    case 0:
                        strsql = "insert into student values (?, ?, ?, ?)";
                        try{
                            pstmt = ct.prepareStatement(strsql);
                            pstmt.setString(1, text1.getText());
                            pstmt.setString(2, text2.getText());
                            pstmt.setString(3, text3.getText());
                            pstmt.setString(4, text4.getText());
                            pstmt.executeUpdate();
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(this, text1.getText() + "学生已存在");
                        }
                        this.dispose();
                        break;
                    case 1:
                        strsql = "insert into course values(?, ?, " + text3.getText() +", ?)";
                        try{
                            pstmt = ct.prepareStatement(strsql);
                            pstmt.setString(1, text1.getText());
                            pstmt.setString(2, text2.getText());
                            pstmt.setString(3, text4.getText());
                            pstmt.executeUpdate();
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(this, text1.getText() + "课程已存在");
                        }
                        this.dispose();
                        break;
                    case 2:
                        String flag = text4.getText();
                        if(flag.equals("否"))
                            strsql = "insert into score values (" + text1.getText() + "?, ?, false)";
                        else
                            strsql = "insert into score values (" + text1.getText() + "?, ?, true)";
                        try{
                            pstmt = ct.prepareStatement(strsql);
                            pstmt.setString(1, text2.getText());
                            pstmt.setString(2, text3.getText());
                            pstmt.executeUpdate();
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(this, "信息有误或" + text2.getText() + "的 " + text3.getText() + "成绩已存在");
                        }
                        this.dispose();
                        break;
                }

            }catch (Exception arg1){
                arg1.printStackTrace();
            }finally {
                try{
                    if(rs != null){
                        rs.close();
                        rs = null;
                    }
                    if(pstmt != null){
                        pstmt.close();
                        pstmt = null;
                    }
                    if(ct != null){
                        ct.close();
                        ct = null;
                    }
                }catch (Exception arg2){
                    arg2.printStackTrace();
                }
            }
        }
        else if(event.getSource() == cancelBtn){
            this.dispose();
        }
    }
}
