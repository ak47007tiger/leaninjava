package 二维码;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;

import com.swetake.util.Qrcode;

public class QRCodeEncoderTest
{

 /** Creates a new instance of QRCodeEncoderTest */
 public QRCodeEncoderTest()
 {
 }
 
 public static void create_image(String sms_info)throws Exception{
  try{
         Qrcode testQrcode =new Qrcode();
            testQrcode.setQrcodeErrorCorrect('M');
            testQrcode.setQrcodeEncodeMode('B');
            testQrcode.setQrcodeVersion(7);
            String testString = sms_info;
            byte[] d = testString.getBytes("gbk");
            System.out.println(d.length);
            //BufferedImage bi = new BufferedImage(98, 98, BufferedImage.TYPE_INT_RGB);
            BufferedImage bi = new BufferedImage(98, 98, BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g = bi.createGraphics();
            g.setBackground(Color.WHITE);
            g.clearRect(0, 0, 98, 98);
//            g.setColor(Color.YELLOW);
            g.setColor(Color.BLACK);
            
            // 限制最大字节数为120
            if (d.length>0 && d.length <120){
                boolean[][] s = testQrcode.calQrcode(d);
                for (int i=0;i<s.length;i++){
                    for (int j=0;j<s.length;j++){
                        if (s[j][i]) {
                            g.fillRect(j*2+3,i*2+3,2,2);
                        }
                    }
                }
            }
            g.dispose();
            bi.flush();
            File f = new File("D:\\"+sms_info+".jpg");
            if(!f.exists()){
       f.createNewFile();
            }
            //创建图片
            ImageIO.write(bi, "jpg", f);
            
        } // end try
        catch (Exception e) {
            e.printStackTrace();
        } // end catch
 }

 public static void main(String[] args) throws Exception {
        System.out.println(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        for(int i =1; i < 3; i ++){
         QRCodeEncoderTest.create_image(i + "_" + sdf.format(new Date()));
        }
        System.out.println(new Date());
    } // end main
}
