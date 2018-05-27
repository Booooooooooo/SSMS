import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DelWin extends JDialog implements ActionListener {
    private JLabel display;
    private int index;
    private JButton button;
    private JButton cancelBtn;
    private String temp1;
    private String temp2;
    private JPanel panel1;
    private JPanel panel2;
    private Font font = new Font("Dialog", Font.PLAIN, 20);

    public DelWin(Frame owner, boolean modal, StuModel model, int rowNum, int index){
        super(owner, "确认删除", modal);
        this.index = index;
        display = new JLabel();
        display.setFont(font);
        button = new JButton("确认");
        button.addActionListener(this);
        button.setFont(font);
        cancelBtn = new JButton("取消");
        cancelBtn.addActionListener(this);
        cancelBtn.setFont(font);
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 1));
        panel2 = new JPanel();

        temp1 = model.getValueAt(rowNum, 0).toString();
        String a = model.getValueAt(rowNum, 0).toString();
        String b = model.getValueAt(rowNum, 1).toString();
        String c = model.getValueAt(rowNum, 2).toString();
        String d = model.getValueAt(rowNum, 3).toString();
        display.setText("   确认删除 " + a + " " + b + " " + c + " " + d + "?");
        panel2.add(button);
        panel2.add(cancelBtn);
        panel1.add(display, BorderLayout.CENTER);
        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        this.setSize(350, 200);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setVisible(true);//写在setsize之后才能正常显示
    }

    public DelWin(Frame owner, boolean modal, CourModel model, int rowNum, int index){
        super(owner, "确认删除", modal);
        this.index = index;
        display = new JLabel();
        display.setFont(font);
        button = new JButton("确认");
        button.addActionListener(this);
        button.setFont(font);
        cancelBtn = new JButton("取消");
        cancelBtn.addActionListener(this);
        cancelBtn.setFont(font);
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 1));
        panel2 = new JPanel();

        temp1 = model.getValueAt(rowNum, 0).toString();
        String a = model.getValueAt(rowNum, 0).toString();
        String b = model.getValueAt(rowNum, 1).toString();
        String c = model.getValueAt(rowNum, 2).toString();
        String d = model.getValueAt(rowNum, 3).toString();
        display.setText("   确认删除 " + a + " " + b + " " + c + " " + d + "?");
        panel2.add(button);
        panel2.add(cancelBtn);
        panel1.add(display, BorderLayout.CENTER);
        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        this.setSize(430, 200);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setVisible(true);//写在setsize之后才能正常显示
    }

    public DelWin(Frame owner, boolean modal, ScoModel model, int rowNum, int index){
        super(owner, "确认删除", modal);
        this.index = index;
        display = new JLabel();
        display.setFont(font);
        button = new JButton("确认");
        button.addActionListener(this);
        button.setFont(font);
        cancelBtn = new JButton("取消");
        cancelBtn.addActionListener(this);
        cancelBtn.setFont(font);
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 1));
        panel2 = new JPanel();

        temp1 = model.getValueAt(rowNum, 1).toString();
        temp2 = model.getValueAt(rowNum, 2).toString();
        String a = model.getValueAt(rowNum, 0).toString();
        String b = model.getValueAt(rowNum, 1).toString();
        String c = model.getValueAt(rowNum, 2).toString();
        String d = model.getValueAt(rowNum, 3).toString();
        display.setText("   确认删除 " + a + " " + b + " " + c + " " + d + "?");
        panel2.add(button);
        panel2.add(cancelBtn);
        panel1.add(display, BorderLayout.CENTER);
        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        this.setSize(400, 200);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setVisible(true);//写在setsize之后才能正常显示
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == button){
            Connection ct = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost/studentsystem?useSSL=false&serverTimezone=GMT%2B8";
                String user = "root";
                String passwd = "wyb980401";

                ct = DriverManager.getConnection(url, user, passwd);
                System.out.println("连接成功");
                switch (index){
                    case 0:
                        pstmt = ct.prepareStatement("delete from student where sno = ?");
                        pstmt.setString(1, temp1);
                        pstmt.executeUpdate();break;
                    case 1:
                        pstmt = ct.prepareStatement("delete from course where cno = ?");
                        pstmt.setString(1, temp1);
                        pstmt.executeUpdate();break;
                    case 2:
                        pstmt = ct.prepareStatement("delete from score where sno = ? and cno = ?");
                        pstmt.setString(1, temp1);
                        pstmt.setString(2, temp2);
                        pstmt.executeUpdate();break;
                }

            }catch(Exception e){
                e.printStackTrace();
            }finally{
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
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            dispose();
        }
        else if(event.getSource() == cancelBtn){
            dispose();
        }
    }

}
