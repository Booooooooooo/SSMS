import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener{
    private List tableList;
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton searchButton;
    private Box listBox;
    private Box buttonBox;
    private JPanel panel;
    private Font headFont;
    private int index;

    private JTable table;
    private String[] colStudent = {"学号", "姓名", "性别", "院系" };
    private String[] colScore = {"课程号", "学生学号", "分数", "重修标记"};
    private String[] colCourse = {"课程号", "课程名", "学分", "教师"};
    private String[] col;
    //private JPanel listPanel;
    //private JPanel displayPanel;
    //private JPanel buttonPanel;

    public MainWindow()
    {
        super("主窗口");
        setSize(1000, 1000);
        setVisible(true);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        headFont = new Font("Dialog", 0, 20);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); //尚未修改 用于测试
        setContentPane(panel);

        listBox = Box.createVerticalBox();
        listBox.setSize(150, 1000);
        tableList = new List(3);
        tableList.setSize(150, 1000);
        tableList.setFont(headFont);
        tableList.add("学生表");
        tableList.add("课程表");
        tableList.add("成绩表");
        listBox.add(tableList);
        col = colStudent;

        String[][] rowData = {{}, {}, {}};
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(rowData, col);
        DefaultTableModel modelS = new DefaultTableModel(rowData, colStudent);
        DefaultTableModel modelSC = new DefaultTableModel(rowData, colScore);
        DefaultTableModel modelC = new DefaultTableModel(rowData, colCourse);
        table.setModel(model);
        tableList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = tableList.getSelectedIndex();
                switch(index){
                    case 0:
                        table.setModel(modelS);break;
                    case 1:
                        table.setModel(modelC);col = colCourse;break;
                    case 2:
                        table.setModel(modelSC);break;
                }

                update(getGraphics());
            }
        });
        table.getTableHeader().setFont(headFont);
        table.setFont(new Font("dialog", 0, 16));
        table.setRowHeight(30);

        buttonBox = Box.createHorizontalBox();
        buttonBox.setSize(600, 100);
        addButton = new JButton("添加");
        addButton.setFont(headFont);
        addButton.setSize(100, 40);
        addButton.addActionListener(this);
        deleteButton = new JButton("删除");
        deleteButton.setFont(headFont);
        deleteButton.setSize(100, 40);
        deleteButton.addActionListener(this);
        modifyButton = new JButton("修改");
        modifyButton.setFont(headFont);
        modifyButton.setSize(100, 40);
        modifyButton.addActionListener(this);
        searchButton = new JButton("查找");
        searchButton.setFont(headFont);
        searchButton.setSize(100, 40);
        searchButton.addActionListener(this);
        buttonBox.add(addButton);
        buttonBox.add(Box.createHorizontalStrut(30));
        buttonBox.add(deleteButton);
        buttonBox.add(Box.createHorizontalStrut(30));
        buttonBox.add(modifyButton);
        buttonBox.add(Box.createHorizontalStrut(30));
        buttonBox.add(searchButton);
        buttonBox.add(Box.createHorizontalStrut(30));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setSize(getWidth() - 150, 50);
        buttonPanel.add(Box.createHorizontalStrut((getWidth() - 670) / 2));
        buttonPanel.add(buttonBox);
        //panel.add(buttonBox);

        JPanel panelr = new JPanel();
        panelr.setLayout(new BorderLayout());
        panelr.add(table.getTableHeader(), BorderLayout.NORTH);
        panelr.add(table, BorderLayout.CENTER);
        panelr.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(listBox);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(panelr);
        panel.add(Box.createHorizontalStrut(10));

    }

    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if(obj == addButton){
            new AddWin(index);
        }else if(obj == deleteButton){
            new DeleteWin(index);
        }else if(obj == modifyButton){
            //new modifyWin();
        }else{
            //new SearchWin();
        }
    }
    public static void main(String[] args){
        MainWindow window = new MainWindow();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
