import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddWin extends JFrame implements ActionListener {

    private JLabel Label1;
    private JLabel Label2;
    private JLabel Label3;
    private JLabel Label4;
    private JTextField Text1;
    private JTextField Text2;
    private JTextField Text3;
    private JTextField Text4;
    private JButton confirmBtn;
    private JPanel labelPanel;
    private JPanel textPanel;
    private int index;

    public AddWin(int index){
        //super(owner, "添加信息", modal);
        super("添加信息");
        setVisible(false);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setSize(500, 200);

        int width = getWidth() / 2;
        Font font = new Font("Dialog", 0, 20);
        switch (index){
            case 0:
                Label2 = new JLabel(" 姓名");
                Label1 = new JLabel(" 学号");
                Label3 = new JLabel(" 性别");
                Label4 = new JLabel(" 院系");break;
            case 1:
                Label1 = new JLabel(" 课程号");
                Label2 = new JLabel(" 课程名");
                Label3 = new JLabel(" 学分");
                Label4 = new JLabel(" 授课老师");break;
            case 2:
                Label3 = new JLabel(" 课程号");
                Label2 = new JLabel("  学号");
                Label1 = new JLabel(" 分数");
                Label4 = new JLabel(" 重修标记");
        }
        Label1.setFont(font);
        Label2.setFont(font);
        Label3.setFont(font);
        Label4.setFont(font);
        Label1.setSize(width, 30);
        Label2.setSize(width, 30);
        Label3.setSize(width, 30);
        Label4.setSize(width, 30);
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(Label1);
        labelPanel.add(Label2);
        labelPanel.add(Label3);
        labelPanel.add(Label4);


        Text1 = new JTextField(width);
        Text2 = new JTextField(width);
        Text3 = new JTextField(width);
        Text4 = new JTextField(width);
        Text1.setSize(width, 30);
        Text2.setSize(width, 30);
        Text3.setSize(width, 30);
        Text4.setSize(width, 30);
        int height = 2;
        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(4, 1));
        textPanel.setSize(getWidth(), 120);
        textPanel.add(Text1);
        //textPanel.add(Box.createVerticalStrut(height));
        textPanel.add(Text2);
        //textPanel.add(Box.createVerticalStrut(height));
        textPanel.add(Text3);
        //textPanel.add(Box.createVerticalStrut(height));
        textPanel.add(Text4);

        JPanel panelu = new JPanel();
        panelu.setLayout(new BorderLayout());
        //panel.setSize();
        panelu.add(labelPanel, BorderLayout.WEST);
        panelu.add(textPanel, BorderLayout.CENTER);

        confirmBtn = new JButton("确认添加");
        confirmBtn.setFont(font);
        confirmBtn.setSize(getWidth(), 40);
        confirmBtn.addActionListener(this);
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(new BorderLayout());
        panel.add(panelu, BorderLayout.NORTH);
        panel.add(confirmBtn, BorderLayout.CENTER);
        //panel.setSize(getWidth(), 160);

        update(getGraphics());
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == confirmBtn){
            Connection ct = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try{
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("加载成功");

                String url = "jdbc:mysql://localhost/studentsystem";
                String user = "root";
                String passwd = "wyb980401";
                ct = DriverManager.getConnection(url, user, passwd);

                String strsql = "insert into student values(?, ?, ?, ?)";
                pstmt = ct.prepareStatement(strsql);

                pstmt.setString(1, Text1.getText());
                pstmt.setString(2, Text2.getText());
                pstmt.setString(3, Text3.getText());
                pstmt.setString(4, Text4.getText());

                System.out.println("添加成功");
                pstmt.executeUpdate();
                this.dispose();
            }catch (Exception arg1){
                arg1.printStackTrace();
            }finally{
                try{
                    if(rs!=null){
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
    }
}
