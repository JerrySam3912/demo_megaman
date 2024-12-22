
package GameEffect;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import javax.imageio.ImageIO;
/**
 *
 * @author ADMIN
 */
public class CacheDataLoader {

    private static CacheDataLoader instance;
    
    private String framefile = "data/frame.txt";
    private String animationfile = "data/animation.txt";
    private String physmapfile = "data/phys_map.txt";
    private String backgroundmapfile = "data/background_map.txt";
    private String soundfile = "data/sounds.txt";
    
    
    private int[][] phys_map;
    private int[][] background_map;
    
    // tạo ra constructor private để cho chương trình chỉ sử dụng mỗi instance
    //mà ta khởi tạo
    
    // <key, value>
    
    private Hashtable<String, FrameImage> frameImages;
    private Hashtable<String, Animation> animations;
    private Hashtable<String, AudioClip> sounds;
    
    private CacheDataLoader(){
        
//        frameImage = new Hashtable<String, FrameImage>();
//        animation = new Hashtable<String, Animation>();
        
    }
    
    // kiểm tra xem instance được tạo ra chưa? chưa thì tạo mới
    
    public static CacheDataLoader getInstance(){
        if(instance == null){
            instance = new CacheDataLoader();
        }
        
        return instance;
        
    }
    
    public AudioClip getSound(String name){
        return instance.sounds.get(name);
    }
    
    
    public int[][] getPhysicalMap(){
        return instance.phys_map;
    }
    
    public int[][] getBackgroundMap(){
        return instance.background_map;
    }
    
    
    public void LoadData() throws IOException{
        loadFrame();
        LoadAnimation();
        LoadPhysMap();
        LoadBackgroundMap();
        LoadSounds();
    }
    
    
    public void LoadSounds() throws IOException{
        sounds = new Hashtable<String, AudioClip>();
        
        FileReader fr = new FileReader(soundfile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        if(br.readLine()==null) { // no line = "" or something like that
            System.out.println("No data");
            throw new IOException();
        }
        else {
            
            fr = new FileReader(soundfile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals(""));
            
            int n = Integer.parseInt(line);
            
            for(int i = 0;i < n; i ++){
                
                AudioClip audioClip = null;
                while((line = br.readLine()).equals(""));

                String[] str = line.split(" ");
                String name = str[0];
                
                String path = str[1];

                try {
                   audioClip =  Applet.newAudioClip(new URL("file","",str[1]));

                } catch (MalformedURLException ex) {}
                
                instance.sounds.put(name, audioClip);
            }
            
        }
        
        br.close();
        
    }
    
    
    
    public void LoadBackgroundMap() throws IOException{
        
        FileReader fr = new FileReader(backgroundmapfile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);
        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);
            
        
        instance.background_map = new int[numberOfRows][numberOfColumns];
        
        for(int i = 0;i < numberOfRows;i++){
            line = br.readLine();
            String [] str = line.split(" |  ");
            for(int j = 0;j<numberOfColumns;j++)
                instance.background_map[i][j] = Integer.parseInt(str[j]);
        }
        
        for(int i = 0;i < numberOfRows;i++){
            
            for(int j = 0;j<numberOfColumns;j++)
                System.out.print(" "+instance.background_map[i][j]);
            
            System.out.println();
        }
        
        br.close();
        
    }
    
    
    public void LoadPhysMap() throws IOException{
        
        FileReader fr = new FileReader(physmapfile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);
        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);
            
        
        instance.phys_map = new int[numberOfRows][numberOfColumns];
        
        for(int i = 0;i < numberOfRows;i++){
            line = br.readLine();
            String [] str = line.split(" ");
            for(int j = 0;j<numberOfColumns;j++)
                instance.phys_map[i][j] = Integer.parseInt(str[j]);
        }
        
        for(int i = 0;i < numberOfRows;i++){
            
            for(int j = 0;j<numberOfColumns;j++)
                System.out.print(" "+instance.phys_map[i][j]);
            
            System.out.println();
        }
        
        br.close();
        
    }
    
    public void loadFrame() throws IOException{
        frameImages = new Hashtable<String, FrameImage>();
        
        FileReader fr = new FileReader(framefile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        /* kiểm tra xem file frame.txt <framefile> có dữ liệu nào trong đó chưa?
        nếu chưa thì ngay câu lệnh if dòng thứ 59 sẽ trả về No data và quăng thằng đó
        vào IOException <ngoại lệ> */
        
        if(br.readLine() == null){
            System.out.println("NO DATA");
            throw new IOException();
        }
        else{
            
            // còn không thì tạo mới fr và br, để đọc được fr thì ta cần 
            //BufferedReader đọc fr và có thể thực thi chuỗi câu lệnh bên dưới
            
            fr = new FileReader(framefile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals(""));
            
            int n = Integer.parseInt(line);
            
            for(int i = 0 ; i < n ; i++ ){
                
                FrameImage frame = new FrameImage();
                while((line = br.readLine()).equals(""));
                frame.setName(line);
                
                while((line = br.readLine()).equals(""));
                String [] str = line.split("\\s+");
                String path = str[1];
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int x = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int y = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int w = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int h = Integer.parseInt(str[1]);
                
                BufferedImage imageData = ImageIO.read(new File(path));
                
                BufferedImage image = imageData.getSubimage(x , y , w ,h);
                frame.setImage(image);
                
                instance.frameImages.put(frame.getName(), frame);
                
            }
           
        }
        
        br.close();
   
    }
    
    public FrameImage getFrameImage(String name){
        
        FrameImage frameImage = new FrameImage(instance.frameImages.get(name));
        return frameImage;
    }
    
    public Animation getAnimation(String name){
        
        Animation animation = new Animation(instance.animations.get(name));
        return animation;
        
    }
    
    public void LoadAnimation() throws IOException {
        
        animations = new Hashtable<String, Animation>();
        
        FileReader fr = new FileReader(animationfile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        if(br.readLine()==null) {
            System.out.println("No data");
            throw new IOException();
        }
        else {
            
            fr = new FileReader(animationfile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals(""));
            int n = Integer.parseInt(line);
            
            for(int i = 0;i < n; i ++){
                
                Animation animation = new Animation();
                
                while((line = br.readLine()).equals(""));
                animation.setName(line);
                
                while((line = br.readLine()).equals(""));
                String[] str = line.split(" ");
                
                for(int j = 0;j<str.length;j+=2)
                    animation.add(getFrameImage(str[j]), Double.parseDouble(str[j+1]));
                
                instance.animations.put(animation.getName(), animation);
                
            }
            
        }
        
        br.close();
    }
}
 