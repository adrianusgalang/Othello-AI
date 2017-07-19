/* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------------------------------------------------------------------------------------- */

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
yang belom
comment - selesai
delay - 
habis turn - selesai
pass - selesai
finish - selesai
start - selesai
game over - selesai
score - selesai
--------------------------------------------------------------------------------------------------------------------------------------------------------------- */
package othello;


import java.awt.GridLayout;
import java.awt.Window;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/* ---------------------------------------------------------------------------------------------------------------------------------------------------------------*
 *
 * @author Adrianus
 --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class OtHello extends JFrame{

    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------*
     * @param args the command line arguments
     --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    player      menunjukan siapa yang berjalan terlabih dahulu, player hanya mamiliki 2 nilai yaitu 1 dan 0
    size        digunakan untuk menentukan berapa ukuran papan permainan
    buttons     merupakan objek yang mewakili setiap objek yang berada di papan permainan, jadi banyaknya sebanyak size * size
    jumlahPutih dan jumlah Hitam digunakan untuk menghitung berapa banyak putih dan hitam yang berada pada papan permainan
    p           merupakan jpanel yang akan menampung papan permainan
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public static byte player = 0;
    static int size = 16;
    static HPButton buttons[][] = new HPButton[size][size];
    static int jumlahPutih, jumlahHitam;
    static JPanel p = new JPanel();


    public static void main(String[] args) {
        // TODO code application logic here
        new OtHello(16);
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    OtHello merupakan fungsi untuk menginisiasi permainan
        pembuatan papan permainan
        membagi papan permainan menjadi beberapa bagian
        menghubungkan variable dengan button yang telah di buat
        membuat tampilan awal
        memberikan bobot terhadap setiap objek
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public OtHello(int s){
        super("OtHello");
        System.out.println(">>>>> "+size);
        size = s;
        System.out.println(">>>>> "+size);
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        pembuatan papan permainan dengan ukuran tertentu
        disini ukurannya size*60 karena akan dibuat korak sebanyak size dengan masing masing size 60 pixel
        60 pixel karena image yang akan digunakan untuk setiap objek yang ada telah di buat dengan ukuran 60*60 pixel
        setelah papan permainan dibuat, selanjutnya papan permainan tersebut dibagi menjadi beberapa grid sesuai dengan nilai size.
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        setSize(size*60,size*60);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(size,size));
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        pembuatan objek button sebanyak grid yang telah dibuat, yang nantinya setiap objek button akan digunakan untuk mengendalikan grid yang ada pada papan permainan
        inisiasi beberapa atribut objek
        pada awalnya setiap objek memiliki bobot 10
        setiap objek ditambahkan ke dalam panel p
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                buttons[i][j] = new HPButton();
                buttons[i][j].setX(i);
                buttons[i][j].setY(j);
                buttons[i][j].bobot = 10;
                p.add(buttons[i][j]);
            }
        }
        
        /* --------------------------------------------------------------------------------------------------------------------------------------------------------------- 
        memberikan tampilan awal permainan berupa terdapat 4 buah bidak berada pada tengah tengah papan permainan dan warnanya selang seling
        objek objek tersebut harus diklik secara otomatis sehingga memerlikan action doClick()
        untuk sebuah objek dapat diklik, status klik nya harus 1, jadi sebelum mengklik harus menghubah status klik nya terlebih dahulu
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        buttons[size/2][size/2].status_klick = 1;
        buttons[size/2][size/2].doClick();

        buttons[size/2][size/2-1].status_klick = 1;
        buttons[size/2][size/2-1].doClick();
        
        buttons[size/2-1][size/2-1].status_klick = 1;
        buttons[size/2-1][size/2-1].doClick();
        
        buttons[size/2-1][size/2].status_klick = 1;
        buttons[size/2-1][size/2].doClick();
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        pada awalnya bobot setiap masing masing objek sudah di set 10
        tapi untuk kepentingan algoritma nilai bobot untuk objek harus dibuat sebagai berikut
        2000 -300 10 10 10 10 10 10 -300 2000  
        -300 -300 10 10 10 10 10 10 -300 -300  
        10   10   10 10 10 10 10 10 10   10  
        10   10   10 10 10 10 10 10 10   10  
        10   10   10 10 10 10 10 10 10   10  
        10   10   10 10 10 10 10 10 10   10  
        10   10   10 10 10 10 10 10 10   10  
        10   10   10 10 10 10 10 10 10   10  
        -300 -300 10 10 10 10 10 10 -300 -300  
        2000 -300 10 10 10 10 10 10 -300 2000
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        buttons[0][0].bobot = 2000;
        buttons[0][1].bobot = -300;
        buttons[1][0].bobot = -300;
        buttons[1][1].bobot = -300;
        
        buttons[size-1][0].bobot = 2000;
        buttons[size-2][0].bobot = -300;
        buttons[size-1][1].bobot = -300;
        buttons[size-2][1].bobot = -300;

        buttons[0][size-1].bobot = 2000;
        buttons[0][size-2].bobot = -300;
        buttons[1][size-1].bobot = -300;
        buttons[1][size-2].bobot = -300;

        buttons[size-1][size-1].bobot = 2000;
        buttons[size-1][size-2].bobot = -300;
        buttons[size-2][size-1].bobot = -300;
        buttons[size-2][size-2].bobot = -300;

        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        menambahkan panel p ke winwow
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        add(p);
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        menampilkan window
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        setVisible(true);
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    setelah user melakukan langkah dengan mengklik sebuah objek maka akan mamanggil function ini
    input   x dan y array objek yang di klik
    output  penyesuaian terhadap langkah user
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public static void cekplay(int x, int y){
        ubahWarna(x,y,buttons);
        setJumlahPH();
        if(setAvailableClick()==0 && jumlahHitam + jumlahPutih >= 4){
            pass();
        }
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        setelah user mengklik, maka AI harus mengklik secara otomatis
        karena user merupakan player 0, maka AI adalah player 1
        dan AI mengklik otomatis setelah inisiasi awal selesai yaitu setelah jumlah hitam dan putih lebih dari 3
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        if(player == 1 && jumlahHitam + jumlahPutih >= 4){
            AIClick();
        }
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    saat sebuah objek A diklik, maka objek tersebut akan memiliki warna tertentu, Hitam atau Putih tergantung siapa yang mengklik
    saat sebuah objek A memiliki warna, maka objek yang lain harus menyesuaikan dengan objek A
    maka dilakukanlah pemeriksaan terhadap atas bawah kanan kiri kananatas kiriatas kananbawah kiribawah, apakah warna objek yang lain harus diganti atau tidak
    input       x dan y dan objek yang diklik
    output      penyesuaian warna objek lain terhadap warna objek yang diklik
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public static void ubahWarna(int x, int y, HPButton TempButton[][]){
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        pemeriksaan terhadap bawah objek yang diklik
        jika maish dalam cakupan array dan status cekSBawah adalah true yang menandakan ada objek yang warnanya harus di rubah
        maka akan dihitung berapa banyak objek dibawah objek yang diklik yang harus di rubah warna
        setelah didapatkan jumlahnya, objek objek di bawahnya akan diganti warna dengan memanggil action change()
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        //bawah//////////////////////////////
        if(x-1 >= 0 && cekSBawah(x-1,y,TempButton)){
            int banyakGanti = cekBawah(x-1,y,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x-i][y].change();
            }
        }
        
        //atas//////////////////////////////
        if(x+1 < size && cekSAtas(x+1,y,TempButton)){
            int banyakGanti = cekAtas(x+1,y,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x+i][y].change();
            }
        }
        
        //kanan//////////////////////////////
        if(y-1 >= 0 && cekSKanan(x,y-1,TempButton)){
            int banyakGanti = cekKanan(x,y-1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x][y-i].change();
            }
        }
        
        //kiri//////////////////////////////
        if(y+1 < size && cekSKiri(x,y+1,TempButton)){
            int banyakGanti = cekKiri(x,y+1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x][y+i].change();
            }
        }
        
        //bawahkanan//////////////////////////////
        if(x-1 >= 0 && y-1 >= 0 && cekSBawahKanan(x-1,y-1,TempButton)){
            int banyakGanti = cekBawahKanan(x-1,y-1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x-i][y-i].change();
            }
        }
        
        //bawahkiri//////////////////////////////
        if(x-1 >= 0 && y+1 < size && cekSBawahKiri(x-1,y+1,TempButton)){
            int banyakGanti = cekBawahKiri(x-1,y+1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x-i][y+i].change();
            }
        }
        
        //ataskanan//////////////////////////////
        if(x+1 < size && y-1 >= 0 && cekSAtasKanan(x+1,y-1,TempButton)){
            int banyakGanti = cekAtasKanan(x+1,y-1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x+i][y-i].change();
            }
        }
        
        //ataskiri//////////////////////////////
        if(x+1 < size && y+1 < size && cekSAtasKiri(x+1,y+1,TempButton)){
            int banyakGanti = cekAtasKiri(x+1,y+1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x+i][y+i].change();
            }
        }
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    merupakan sebuah fungsi rekursif untuk mengecek warna objek di bawah sebuah objek
    
    jika hp = 0 menandakan objek tersebut belum memiliki warna maka tidak ada objek yang warnanya harus diganti
    
    hp = 1 = putih
    hp = 2 = hitam
    player = 0 = putih
    player = 1 = hitam
    jadi jika hp = player + 1 menandakan warna objek tersebut sama dengan warna objek yang diklik maka ada objek yang warnanya harus di ganti
    kondisi tersebut mencakup misal 11, 101, 1001 dst, berarti 0 harus di ganti dengan 1. 11 masuk ke dalam kondisi true meskipun tidak ada yang harus di ganti.
    
    jika objek yang diperiksa melebihi batas array maka tidak ada objek yang harus di ganti
    
    jika objek yang diperiksa memiliki warna yang berbeda dengan warna yang diklik maka diperiksa objek di bawahnya dengan memanggil fungsi yang sama
    
    input   x dan y dan objek yang akan diperiksa
    output  status apakah ada objek yang harus di ganti warnanya atau tidak
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    //bawah////////////////////////////////////////////
    public static boolean cekSBawah(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == 0){
            return false;
        }
        else if(TempButton[x][y].hp == (player + 1)){
            return true;
        }else{
            if(x-1 < 0){
                return false;
            }else{
                return false || cekSBawah(x-1,y, TempButton);
            }
        }
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    saat pengecekan status menghasilkan hasil true
    maka akan memanggil fungsi ini yang digunakan untuk menghitung berapa banyak objek dibawah objek yang diklik yang harus di ganti warnanya
    seperti pengecekan status, fungsi direkursif sampai didapatkan warna objek yang sama dengan objek yang diklik
    
    input   x dan y dan objek yang akan diperiksa
    output  jumlah objek yang warnanya harus diganti
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public static int cekBawah(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == (player + 1)){
            return 0;
        }else{
            return 1 + cekBawah(x-1,y, TempButton);
        }
    }
    
    //atas////////////////////////////////////////////
    public static boolean cekSAtas(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == 0){
            return false;
        }
        else if(TempButton[x][y].hp == (player + 1)){
            return true;
        }else{
            if(x+1 >= size){
                return false;
            }else{
                return false || cekSAtas(x+1,y, TempButton);
            }
        }
    }
    
    public static int cekAtas(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == (player + 1)){
            return 0;
        }else{
            return 1 + cekAtas(x+1,y, TempButton);
        }
    }
    
    //kanan////////////////////////////////////////////
    public static boolean cekSKanan(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == 0){
            return false;
        }
        else if(TempButton[x][y].hp == (player + 1)){
            return true;
        }else{
            if(y-1 < 0){
                return false;
            }else{
                return false || cekSKanan(x,y-1, TempButton);
            }
        }
    }
    
    public static int cekKanan(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == (player + 1)){
            return 0;
        }else{
            return 1 + cekKanan(x,y-1, TempButton);
        }
    }
    
    //kiri////////////////////////////////////////////
    public static boolean cekSKiri(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == 0){
            return false;
        }
        else if(TempButton[x][y].hp == (player + 1)){
            return true;
        }else{
            if(y+1 >= size){
                return false;
            }else{
                return false || cekSKiri(x,y+1, TempButton);
            }
        }
    }
    
    public static int cekKiri(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == (player + 1)){
            return 0;
        }else{
            return 1 + cekKiri(x,y+1, TempButton);
        }
    }
    
    //bawahkanan////////////////////////////////////////////
    public static boolean cekSBawahKanan(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == 0){
            return false;
        }
        else if(TempButton[x][y].hp == (player + 1)){
            return true;
        }else{
            if(x-1 < 0 || y-1 < 0){
                return false;
            }else{
                return false || cekSBawahKanan(x-1,y-1, TempButton);
            }
        }
    }
    
    public static int cekBawahKanan(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == (player + 1)){
            return 0;
        }else{
            return 1 + cekBawahKanan(x-1,y-1, TempButton);
        }
    }
    
    //bawahkiri////////////////////////////////////////////
    public static boolean cekSBawahKiri(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == 0){
            return false;
        }
        else if(TempButton[x][y].hp == (player + 1)){
            return true;
        }else{
            if(x-1 < 0 || y+1 >= size){
                return false;
            }else{
                return false || cekSBawahKiri(x-1,y+1, TempButton);
            }
        }
    }
    
    public static int cekBawahKiri(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == (player + 1)){
            return 0;
        }else{
            return 1 + cekBawahKiri(x-1,y+1, TempButton);
        }
    }
    
    //ataskanan////////////////////////////////////////////
    public static boolean cekSAtasKanan(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == 0){
            return false;
        }
        else if(TempButton[x][y].hp == (player + 1)){
            return true;
        }else{
            if(x+1 >= size || y-1 < 0){
                return false;
            }else{
                return false || cekSAtasKanan(x+1,y-1, TempButton);
            }
        }
    }
    
    public static int cekAtasKanan(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == (player + 1)){
            return 0;
        }else{
            return 1 + cekAtasKanan(x+1,y-1, TempButton);
        }
    }
    
    //ataskiri////////////////////////////////////////////
    public static boolean cekSAtasKiri(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == 0){
            return false;
        }
        else if(TempButton[x][y].hp == (player + 1)){
            return true;
        }else{
            if(x+1 >= size || y+1 >= size){
                return false;
            }else{
                return false || cekSAtasKiri(x+1,y+1, TempButton);
            }
        }
    }
    
    public static int cekAtasKiri(int x, int y, HPButton TempButton[][]){
        if(TempButton[x][y].hp == (player + 1)){
            return 0;
        }else{
            return 1 + cekAtasKiri(x+1,y+1, TempButton);
        }
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    digunakan untuk mencari objek objek yang dapat diklik
    karena dalam permainan otHello, pemain hanya dapat mengklik objek yang akan merubah warna objek lain
    sehingga status klik akan berubah dan player hanya akan dapat mengklik button yang sesuai saja
    
    input   objek button dengan kondisi status klik milik player sebelumnya
    output  objek button dengan kondisi status klik milik player yang akan mengklik
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public static int setAvailableClick(){
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        karena digunakan untuk mencari milik player setelahnya maka player dirubah ke player setelahnya terlebih dahulu
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        player++;
        player%=2;
        int banyakAvailable = 0;
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                
                /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                set seluruh status klik = 0, membuat seluruh objek tidak dapat diklik
                --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
                if(buttons[x][y].hp == 0){
                    buttons[x][y].setUnAvailableClick();
                    buttons[x][y].status_klick = 0;
                }else{
                    buttons[x][y].status_klick = 0;
                }
                
                /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                diperiksa apakah jika objek tersebut diklik dia akan membuat perubahaan pada objek lain atau tidak,
                pemeriksaanya mengunakan kondisi kondisi mirip dengan pada fungsi ubah warna yaitu
                diperiksa apakah keluar array atau tidak
                diperiksa apakah ada objek yang harus di rubah atau tidak
                diperiksa apakah objek yang dirubah lebih dari 0
                jika ada yang memenuhi maka objek tersebut mendapatkan action setAvailibleClick() digunakan untuk merubah warna
                dan status klik objek tersebut dirubah menjadi 1 yang membuat nantinya objek tersebut dapat diklik
                --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
                if((
                        (x-1 >= 0 && cekSBawah(x-1,y,buttons) && cekBawah(x-1,y,buttons) > 0) || 
                        (x+1 < size && cekSAtas(x+1,y,buttons) && cekAtas(x+1,y,buttons)> 0) || 
                        (y-1 >= 0 && cekSKanan(x,y-1,buttons) && cekKanan(x,y-1,buttons)> 0) || 
                        (y+1 < size && cekSKiri(x,y+1,buttons) && cekKiri(x,y+1,buttons)> 0) || 
                        (x-1 >= 0 && y-1 >= 0 && cekSBawahKanan(x-1,y-1,buttons) && cekBawahKanan(x-1,y-1,buttons)> 0) || 
                        (x-1 >= 0 && y+1 < size && cekSBawahKiri(x-1,y+1,buttons) && cekBawahKiri(x-1,y+1,buttons)> 0) || 
                        (x+1 < size && y-1 >= 0 && cekSAtasKanan(x+1,y-1,buttons) && cekAtasKanan(x+1,y-1,buttons)> 0) || 
                        (x+1 < size && y+1 < size && cekSAtasKiri(x+1,y+1,buttons) && cekAtasKiri(x+1,y+1,buttons)> 0)
                        ) && 
                        buttons[x][y].hp == 0
                ){
                    banyakAvailable++;
                    buttons[x][y].setAvailableClick();
                    buttons[x][y].status_klick = 1;
                }
                //System.out.print(buttons[x][y].status_klick+" ");
            }
            //System.out.println(" ");
        }
            //System.out.println(" ");
            //System.out.println(" ");
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        karena pada awal fungsi player dirubah ke player setelahnya, maka pada akhir fungsi state player harus dikembalikan.
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        player++;
        player%=2;
        System.out.println(player + " " +banyakAvailable);
        return banyakAvailable;
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    digunakan untuk mengeraklan langkah AI
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public static void AIClick(){
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        karena giliran AI maka player dirubah ke AI
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        player++;
        player%=2;
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        koordinat digunkaan untuk menampung koordinat hasil dari algoritma minimax.
        setelah koordinat yang harus diklik didapatkan, maka objek dengan koordinat tersebut diklik dan warnanya diganti
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        int[] koordinat = new int[2];
        koordinat = min(buttons);
        buttons[koordinat[0]][koordinat[1]].doClick();
        buttons[koordinat[0]][koordinat[1]].change();
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        saat ada sebuah objek yang diklik, seperti saat user mengklik harus di periksa terhadap seluruh objek, objek mana saja yang warnanya harus diganti
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        ubahWarna(koordinat[0],koordinat[1],buttons);
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        hitung jumlah hitam dan putih yang ada di papan permainan
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        setJumlahPH();
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        set objek yang mungkin di klik oleh player selanjutnya
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        if(setAvailableClick()==0){
            pass();
        }
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    digunakan untuk mendapatkan nilai min dari hasil perhitungan
    input   seluruh objek
    output  koordinat objek yang memiliki nilai keuntungan lawan paling kecil
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public static int[] min(HPButton TempButton[][]){
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        tempkoordinat   digunakan untuk menampung koordinat hasil perhitungan
        temp            di beri nilai awal 100000 karena nilai haril perhitungan tidak akan lebih besar dari 100000 sehingga pada perhitungan pertama nilai temp akan berganti
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        int[] tempkoordinat = new int[2];
        int temp = 100000;

        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        periksa ke semua objek, mana saja yang dapat diklik
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                if(TempButton[x][y].hp == 0){
                    TempButton[x][y].setUnAvailableClick();
                    TempButton[x][y].status_klick = 0;
                }
                
                if((
                        (x-1 >= 0 && cekSBawah(x-1,y,TempButton) && cekBawah(x-1,y,TempButton) > 0) || 
                        (x+1 < size && cekSAtas(x+1,y,TempButton) && cekAtas(x+1,y,TempButton)> 0) || 
                        (y-1 >= 0 && cekSKanan(x,y-1,TempButton) && cekKanan(x,y-1,TempButton)> 0) || 
                        (y+1 < size && cekSKiri(x,y+1,TempButton) && cekKiri(x,y+1,TempButton)> 0) || 
                        (x-1 >= 0 && y-1 >= 0 && cekSBawahKanan(x-1,y-1,TempButton) && cekBawahKanan(x-1,y-1,TempButton)> 0) || 
                        (x-1 >= 0 && y+1 < size && cekSBawahKiri(x-1,y+1,TempButton) && cekBawahKiri(x-1,y+1,TempButton)> 0) || 
                        (x+1 < size && y-1 >= 0 && cekSAtasKanan(x+1,y-1,TempButton) && cekAtasKanan(x+1,y-1,TempButton)> 0) || 
                        (x+1 < size && y+1 < size && cekSAtasKiri(x+1,y+1,TempButton) && cekAtasKiri(x+1,y+1,TempButton)> 0)
                        ) && 
                        TempButton[x][y].hp == 0
                ){
                    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                    jika ada objek yang dapat diklik, maka akan masuk ke dalam kondisi ini
                    pada kondisi ini memanggil fungsi max yang digunakan untuk mensimulasikan jika objek tersebut di klik, 
                    maka musuh akan dapat mengklik objek yang mana saja selanjutnya, 
                    dan nilainya paling besar yang didapatkan musuh berapa lalu disimpan kedalam variable bobot.
                    
                    setelah itu dibandingkan nilai temp dengan bobot yang dikurangi dengan nilai bobot objek yang di klik.
                    jika nilainya lebih kecil maka temp akan diganti, dan tempkoordinat akan diganti juga.
                    
                    semakin kecil nilai temp, berarti semakin kecil keuntungan musuh.
                    
                    temp dikirim ke max untuk menerapkan alpha beta pruning
                    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
                    //System.out.println("min");
                    //System.out.println(x + " " + y );
                    int bobot = max(TempButton,x,y,temp);
                    if(temp > bobot - TempButton[x][y].bobot){
                        temp = bobot - TempButton[x][y].bobot;
                        tempkoordinat[0] = x;
                        tempkoordinat[1] = y;
                        //System.out.println("min >> "+x+" "+y);
                    }
                    //x = size;
                    //y = size;
                    //buttons[x][y].status_klick = 1;
                }
            }
        }
        return tempkoordinat;
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    digunakan untuk mendapatkan nilai max dari hasil perhitungan
    input   seluruh objek dan koordinat simulasi objek yang diklik
    output  nilai maksimal hasil perhitungan
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public static int max(HPButton TempButton[][],int i, int j, int ab){
        //System.out.println("max");

        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        untuk menerapkan simulasi objek koordinat i j diklik maka hp objek tersebut dirubah
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        switch(player){
            case 0:
                 TempButton[i][j].hp = 1;
                break;
            case 1:
                 TempButton[i][j].hp = 2;
                break;
        }
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        saat ada sebuah objek yang memiliki warna baru,
        maka harus objek lain harus di cek mana saja objek yang warnanya harus diganti
        simpanRubah     digunakan untuk menampung koordinat koordinat objek yang warnanya dirubah karena fungsi ubahWarna2,
                        sehingga nanti pada akhir fungsi objek yang warnya dirubah karena simulasi dapat dikembalikan ke warna aslinya
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        int simpanRubah[][] = new int[300][3];
        simpanRubah = ubahWarna2(i, j, TempButton);
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        a       digunakan untuk menghitung berapa banyak objek yang warnanya dirubah, dan sebagai faktor yang menentukan bobot.
                semakin banyak nilai a maka semakin musuh dirugikan
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        int a = 0;
        while(a == simpanRubah[a][2]){
            a++;
        }
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        karena mensimulasikan pergerakan musuh, maka status player harus di rubah ke musuh
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        player++;
        player%=2;
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        temp digunakan untuk menampung hasil perhitungan, diberi nilai awal -100000 karena dicari nilai maksimal jadi pada kondisi pertama akan berganti nilainya
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        int temp = -100000;
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        di cek ke seluruh objek, objek mana saja yang dapat di klik
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                if((
                        (x-1 >= 0 && cekSBawah(x-1,y,TempButton) && cekBawah(x-1,y,TempButton) > 0) || 
                        (x+1 < size && cekSAtas(x+1,y,TempButton) && cekAtas(x+1,y,TempButton)> 0) || 
                        (y-1 >= 0 && cekSKanan(x,y-1,TempButton) && cekKanan(x,y-1,TempButton)> 0) || 
                        (y+1 < size && cekSKiri(x,y+1,TempButton) && cekKiri(x,y+1,TempButton)> 0) || 
                        (x-1 >= 0 && y-1 >= 0 && cekSBawahKanan(x-1,y-1,TempButton) && cekBawahKanan(x-1,y-1,TempButton)> 0) || 
                        (x-1 >= 0 && y+1 < size && cekSBawahKiri(x-1,y+1,TempButton) && cekBawahKiri(x-1,y+1,TempButton)> 0) || 
                        (x+1 < size && y-1 >= 0 && cekSAtasKanan(x+1,y-1,TempButton) && cekAtasKanan(x+1,y-1,TempButton)> 0) || 
                        (x+1 < size && y+1 < size && cekSAtasKiri(x+1,y+1,TempButton) && cekAtasKiri(x+1,y+1,TempButton)> 0)
                        ) && 
                        TempButton[x][y].hp == 0
                ){
                    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                    jika ada objek yang dapat diklik, maka akan masuk ke dalam kondisi ini
                    pada kondisi ini dibandingkan nilai temp dengan hasil perhitungan sehingga didapatkan nilai maksimal hasil perhitungan.
                    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
                    //System.out.println(x + " " + y + " -" + TempButton[x][y].bobot);
                    if(temp < TempButton[x][y].bobot - 10*a){
                        temp = TempButton[x][y].bobot - 10*a;
                        //System.out.println(x + " " + y + " max>> " + TempButton[x][y].bobot);
                        /*
                        ab digunakan untuk algoritma alpha beta pruning
                        
                        */
                        if(temp - TempButton[i][j].bobot > ab){
                            x = size;
                            y = size;
                        }
                    }
                    //buttons[x][y].status_klick = 1;
                }
            }
        }
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        karena objek yang diklik yang terjadi pada fungsi ini hanya simulasi,
        maka nilai nilai objek harus dikembalikan kedalam bentuk aslinya.
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        TempButton[i][j].hp = 0;
        a = 0;
        while(a == simpanRubah[a][2]){
            //System.out.println(simpanRubah[a][0] + " "+ simpanRubah[a][1]);
            TempButton[simpanRubah[a][0]][simpanRubah[a][1]].hp = player+1;
            simpanRubah[a][0] = 0;
            simpanRubah[a][1] = 0;
            simpanRubah[a][2] = 0;
            a++;
        }
        
//        System.out.println("||||||||||||||");
//        for(int x = 0; x < size; x++){
//            for(int y = 0; y < size; y++){
//                System.out.print(TempButton[x][y].hp+" ");
//            }
//            System.out.println(" ");
//        }
        
        /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
        karena pada awal fungsi status player dirubah ke musuh, maka sekarang status player dikembalikan
        --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        player++;
        player%=2;
        return temp;
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    fungsi ini tidak jauh berbeda dengan fungsi ubah warna, hanya saja pada fungsi ini ada sebuah array
    yang digunakan untuk menampung objek mana saja yang warnanya dirubah
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public static int[][] ubahWarna2(int x, int y, HPButton TempButton[][]){
        
        int simpanYangDirubah[][] = new int[300][3];
        int a = 0;
        //bawah//////////////////////////////
        if(x-1 >= 0 && cekSBawah(x-1,y,TempButton)){
            int banyakGanti = cekBawah(x-1,y,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x-i][y].hp = player+1;
                simpanYangDirubah[a][0] = x-i;
                simpanYangDirubah[a][1] = y;
                simpanYangDirubah[a][2] = a;
                a++;
            }
        }
        
        //atas//////////////////////////////
        if(x+1 < size && cekSAtas(x+1,y,TempButton)){
           // System.out.println("asdasdasd");
            int banyakGanti = cekAtas(x+1,y,TempButton);
            //System.out.println("asdasdasd"+banyakGanti);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x+i][y].hp = player+1;
                simpanYangDirubah[a][0] = x+i;
                simpanYangDirubah[a][1] = y;
                simpanYangDirubah[a][2] = a;
                a++;
            }
        }
        
        //kanan//////////////////////////////
        if(y-1 >= 0 && cekSKanan(x,y-1,TempButton)){
            int banyakGanti = cekKanan(x,y-1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x][y-i].hp = player+1;
                simpanYangDirubah[a][0] = x;
                simpanYangDirubah[a][1] = y-i;
                simpanYangDirubah[a][2] = a;
                a++;
            }
        }
        
        //kiri//////////////////////////////
        if(y+1 < size && cekSKiri(x,y+1,TempButton)){
            int banyakGanti = cekKiri(x,y+1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x][y+i].hp = player+1;
                simpanYangDirubah[a][0] = x;
                simpanYangDirubah[a][1] = y+i;
                simpanYangDirubah[a][2] = a;
                a++;
            }
        }
        
        //bawahkanan//////////////////////////////
        if(x-1 >= 0 && y-1 >= 0 && cekSBawahKanan(x-1,y-1,TempButton)){
            int banyakGanti = cekBawahKanan(x-1,y-1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x-i][y-i].hp = player+1;
                simpanYangDirubah[a][0] = x-i;
                simpanYangDirubah[a][1] = y-i;
                simpanYangDirubah[a][2] = a;
                a++;
            }
        }
        
        //bawahkiri//////////////////////////////
        if(x-1 >= 0 && y+1 < size && cekSBawahKiri(x-1,y+1,TempButton)){
            int banyakGanti = cekBawahKiri(x-1,y+1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x-i][y+i].hp = player+1;
                simpanYangDirubah[a][0] = x-i;
                simpanYangDirubah[a][1] = y+i;
                simpanYangDirubah[a][2] = a;
                a++;
            }
        }
        
        //ataskanan//////////////////////////////
        if(x+1 < size && y-1 >= 0 && cekSAtasKanan(x+1,y-1,TempButton)){
            int banyakGanti = cekAtasKanan(x+1,y-1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x+i][y-i].hp = player+1;
                simpanYangDirubah[a][0] = x+i;
                simpanYangDirubah[a][1] = y-i;
                simpanYangDirubah[a][2] = a;
                a++;
            }
        }
        
        //ataskiri//////////////////////////////
        if(x+1 < size && y+1 < size && cekSAtasKiri(x+1,y+1,TempButton)){
            int banyakGanti = cekAtasKiri(x+1,y+1,TempButton);
            for(int i = 1; i <= banyakGanti; i++){
                TempButton[x+i][y+i].hp = player+1;
                simpanYangDirubah[a][0] = x+i;
                simpanYangDirubah[a][1] = y+i;
                simpanYangDirubah[a][2] = a;
                a++;
            }
        }
        return simpanYangDirubah;
    }
    
    /* ---------------------------------------------------------------------------------------------------------------------------------------------------------------
    digunakan untuk menghitung jumlah bidak hitam dan putih yang berada pada papan permainan
    input   seluruh objek
    output  nilai bidak hitam dan nilai bidak putih
    --------------------------------------------------------------------------------------------------------------------------------------------------------------- */
    public static void setJumlahPH(){
        jumlahPutih=0;
        jumlahHitam=0;
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                if(buttons[x][y].hp == 1){
                    jumlahPutih++;
                }else if(buttons[x][y].hp == 2){
                    jumlahHitam++;
                }
            }
        }
    }
    
    public static void pause(int seconds){
        Date start = new Date();
        Date end = new Date();
        while(end.getTime() - start.getTime() < seconds * 1000){
            end = new Date();
        }
    }
    
    public static void pass(){
        player++;
        player%=2;
        System.out.println("not Available "+ player);
        if(jumlahHitam + jumlahPutih == size*size){
            finish();
        }
        if(setAvailableClick()==0 && jumlahHitam + jumlahPutih >= 4){
            finish();
        }
    }
    
    public static void finish(){
        Window w = SwingUtilities.getWindowAncestor(p);
        w.setVisible(false);
        p.removeAll();
        GameOver GO = new GameOver(jumlahHitam,jumlahPutih);
        GO.setVisible(true);
    }
}
