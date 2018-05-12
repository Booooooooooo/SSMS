import javax.swing.*;
import java.awt.*;

import static com.sun.glass.ui.Cursor.setVisible;
import static com.sun.javafx.tk.Toolkit.getToolkit;

public class DeleteWin extends JFrame{
    private JLabel label;
    private JLabel messLabel;
    private JTextField text;
    private JButton confirmBtn;
    private JPanel labelPanel;
    private JPanel textPanel;
    private int index;

    public DeleteWin(int index){
        setVisible(true);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        setTitle("删除信息");
        setSize(500, 150);

        int width = getWidth() / 2;
        Font font = new Font("Dialog", 0, 20);
        switch (index){
            case 0:
                label = new JLabel(" 学号");break;
            case 1:
                label = new JLabel(" 课程号");break;
            case 2:
                label = new JLabel("课程号及学号（空格分割）");break;
        }

        label.setFont(font);
        label.setSize(width, 30);
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(label);

        text = new JTextField(width);
        text.setSize(width, 30);
        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1, 1));
        textPanel.setSize(getWidth(), 30);
        textPanel.add(text);

        JPanel panelu = new JPanel();
        panelu.setLayout(new BorderLayout());
        panelu.add(labelPanel, BorderLayout.WEST);
        panelu.add(textPanel, BorderLayout.CENTER);

        confirmBtn = new JButton("确认删除");
        confirmBtn.setFont(font);
        confirmBtn.setSize(getWidth(), 40);

        messLabel = new JLabel("请输入要删除的数据", JLabel.CENTER);
        messLabel.setFont(font);
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(new BorderLayout());
        panel.add(messLabel, BorderLayout.NORTH);
        panel.add(panelu, BorderLayout.CENTER);
        panel.add(confirmBtn, BorderLayout.SOUTH);
    }
}
