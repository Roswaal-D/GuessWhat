package UI;
import server.ranking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startUI extends JFrame implements ActionListener {
    private JMenuBar jmb;
    private JMenu jf,ph;//计分、排行
    private JMenuItem j1,j2,ps,pz;
    private JPanel ct;
    private String maxshow="最高纪录:";
    private ImageIcon logo,ui1,ui2;
    private JPanel index,yz,qb;
    private JLabel id1,id2,id3,id4,sc1,sc2,sc3,sc4;
    private Container contain = this.getContentPane();
    private ranking rnkA,rnkB;
    private Font font;
    private JButton jb1,jb2,jb3,jb4;

    public startUI(){
        rnkA=new ranking(0);
        rnkB=new ranking(1);

        font = new Font("方正兰亭黑_GBK", Font.PLAIN, 25);//创建1个字体实例
        //jl1.setForeground(Color.PINK);//设置文字的颜色

        init();

        setJMenuBar(jmb);

        //常规设置
        setIconImage(logo.getImage());
        setTitle("GuessWhat-猜一猜");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setSize(800,560);
        //this.setBounds(500, 200, 800, 560);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension scrnsize = toolkit.getScreenSize();
        setLocation(scrnsize.width / 2 - getWidth() / 2, scrnsize.height / 2 - getHeight() / 2);


        contain.setLayout(new GridLayout(1,1));
        contain.add(yz);
        setVisible(true);
    }

    private void yzinit(){
        //一站到底-panel
        yz=new bgPanel(1);
        id1=new JLabel(rnkA.getWinnerid());
        id1.setFont(font);
        sc1=new JLabel(""+rnkA.getWinnersc());
        sc1.setFont(font);
        id2=new JLabel(rnkB.getWinnerid());
        id2.setFont(font);
        sc2=new JLabel(""+rnkB.getWinnersc());
        sc2.setFont(font);
        yz.setLayout(null);
        id1.setBounds(145,320,110,25);//260
        yz.add(id1);
        sc1.setBounds(300,320,60,25);//260
        yz.add(sc1);
        id2.setBounds(495,320,110,25);
        yz.add(id2);
        sc2.setBounds(650,320,60,25);
        yz.add(sc2);

        jb1=new JButton("<html><body><div style='font-size:17px;font-family:黑体;'>打 榜</div></body></html>");
        jb1.setBounds(175,375,120,40);
        jb1.addActionListener(this);
        yz.add(jb1);

        jb2=new JButton("<html><body><div style='font-size:17px;font-family:黑体;'>打 榜</div></body></html>");
        jb2.setBounds(525,375,120,40);
        jb2.addActionListener(this);
        yz.add(jb2);
    }

    private void qbinit(){
        //七步杀-pabel
        qb=new bgPanel(2);
        qb.setLayout(null);
        jb3=new JButton("<html><body><div style='font-size:17px;font-family:黑体;'>打 榜</div></body></html>");
        jb3.setBounds(175,375,120,40);
        jb3.addActionListener(this);
        jb4=new JButton("<html><body><div style='font-size:17px;font-family:黑体;'>打 榜</div></body></html>");
        jb4.setBounds(525,375,120,40);
        jb4.addActionListener(this);
        id3=new JLabel(rnkA.getWinnerid());
        id3.setFont(font);
        id3.setBounds(145,320,110,25);//260
        qb.add(id3);
        sc3=new JLabel(""+rnkA.getWinnersc());
        sc3.setFont(font);
        sc3.setBounds(300,320,60,25);//260
        qb.add(sc3);
        id4=new JLabel(rnkB.getWinnerid());
        id4.setFont(font);
        id4.setBounds(495,320,110,25);
        qb.add(id4);
        sc4=new JLabel(""+rnkB.getWinnersc());
        sc4.setFont(font);
        sc4.setBounds(650,320,60,25);
        qb.add(sc4);
        qb.add(jb3);
        qb.add(jb4);
    }

    private void init(){
        //菜单栏
        j1=new JMenuItem("一猜到底");
        j1.addActionListener(this);
        j2=new JMenuItem("七步杀");
        j2.addActionListener(this);
        jf=new JMenu("游戏模式");
        jf.add(j1);
        jf.add(j2);

        ps=new JMenuItem("数字排行");
        ps.addActionListener(this);
        pz=new JMenuItem("字母排行");
        pz.addActionListener(this);
        ph=new JMenu("分数排行");
        ph.add(ps);
        ph.add(pz);

        jmb=new JMenuBar();
        jmb.add(jf);
        jmb.add(ph);

        //图片init
        logo=new ImageIcon("images/logo.png");
        //ui1=new ImageIcon("images/ui1.jpg");



        //一站到底-panel
        yzinit();

        //七步杀-pabel
        //qbinit();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==j1){
            contain.removeAll();
            ranking.setkk(0);
            yzinit();
            contain.add(yz);
            contain.validate();
            contain.repaint();

        }
        if(e.getSource()==j2) {
            contain.removeAll();
            ranking.setkk(1);
            qbinit();
            contain.add(qb);
            contain.validate();
            contain.repaint();
        }
        if(e.getSource()==jb1){
            System.out.println("一站到底1234");
        }
        if(e.getSource()==jb2){
            System.out.println("一站到底abcd");
        }
        if(e.getSource()==jb3){
            System.out.println("七步杀1234");
        }
        if(e.getSource()==jb4){
            System.out.println("七步杀abcd");
        }

    }
}
