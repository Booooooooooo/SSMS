import jdk.nashorn.internal.scripts.JO;
import sun.security.util.Password;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class LoginWin extends JFrame implements ActionListener {
    private Statement stat = null;
    private Connection ct = null;
    private ResultSet rs = null;
    private JTextField userName;
    private JPasswordField password;
    private JLabel user;
    private JLabel pass;
    private JButton confirmBtn;
    private Font font;
    private final String teacher = "admin";
    private final String tPass = "123456";
    private MainWindow mainWindow;

    public LoginWin(){
        super("请登录");
        font = new Font("宋体", Font.PLAIN, 24);

        user = new JLabel("用户名");
        pass = new JLabel("密码");
        userName = new JTextField(15);
        password = new JPasswordField(15);
        confirmBtn = new JButton("登录");
        confirmBtn.addActionListener(this);
        user.setFont(font);
        pass.setFont(font);
        confirmBtn.setFont(font);
        userName.setFont(font);
        password.setFont(font);
        confirmBtn.setSize(150, 50);

        JPanel textPanel = new JPanel();
        textPanel.setBackground(null);
        textPanel.setOpaque(false);
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(null);
        labelPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        textPanel.add(userName);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(password);
        labelPanel.add(user);
        labelPanel.add(Box.createVerticalStrut(5));
        labelPanel.add(pass);

        JPanel panel = new JPanel();
        panel.setBackground(null);
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalStrut(10));
        panel.add(labelPanel);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(textPanel);
        panel.add(Box.createHorizontalStrut(10));
        JLabel title = new JLabel("   学生信息管理系统");
        title.setFont(new Font("宋体", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        JPanel btnPanel = new JPanel();
        btnPanel.add(confirmBtn, BorderLayout.CENTER);
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
            String us = userName.getText();
            String ps = String.valueOf(password.getPassword());
            //System.out.println(ps);
            if(us.equals(teacher)){
                if(ps.equals(tPass)){
                    mainWindow = new MainWindow("0");
                    mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "密码错误");
                }
            }
            else{
                String sql = "select sno from student where sno = '" + us + "'";
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("加载成功");
                    String url = "jdbc:mysql://localhost/studentsystem?useSSL=false&serverTimezone=GMT%2B8";
                    String user = "root";
                    String passwd = "wyb980401";

                    ct = DriverManager.getConnection(url, user, passwd);
                    stat = ct.createStatement();
                    rs = stat.executeQuery(sql);

                    boolean flag = false;
                    while(rs.next()){
                        flag = true;
                    }
                    if(flag){
                        //System.out.println(us.substring(us.length() - 4, us.length() - 1));
                        if(ps.equals(us.substring(us.length() - 3, us.length()))){
                            mainWindow = new MainWindow(us);
                            mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            this.dispose();
                        }else{
                            JOptionPane.showMessageDialog(this, "密码错误");
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, "用户名不存在");
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "用户名不存在");
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

        }

    }

    public static void main(String[] args){
        LoginWin loginWin = new LoginWin();
        loginWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
