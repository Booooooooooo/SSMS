import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchWin extends JDialog implements ActionListener {
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
    private SearchRes searchRes;
    private Frame owner;


    public SearchWin(Frame owner, boolean modal,int index){
        super(owner, "添加", modal);
        this.index = index;
        this.owner = owner;
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
            String a = text1.getText();
            String b = text2.getText();
            String c = text3.getText();
            String d = text4.getText();
            boolean first = true;
            String strsql = "select  * from ";
            switch (index){
                case 0:
                    strsql += "student where ";
                    if(!a.trim().equals("")){
                        strsql += "sno = '" + a + "'";
                        first = false;
                    }
                    if(!b.trim().equals("")){
                        if(first){
                            strsql += "name = '" + b + "'";
                            first = false;
                        }else{
                            strsql += "and name = '" +b + "'";
                        }
                    }
                    if(!c.trim().equals("")){
                        if(first){
                            strsql += "sex = '" + c + "'";
                            first = false;
                        }else{
                            strsql += "and sex = '" + c + "'";
                        }
                    }
                    if(!d.trim().equals("")){
                        if(first){
                            strsql += "department = '" + d + "'";
                            first = false;
                        }else{
                            strsql += "and department = '" + d + "'";
                        }
                    }
                    break;
                case 1:
                    strsql += "course where ";
                    if(!a.trim().equals("")){
                        strsql += "cno = '" + a + "'";
                        first = false;
                    }
                    if(!b.trim().equals("")){
                        if(first){
                            strsql += "name = '" + b + "'";
                            first = false;
                        }else{
                            strsql += "and name = '" +b + "'";
                        }
                    }
                    if(!c.trim().equals("")){
                        if(first){
                            strsql += "mark = " + c;
                            first = false;
                        }else{
                            strsql += "and mark = " + c;
                        }
                    }
                    if(!d.trim().equals("")){
                        if(first){
                            strsql += " teacher = '" + d + "'";
                            first = false;
                        }else{
                            strsql += "and teacher = '" + d + "'";
                        }
                    }
                    break;
                case 2:
                    strsql += "score where ";
                    if(!a.trim().equals("")){
                        strsql += "score = " + a;
                        first = false;
                    }
                    if(!b.trim().equals("")){
                        if(first){
                            strsql += "sno = '" + b + "'";
                            first = false;
                        }else{
                            strsql += "and sno = '" +b + "'";
                        }
                    }
                    if(!c.trim().equals("")){
                        if(first){
                            strsql += "cno  = '" + c + "'";
                            first = false;
                        }else{
                            strsql += "and cno = '" + c + "'";
                        }
                    }
                    if(!d.trim().equals("")){
                        if(d.equals("否")){
                            d = "false";
                        }else if(d.equals("是")){
                            d = "true";
                        }
                        if(first){
                            strsql += "flag = " + d;
                            first = false;
                        }else{
                            strsql += "and flag = " + d;
                        }
                    }
            }
            searchRes = new SearchRes(this, true, index, strsql);

        }else if(event.getSource() == cancelBtn){
            this.dispose();
        }
    }
}
