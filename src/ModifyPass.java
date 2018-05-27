import jdk.nashorn.internal.scripts.JO;
import sun.security.util.Password;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class ModifyPass extends JFrame implements ActionListener {
    private Statement stat = null;
    private Connection ct = null;
    private ResultSet rs = null;
    //private JTextField userName;
    private JPasswordField password;
    private JPasswordField repass;
    //private JLabel user;
    private JLabel pass;
    private JLabel reenter;
    private JButton confirmBtn;
    private JButton cancelBtn;
    private Font font;
    private String userName;

    public ModifyPass(String username){

        super("修改密码");
        font = new Font("宋体", Font.PLAIN, 24);
        if(username.equals("0")){
            userName = "admin";
        }else{
            userName = username;
        }

        //user = new JLabel("用户名");
        pass = new JLabel("密码");
        reenter = new JLabel("确认密码");
        //userName = new JTextField(15);
        password = new JPasswordField(15);
        repass = new JPasswordField(15);
        confirmBtn = new JButton("确认修改");
        confirmBtn.addActionListener(this);
        cancelBtn = new JButton("取消");
        cancelBtn.addActionListener(this);
        //user.setFont(font);
        pass.setFont(font);
        reenter.setFont(font);
        confirmBtn.setFont(font);
        cancelBtn.setFont(font);
        //userName.setFont(font);
        password.setFont(font);
        repass.setFont(font);
        confirmBtn.setSize(150, 50);
        cancelBtn.setSize(150, 50);

        JPanel textPanel = new JPanel();
        textPanel.setBackground(null);
        textPanel.setOpaque(false);
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(null);
        labelPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        //textPanel.add(userName);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(password);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(repass);
        //labelPanel.add(user);
        labelPanel.add(Box.createVerticalStrut(5));
        labelPanel.add(pass);
        labelPanel.add(Box.createVerticalStrut(5));
        labelPanel.add(reenter);

        JPanel panel = new JPanel();
        panel.setBackground(null);
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalStrut(10));
        panel.add(labelPanel);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(textPanel);
        panel.add(Box.createHorizontalStrut(10));
        JLabel title = new JLabel("学生信息管理系统");
        title.setFont(new Font("宋体", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.add(Box.createHorizontalStrut(80));
        btnPanel.add(confirmBtn);
        btnPanel.add(Box.createHorizontalStrut(15));
        btnPanel.add(cancelBtn);
        btnPanel.setBackground(null);
        btnPanel.setOpaque(false);
        JPanel p = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("titlebg.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, 1000, 1000, icon.getImageObserver());
            }
        };
        setContentPane(p);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(Box.createVerticalStrut(10));
        p.add(title);
        p.add(Box.createVerticalStrut(5));
        p.add(panel);
        p.add(Box.createVerticalStrut(5));
        p.add(btnPanel);

        setSize(450, 230);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == confirmBtn){
            //String us = userName.getText();
            String us = userName;
            String ps = String.valueOf(password.getPassword());
            String reps = String.valueOf(repass.getPassword());
            if(!ps.equals(reps)){
                JOptionPane.showMessageDialog(this, "密码输入不一致，请重试");
                password.setText("");
                repass.setText("");
            }else{
                Connection ct = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                String sql = "select username from user_pass where username = '" + us + "'";
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("加载成功");
                    String url = "jdbc:mysql://localhost/studentsystem?useSSL=false&serverTimezone=GMT%2B8";
                    String user = "root";
                    String passwd = "wyb980401";

                    ct = DriverManager.getConnection(url, user, passwd);
                    pstmt = ct.prepareStatement(sql);
                    rs = pstmt.executeQuery();

                    boolean flag = false;
                    while(rs.next()){
                        flag = true;
                    }
                    if(flag){
                        sql = "update user_pass set password = '" + ps + "' where username = '" + us + "'";
                        pstmt = ct.prepareStatement(sql);
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(this, "修改成功");
                        this.dispose();
                    }else{
                        JOptionPane.showMessageDialog(this, "用户名不存在");
                        //userName.setText("");
                        password.setText("");
                        repass.setText("");
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "修改失败");
                }finally{
                    try{
                        if(rs != null){
                            rs.close();
                            rs = null;
                        }
                        if(stat!= null){
                            stat.close();
                            stat = null;
                        }
                        if(ct != null){
                            ct.close();
                            ct = null;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

        }else if(event.getSource() == cancelBtn){
            this.dispose();
        }

    }

}
