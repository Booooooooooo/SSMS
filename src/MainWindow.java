import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private List tableList;
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton searchButton;
    private JTable table;
    private Box listBox;
    private Box buttonBox;
    private JPanel panel;
    //private JPanel listPanel;
    //private JPanel displayPanel;
    //private JPanel buttonPanel;

    public MainWindow()
    {
        super("主窗口");
        setSize(1000, 1000);
        setVisible(true);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); //尚未修改 用于测试
        setContentPane(panel);

        listBox = Box.createVerticalBox();
        tableList = new List(3);
        tableList.setSize(150, 1000);
        tableList.add("学生表");
        tableList.add("课程表");
        tableList.add("成绩表");
        listBox.add(tableList);
        panel.add(listBox);

        buttonBox = Box.createHorizontalBox();
        addButton = new JButton("添加");
        addButton.setSize(100, 40);
        deleteButton = new JButton("删除");
        deleteButton.setSize(100, 40);
        modifyButton = new JButton("修改");
        modifyButton.setSize(100, 40);
        searchButton = new JButton("查找");
        searchButton.setSize(100, 40);
        buttonBox.add(addButton);
        buttonBox.add(Box.createHorizontalStrut(15));
        buttonBox.add(deleteButton);
        buttonBox.add(Box.createHorizontalStrut(15));
        buttonBox.add(modifyButton);
        buttonBox.add(Box.createHorizontalStrut(15));
        buttonBox.add(searchButton);
        buttonBox.add(Box.createHorizontalStrut(15));
        panel.add(buttonBox);

        table = new JTable();
        

    }

    public static void main(String[] args){
        MainWindow window = new MainWindow();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
