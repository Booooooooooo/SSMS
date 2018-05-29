import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModifyWin extends JDialog implements ActionListener {
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
    private String temp1, temp2;
    private Font font = new Font("Dialog", Font.PLAIN, 15);

    public ModifyWin(Frame owner, boolean modal, StuModel model, int rowNum, int index){
        super(owner, "修改学生表", modal);
        this.index = index;
        temp1 = model.getValueAt(rowNum, 0).toString();
        label1 = new JLabel("学号");
        label2 = new JLabel("姓名");
        label3 = new JLabel("性别");
        label4 = new JLabel("院系");
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        text1 = new JTextField(10);
        text1.setText(model.getValueAt(rowNum, 0).toString());
        text2 = new JTextField(10);
        text2.setText(model.getValueAt(rowNum, 1).toString());
        text3 = new JTextField(10);
        text3.setText(model.getValueAt(rowNum, 2).toString());
        text4 = new JTextField(10);
        text4.setText(model.getValueAt(rowNum, 3).toString());
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
    public ModifyWin(Frame owner, boolean modal, CourModel model, int rowNum, int index){
        super(owner, "修改课程表", modal);
        this.index = index;
        temp1 = model.getValueAt(rowNum, 0).toString();
        label1 = new JLabel("课程号");
        label2 = new JLabel("课程名");
        label3 = new JLabel("学分");
        label4 = new JLabel("授课老师");
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        text1 = new JTextField(10);
        text1.setText(model.getValueAt(rowNum, 0).toString());
        text2 = new JTextField(10);
        text2.setText(model.getValueAt(rowNum, 1).toString());
        text3 = new JTextField(10);
        text3.setText(model.getValueAt(rowNum, 2).toString());
        text4 = new JTextField(10);
        text4.setText(model.getValueAt(rowNum, 3).toString());
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
    public ModifyWin(Frame owner, boolean modal, ScoModel model, int rowNum, int index){
        super(owner, "修改成绩表", modal);
        this.index = index;
        temp1 = model.getValueAt(rowNum, 1).toString();
        temp2 = model.getValueAt(rowNum, 2).toString();
        label1 = new JLabel("成绩");
        label2 = new JLabel("学号");
        label3 = new JLabel("课程号");
        label4 = new JLabel("重修标记");
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        text1 = new JTextField(10);
        text1.setText(model.getValueAt(rowNum, 0).toString());
        text2 = new JTextField(10);
        text2.setText(model.getValueAt(rowNum, 1).toString());
        text3 = new JTextField(10);
        text3.setText(model.getValueAt(rowNum, 2).toString());
        text4 = new JTextField(10);
        text4.setText(model.getValueAt(rowNum, 3).toString());
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
                String url = "jdbc:mysql://localhost/studentsystem?useSSL=false&serverTimezone=GMT%2B8";
                String user = "root";
                String passwd = "wyb980401";
                ct = DriverManager.getConnection(url, user, passwd);
                System.out.println("修改连接成功");

                String strsql = "";
                switch (index){
                    case 0:
                        strsql = "update student set sno = ?, name = ?, sex = ?, department = ? where sno = ?";
                        try{
                            pstmt = ct.prepareStatement(strsql);
                            pstmt.setString(1, text1.getText());
                            pstmt.setString(2, text2.getText());
                            pstmt.setString(3, text3.getText());
                            pstmt.setString(4, text4.getText());
                            pstmt.setString(5, temp1);
                            pstmt.executeUpdate();
                            if(!text1.getText().equals("")){
                                strsql = "select password from user_pass where username = ?";
                                pstmt = ct.prepareStatement(strsql);
                                pstmt.setString(1, temp1);
                                rs = pstmt.executeQuery();
                                while(rs.next()){
                                    if(rs.getString("password").equals(temp1.substring(temp1.length() - 3, temp1.length()))){
                                        strsql = "update user_pass set username = ?, password = ? where username = ?";
                                        pstmt = ct.prepareStatement(strsql);
                                        pstmt.setString(1, text1.getText());
                                        pstmt.setString(2, text1.getText().substring(text1.getText().length() - 3, text1.getText().length()));
                                        pstmt.setString(3, temp1);
                                        pstmt.executeUpdate();
                                    }else{
                                        strsql = "update user_pass set username = ? where username = ?";
                                        pstmt = ct.prepareStatement(strsql);
                                        pstmt.setString(1, text1.getText());
                                        pstmt.setString(2, temp1);
                                        pstmt.executeUpdate();
                                    }
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(this, text1.getText() + "学生已存在");
                        }
                        this.dispose();
                        break;
                    case 1:
                        strsql = "update course set cno = ?, name = ?, mark = " + text3.getText() + ", teacher = ? where cno = ?";
                        try{
                            pstmt = ct.prepareStatement(strsql);
                            pstmt.setString(1, text1.getText());
                            pstmt.setString(2, text2.getText());
                            pstmt.setString(3, text4.getText());
                            pstmt.setString(4, temp1);
                            pstmt.executeUpdate();
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(this, text1.getText() + "课程已存在");
                        }
                        this.dispose();break;
                    case 2:
                        String flag = text4.getText();
                        if(flag.equals("否"))
                            strsql = "update score set score = " + text1.getText() + ", sno = ?, cno = ?, flag = false where sno = ? and cno = ?";
                        else
                            strsql = "update score set score = " + text1.getText() + ", sno = ?, cno = ?, flag = true where sno = ? and cno = ? ";
                        try{
                            pstmt = ct.prepareStatement(strsql);
                            pstmt.setString(1, text2.getText());
                            pstmt.setString(2, text3.getText());
                            pstmt.setString(3, temp1);
                            pstmt.setString(4, temp2);
                            pstmt.executeUpdate();
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(this, "信息有误或" + text2.getText() + "的 " + text3.getText() + "成绩已存在");
                        }
                        this.dispose();break;
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
