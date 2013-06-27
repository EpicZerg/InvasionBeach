
/*    */ import java.awt.Image;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class Animation
/*    */ {
/*    */   private ArrayList<Animation.AnimFrame> frames;
/*    */   private int currentFrame;
/*    */   private long animTime;
/*    */   private long totalDuration;
/*    */ 
/*    */   public Animation()
/*    */   {
/* 14 */     this.frames = new ArrayList<Animation.AnimFrame>();
/* 15 */     this.totalDuration = 0L;
/*    */ 
/* 17 */     synchronized (this) {
/* 18 */       this.animTime = 0L;
/* 19 */       this.currentFrame = 0;
/*    */     }
/*    */   }
/*    */ 
/*    */   public synchronized void addFrame(Image image, long duration) {
/* 24 */     this.totalDuration += duration;
/* 25 */     this.frames.add(new Animation.AnimFrame(image, this.totalDuration));
/*    */   }
/*    */ 
/*    */   public synchronized void update(long elapsedTime) {
/* 29 */     if (this.frames.size() > 1) {
/* 30 */       this.animTime += elapsedTime;
/* 31 */       if (this.animTime >= this.totalDuration) {
/* 32 */         this.animTime %= this.totalDuration;
/* 33 */         this.currentFrame = 0;
/*    */       }
/*    */ 
/* 36 */       while (this.animTime > getFrame(this.currentFrame).endTime)
/* 37 */         this.currentFrame += 1;
/*    */     }
/*    */   }
/*    */ 
/*    */   public synchronized Image getImage()
/*    */   {
/* 43 */     if (this.frames.size() == 0) {
/* 44 */       return null;
/*    */     }
/* 46 */     return getFrame(this.currentFrame).image;
/*    */   }
/*    */ 
/*    */   private Animation.AnimFrame getFrame(int i)
/*    */   {
/* 51 */     return (Animation.AnimFrame)this.frames.get(i);
/*    */   }
/*    */   private class AnimFrame {
/*    */     Image image;
/*    */     long endTime;
/*    */ 
/*    */     public AnimFrame(Image image, long endTime) {
/* 59 */       this.image = image;
/* 60 */       this.endTime = endTime;
/*    */     }
/*    */   }
/*    */ }
