/* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------------------------------------------------------------------------------------- */

package othello;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------------*
 *
 * @author Adrianus
 --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
class HPButton extends JButton implements ActionListener{
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    atribut
    Hitam       digunakan untuk mengakses hitam1.png
    Putih       digunakan untuk mengakses putih1.png
    Lantai      digunakan untuk mengakses lantai1.png
    Availible   digunakan untuk mengakses lantai3.png
    
    hp      = 0 jika belum ada warna
    hp      = 1 jika berwarna putih
    hp      = 2 jika berwarna hitam
    
    x y         koordinat objek
    
    status_klik digunakan untuk membuat objek tersebut dapat dilkil atau tidak
    status_klik = 0 membuat objek tidak dapat diklik
    status_klik = 1 membuat objek dapat diklik
    
    bobot   digunakan untuk menentukan nilai perhitungan pada algoritma minimax
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    ImageIcon Hitam,Putih,Lantai,Available;
    int hp,x,y,status_klick,bobot;
    
    public HPButton(){

        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        pada awalnya seluruh objek akan bergambar lantai1.png
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        Hitam = new ImageIcon(this.getClass().getResource("hitam1.png"));
        Putih = new ImageIcon(this.getClass().getResource("putih1.png"));
        Lantai = new ImageIcon(this.getClass().getResource("lantai1.png"));
        Available = new ImageIcon(this.getClass().getResource("lantai3.jpg"));
        setIcon(Lantai);
        this.addActionListener(this);
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    action listener jika ada sebuah objek yang diklik
    akan memeriksa status klik jika status klik nya adalah 1 maka objek tersebut akan diganti warnanya sesuai dengan player
    setiap ada sebuah objek diklik maka akan memanggil fungsi cekplay() dan banyak hal yang terjadi pada fungsi tersebut
    
    input       objek clicked
    output      objek berganti warna atau tidak
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public void actionPerformed(ActionEvent e) {
        if(status_klick == 1){
            OtHello.player++;
            OtHello.player%=2;
            switch(OtHello.player){
                case 0:
                    setIcon(Putih);
                    hp = 1;
                    break;
                case 1:
                    setIcon(Hitam);
                    hp = 2;
                    break;
            }
            OtHello.cekplay(x,y);
        }
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    digunakan untuk mengganti warna sebuah objek tanpa harus memperhatikan nilai status_klik 
    warna diganti sesuai dengan player
    
    input       objek clicked
    output      objek berganti warna
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public void change(){
        switch(OtHello.player){
            case 0:
                setIcon(Putih);
                hp = 1;
                break;
            case 1:
                setIcon(Hitam);
                hp = 2;
                break;
        }
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    digunakan untuk menset koordinat x
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public void setX(int i){
        x = i;
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    digunakan untuk menset koordinat y
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public void setY(int j){
        y = j;
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    digunakan untuk membuat objek yang dapat action ini, warnanya akan diganti menjadi Available
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public void setAvailableClick(){
        setIcon(Available);
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    digunakan untuk membuat objek yang dapat action ini, warnanya akan diganti menjadi Lantai
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public void setUnAvailableClick(){
        setIcon(Lantai);
    }
    
}
