
/*    */ import java.awt.Image;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class MuzzleFlash extends Effect
/*    */ {
/*  7 */   public Animation anim = new Animation();
/*  8 */   public ArrayList<Image> pictures = new ArrayList();
/*    */   public Tileset tileset;
/*    */ 
/*    */   public MuzzleFlash(int duration, int x, int y)
/*    */   {
/* 12 */     super(duration, x, y);
/* 13 */     this.tileset = Core.shooting;
/* 14 */     loadPictures();
/* 15 */     for (int i = 0; i < this.pictures.size(); i++)
/* 16 */       this.anim.addFrame((Image)this.pictures.get(i), duration / 4);
/*    */   }
/*    */ 
/*    */   public void update(int time)
/*    */   {
/* 22 */     this.timeElapsed += time;
/* 23 */     if (this.timeElapsed >= this.duration) {
/* 24 */       this.elapsed = true;
/* 25 */       return;
/*    */     }
/* 27 */     this.anim.update(time);
/*    */   }
/*    */ 
/*    */   public Image getImage() {
/* 31 */     return this.anim.getImage();
/*    */   }
/*    */ 
/*    */   public void setImage(Image image) {
/* 35 */     this.image = image;
/*    */   }
/*    */ 
/*    */   public void loadPictures() {
/* 39 */     for (int i = 0; i < 4; i++)
/*    */       try {
/* 41 */         this.pictures.add(this.tileset.getTileImage(i));
/*    */       } catch (Exception e) {
/* 43 */         e.printStackTrace();
/*    */       }
/*    */   }
/*    */ }