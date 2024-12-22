
package GameEffect;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Animation {
    private String name;
    private boolean isRepeated;

    private ArrayList<FrameImage> frameImages;
    public int currentFrame;

    private ArrayList<Boolean> ignoreFrames;

    private ArrayList<Double> delayFrames;
    private long beginTime;

    private boolean drawRectFrame;


    //khởi tạo cho các đối tượng đã được khai báo trước đó
    
    public Animation(){
        delayFrames = new ArrayList<Double>();
        beginTime = 0;
        currentFrame = 0;

        ignoreFrames = new ArrayList<Boolean>();

        frameImages = new ArrayList<FrameImage>();

        drawRectFrame = false;
        
        isRepeated = true;
    }

    // tạo copy constructor để vẽ ra các animation khác

    public Animation(Animation animation){
        
        beginTime = animation.beginTime;
        currentFrame = animation.currentFrame;
        drawRectFrame = animation.drawRectFrame;
        isRepeated = animation.isRepeated;
        
        delayFrames = new ArrayList<Double>();
        for(Double d : animation.delayFrames){
            delayFrames.add(d);
        }
        
        ignoreFrames = new ArrayList<Boolean>();
        for(boolean b : animation.ignoreFrames){
            ignoreFrames.add(b);
        }
        
        frameImages = new ArrayList<FrameImage>();
        for(FrameImage f : animation.frameImages){
            frameImages.add(new FrameImage(f));
        }

    }

    //Tạo hàm getter setter cho các biến khởi tạo
    
    public void setIsRepeated(boolean isRepeated){
        this.isRepeated = isRepeated;
    }
    
    public boolean getIsRepeated(){
        return isRepeated;
    }
    
    public boolean isIgnoreFrame(int id){
        return ignoreFrames.get(id);
    }
    
    public void setIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, true);
    }
    
    public void unIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, false);
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void setCurrentFrame(int currentFrame){
        if(currentFrame >= 0 && currentFrame < frameImages.size())
            this.currentFrame = currentFrame;
        else this.currentFrame = 0;
    }
    public int getCurrentFrame(){
        return this.currentFrame;
    }

    // cập nhật lại trạng thái di chuyển nếu đang di chuyển bằng phím VK_RIGHT hay VK_LEFT mà dừng đột ngột thì sẽ quay lại hoạt ảnh đứng chờ

    public void reset(){
        currentFrame = 0;
        beginTime = 0;

        for(int i = 0 ; i < ignoreFrames.size(); i++){
            ignoreFrames.set(i, false);
        }
    }



    public void add(FrameImage frameImage, double timeToNextFrame){

        ignoreFrames.add(false);
        frameImages.add(frameImage);
        delayFrames.add(new Double(timeToNextFrame));
        
    }

     public BufferedImage getCurrentImage(){
        return frameImages.get(currentFrame).getImage();
    }

    public void Update(long currentTime){
        
        if(beginTime == 0) beginTime = currentTime;
        else{
            
            if(currentTime - beginTime > delayFrames.get(currentFrame)){
                nextFrame();
                beginTime = currentTime;
            }
        }
    }

// kiểm tra, vi du co 4 frame chạy từ 1 đến 4 thì khi tới 4 nó sẽ quay về 1 (thiết lập trạng thái hoạt ảnh di chuyển)


    private void nextFrame(){
        
        if(currentFrame >= frameImages.size() - 1){
            
            if(isRepeated) currentFrame = 0;
        }
        else currentFrame++;
        
        if(ignoreFrames.get(currentFrame)) nextFrame();
        
    }


//Kiểm tra xem animation đã vẽ xong chưa, nếu rồi thì chuyển qua 

    public boolean isLastFrame(){
        if(currentFrame == frameImages.size() - 1)
            return true;
        else return false;
    }

// lật ngược các tấm hình lại, nếu có tấm hình di chuyển sang trái thì nhẹ hơn, nhưng không có thì phương thức sau sẽ lật các tấm hình trong data 
    public void flipAllImage(){
        
        for(int i = 0;i < frameImages.size(); i++){
            
            BufferedImage image = frameImages.get(i).getImage();
            
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(), 0);

            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);
            
            frameImages.get(i).setImage(image);
            
        }
    }
    public void draw( int x, int y, Graphics2D g2 ){
        
        BufferedImage image = getCurrentImage();
            
        g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
        if(drawRectFrame)
            g2.drawRect(x - image.getWidth()/2, x - image.getWidth()/2, image.getWidth(), image.getHeight());
            
        }  

    
    
}

