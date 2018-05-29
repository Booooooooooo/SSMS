import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainWindow extends JFrame implements ActionListener{
    private String Sno;//0为老师
    private List tableList;
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton searchButton;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem loginMenu;
    private JMenuItem modifyMenu;
    private Box listBox;
    private Box buttonBox;
    private JPanel panel;
    private Font headFont;
    private int index;
    private StuModel stuModel;
    private ScoModel scoModel;
    private CourModel courModel;

    public JTable table;
    private String[] colStudent = {"学号", "姓名", "性别", "院系" };
    private String[] colScore = {"课程号", "学生学号", "分数", "重修标记"};
    private String[] colCourse = {"课程号", "课程名", "学分", "教师"};
    private String[] col;

    private AddWindow addWin;
    private ModifyWin modifyWin;
    private DelWin delWin;
    private SearchWin searchWin;
    private AbstractTableModel m;
    private Statement stat = null;
    private PreparedStatement ps;
    private Connection ct = null;
    private ResultSet rs = null;
    //private JPanel listPanel;
    //private JPanel displayPanel;
    //private JPanel buttonPanel;
    //String id;

    public MainWindow(String sno)
    {
        super("主窗口");
        setSize(700, 600);
        if(sno.equals("admin"))Sno = "0";
        else Sno = sno;
        menuBar = new JMenuBar();
        //menuBar.setBackground(null);
        //menuBar.setOpaque(false);
        menu = new JMenu("菜单");
        menu.setFont(new Font("宋体", Font.PLAIN, 18));
        menuBar.add(menu);
        loginMenu = new JMenuItem("退出登录");
        loginMenu.setFont(new Font("宋体", Font.PLAIN, 18));
        menu.add(loginMenu);
        loginMenu.addActionListener(this);
        modifyMenu = new JMenuItem("修改密码");
        modifyMenu.setFont(new Font("宋体", Font.PLAIN, 18));
        menu.add(modifyMenu);
        modifyMenu.addActionListener(this);
        this.setJMenuBar(menuBar);

        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        JPanel wholePanel = new JPanel(){
            protected void paintComponent(Graphics g){
                ImageIcon icon = new ImageIcon("bg.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, 1000, 1000, icon.getImageObserver());
            }
        };
        setContentPane(wholePanel);
        wholePanel.setSize(600, 800);
        JLabel title = new JLabel("学生信息管理系统");
        title.setFont(new Font("宋体", Font.BOLD, 26));
        title.setForeground(Color.white);

        headFont = new Font("Dialog", 0, 20);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(null);
        panel.setOpaque(false);
        //setContentPane(panel);

        listBox = Box.createVerticalBox();
        listBox.setSize(150, 700);
        tableList = new List(3);
        tableList.setSize(150, 700);
        tableList.setFont(headFont);
        tableList.add("学生表");
        tableList.add("课程表");
        tableList.add("成绩表");
        listBox.add(tableList);
        col = colStudent;

        String[][] rowData = {{}, {}, {}};
        table = new JTable();
        table.setSize(1000, 700);
        try{
            stuModel = new StuModel();
            table.setModel(stuModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(400, 600);

        tableList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                index = tableList.getSelectedIndex();
                switch(index){
                    case 0:
                        try{
                            stuModel = new StuModel();
                            table.setModel(stuModel);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        try{
                            courModel = new CourModel();
                            table.setModel(courModel);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try{
                            scoModel = new ScoModel(Sno);
                            table.setModel(scoModel);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
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
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(buttonBox);
        buttonPanel.setBackground(null);
        buttonPanel.setOpaque(false);
        //panel.add(buttonBox);

        JPanel panelr = new JPanel();
        panelr.setBackground(null);
        panelr.setOpaque(false);

        panelr.setLayout(new BorderLayout());
        //panelr.add(table.getTableHeader(), BorderLayout.NORTH);
        //panelr.add(table, BorderLayout.CENTER);
        panelr.add(scrollPane, BorderLayout.CENTER);
        panelr.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(listBox);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(panelr);
        panel.add(Box.createHorizontalStrut(20));
        panel.setSize(1100, 500);

        //wholePanel.setLayout(new BoxLayout(wholePanel, BoxLayout.Y_AXIS));
        wholePanel.add(title, BorderLayout.NORTH);
        wholePanel.add(panel, BorderLayout.CENTER);
        //wholePanel.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
        //wholePanel.add(Box.createVerticalStrut(100));

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
        if(obj == addButton){
            if(!Sno.equals("0")){
                JOptionPane.showMessageDialog(this, "对不起，您没有添加权限");
            }else{
                switch (index){
                    case 0:
                        addWin = new AddWindow(this, true, index);
                        try{
                            stuModel = new StuModel();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        table.setModel(stuModel);break;
                    case 1:
                        addWin = new AddWindow(this, true, index);
                        try{
                            courModel = new CourModel();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        table.setModel(courModel);break;
                    case 2:
                        addWin = new AddWindow(this, true, index);
                        try{
                            scoModel = new ScoModel(Sno);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        table.setModel(scoModel);break;
                }
            }
        }else if(obj == deleteButton){
            if(!Sno.equals("0")){
                JOptionPane.showMessageDialog(this, "对不起，您没有删除权限");
            }else{
                int rowNum = table.getSelectedRow();
                if(rowNum == -1){
                    JOptionPane.showMessageDialog(this, "请选中一行");
                    return;
                }
                switch (index){
                    case 0:
                        delWin = new DelWin(this, true, stuModel, rowNum, index);
                        try{
                            stuModel = new StuModel();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        table.setModel(stuModel);
                        break;
                    case 1:
                        delWin = new DelWin(this, true, courModel, rowNum, index);
                        try{
                            courModel = new CourModel();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        table.setModel(courModel);
                        break;
                    case 2:
                        delWin = new DelWin(this, true, scoModel, rowNum, index);
                        try{
                            scoModel = new ScoModel(Sno);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        table.setModel(scoModel);
                        break;
                }
            }
        }else if(obj == modifyButton){
            if(!Sno.equals("0")){
                JOptionPane.showMessageDialog(this, "对不起，您没有修改权限");
            }else{
                int rowNum = table.getSelectedRow();
                if(rowNum == -1){
                    JOptionPane.showMessageDialog(this, "请选择一行");
                    return;
                }
                switch(index){
                    case 0:
                        modifyWin = new ModifyWin(this, true, stuModel, rowNum, index);
                        try{
                            stuModel = new StuModel();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        table.setModel(stuModel);break;
                    case 1:
                        modifyWin = new ModifyWin(this, true, courModel, rowNum, index);
                        try{
                            courModel = new CourModel();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        table.setModel(courModel);break;
                    case 2:
                        modifyWin = new ModifyWin(this, true, scoModel, rowNum,index);
                        try{
                            scoModel = new ScoModel(Sno);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        table.setModel(scoModel);break;
                }
            }
        }else if(obj == searchButton){
            //System.out.println(Sno);
            searchWin = new SearchWin(this, true, index, Sno);
        }else if(obj == loginMenu){
            int re = JOptionPane.showConfirmDialog(this, "确认退出登录？", "退出登录", JOptionPane.YES_NO_OPTION);
            if(re == 0){
                LoginWin win = new LoginWin();
                win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                this.dispose();
            }
        }else if(obj == modifyMenu){
            ModifyPass mod = new ModifyPass(Sno);
            mod.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
    }


    public static void main(String[] args){
        MainWindow win = new MainWindow("0");
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
