package Game;

import server.rankUI;
import server.ranking;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class sevGame extends fatherGame {
    final private int max_l=7;
    private int scc=0;
    public sevGame(int mo) {
        super(mo);
    }
    public void actionPerformed(ActionEvent e) {

        int a=0,b=0;
        boolean yz=false;
        String te=inp.getText();
        if(te.length()<4){
            JOptionPane.showMessageDialog(null,"输入长度应为四个字符！","警告",JOptionPane.PLAIN_MESSAGE);
            return;
        }
        String guess=te.substring(0,4);
        inp.setText("");
        deco(guess);
        System.out.println("get "+temas[0]+" "+temas[1]+" "+temas[2]+" "+temas[3]+" ");


        for(int i=0;i<4;i++){
            if(temas[i]==as[i]) b++;
            for(int j=0;j<4;j++){
                if(temas[i]==as[j]) a++;
            }
        }

        b=a-b;
        a=a-b;

        if(a==4){
            yz=true;
            scc+=(8*(7-lj));
        }else{
            scc+=(2*a+b);
        }
        System.out.println(scc);

        JOptionPane.showMessageDialog(null,("本轮结果："+a+"A"+b+"B"),"本轮结果",JOptionPane.PLAIN_MESSAGE);
        jta.append(guess+"  ->  "+a+"A"+b+"B\n");

       // System.out.println(guess);
        lj++;
        if(lj>=max_l||yz){
            String jl="游戏结束，您的得分为：";
            jl+=(""+scc);
            ranking rk=new ranking(mod%2);
            if(rk.insertIt(id,scc)){
                jl+="，恭喜成功打榜！";
            }else{
                jl+="，未能成功打榜，请再接再厉~";
            }

            JOptionPane.showMessageDialog(null,jl,"游戏结算",JOptionPane.PLAIN_MESSAGE);
            rankUI ru=new rankUI();
            dispose();
        }
    }
}
