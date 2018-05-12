import javax.swing.*;
import java.awt.*;

public class AddWin extends JFrame {

    private JLabel nameLabel;
    private JLabel snoLabel;
    private JLabel deptLabel;
    private JLabel sexLabel;
    private JTextField nameText;
    private JTextField snoText;
    private JTextField deptText;
    private JTextField sexText;
    private JButton confirmBtn;
    private JPanel labelPanel;
    private JPanel textPanel;
    private int index;

    public AddWin(int index){
        setVisible(true);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setTitle("添加信息");
        setSize(500, 200);

        int width = getWidth() / 2;
        Font font = new Font("Dialog", 0, 20);
        switch (index){
            case 0:
                nameLabel = new JLabel(" 姓名");
                snoLabel = new JLabel(" 学号");
                sexLabel = new JLabel(" 性别");
                deptLabel = new JLabel(" 院系");break;
            case 1:
                nameLabel = new JLabel(" 课程号");
                snoLabel = new JLabel(" 课程名");
                sexLabel = new JLabel(" 学分");
                deptLabel = new JLabel(" 授课老师");break;
            case 2:
                nameLabel = new JLabel(" 课程号");
                snoLabel = new JLabel("  学号");
                sexLabel = new JLabel(" 分数");
                deptLabel = new JLabel(" 重修标记");
        }

        nameLabel.setFont(font);
        snoLabel.setFont(font);
        sexLabel.setFont(font);
        deptLabel.setFont(font);
        nameLabel.setSize(width, 30);
        snoLabel.setSize(width, 30);
        sexLabel.setSize(width, 30);
        deptLabel.setSize(width, 30);
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(nameLabel);
        labelPanel.add(snoLabel);
        labelPanel.add(sexLabel);
        labelPanel.add(deptLabel);


        nameText = new JTextField(width);
        snoText = new JTextField(width);
        sexText = new JTextField(width);
        deptText = new JTextField(width);
        nameText.setSize(width, 30);
        snoText.setSize(width, 30);
        sexText.setSize(width, 30);
        deptText.setSize(width, 30);
        int height = 2;
        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(4, 1));
        textPanel.setSize(getWidth(), 120);
        textPanel.add(nameText);
        //textPanel.add(Box.createVerticalStrut(height));
        textPanel.add(snoText);
        //textPanel.add(Box.createVerticalStrut(height));
        textPanel.add(sexText);
        //textPanel.add(Box.createVerticalStrut(height));
        textPanel.add(deptText);

        JPanel panelu = new JPanel();
        panelu.setLayout(new BorderLayout());
        //panel.setSize();
        panelu.add(labelPanel, BorderLayout.WEST);
        panelu.add(textPanel, BorderLayout.CENTER);

        confirmBtn = new JButton("确认添加");
        confirmBtn.setFont(font);
        confirmBtn.setSize(getWidth(), 40);
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(new BorderLayout());
        panel.add(panelu, BorderLayout.NORTH);
        panel.add(confirmBtn, BorderLayout.CENTER);
        //panel.setSize(getWidth(), 160);

        update(getGraphics());
    }
}
