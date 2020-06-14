package server;

import java.io.*;

public class ranking implements rankport{


    private static int kk=0;//0->一站到底、1->七步杀
    private String id[][]=new String[2][5];
    private int score[][]=new int[2][5];
    private File file;
    public ranking(int k){
        if(k==0){
            file=new File("d:/TEM","rankA.txt");
        }else {
            file = new File("d:/TEM", "rankB.txt");
        }
    }
    public int getint(String aim){
        int num=0;
        int length=aim.length();
        for (int i=0;i<length;i++){
            num=num*10+(aim.charAt(i)-'0');
        }
        return num;
    }

    public static void setkk(int m){
        kk=m;
    }

    @Override
    public void readTxt() {
        BufferedReader reader=null;
        try {
            reader=new BufferedReader(new FileReader(file));
            String str=null,sc=null;
            for(int j=0;j<2;j++){
                for(int i=0;i<5;i++){
                    str=reader.readLine();
                    id[j][i]=str;
                    sc=reader.readLine();
                    score[j][i]=getint(sc);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /*for(int i=0;i<5;i++){
            System.out.println(id[kk][i]+" "+score[kk][i]);
        }*/
    }

    @Override
    public String getrank() {
        this.readTxt();

        String res="";
        for(int i=0;i<5;i++){
            res+=id[kk][i];

            int len=0;
            char aim[]=id[kk][i].toCharArray();
            for(int j=0;j<id[kk][i].length();j++){
                if (aim[j] >= 0 && aim[j] <= 128)
                    len += 1;
                else
                    len += 2;
            }
            int h=20-len;
            for(int j=0;j<h;j++){
                res+=" ";
            }
            res+=score[kk][i];
            res+="\n";
        }

        return res;
    }

    @Override
    public String getWinnerid() {
        this.readTxt();
        return id[kk][0];
    }

    @Override
    public int getWinnersc() {
        this.readTxt();
        return score[kk][0];
    }

    @Override
    public boolean insertIt(String str, int sc) {
        this.readTxt();
        int k=-1;
        for(int i=0;i<5;i++){
            if(score[kk][i]<sc||id[kk][i].equals("无")){
                k=i;
                break;
            }
        }
        if(k==-1){
            return false;
        }
        for(int i=4;i>k;i--){
            id[kk][i]=id[kk][i-1];
            score[kk][i]=score[kk][i-1];
        }
        id[kk][k]=str;
        score[kk][k]=sc;
        String fin="";
        for(int j=0;j<2;j++){
            for(int i=0;i<5;i++){
                fin+=(id[j][i]+"\n");
                fin+=(""+score[j][i]+"\n");
            }
        }

        try {
            FileWriter fw=new FileWriter(file);
            fw.write(fin);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
