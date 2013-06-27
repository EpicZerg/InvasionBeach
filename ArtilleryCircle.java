
/*    */ import java.awt.Image;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ArtilleryCircle extends Effect
/*    */ {
/*  8 */   public Animation anim = new Animation();
/*  9 */   public ArrayList<Image> pictures = new ArrayList<Image>();
/*    */   public Tileset tileset;
/*    */ 
/*    */   public ArtilleryCircle(int duration, int x, int y)
/*    */   {
/* 13 */     super(duration, x, y);
/* 14 */     this.tileset = Core.aimingCircle;
/* 15 */     loadPictures();
/* 16 */     for (int i = 0; i < this.pictures.size(); i++)
/* 17 */       this.anim.addFrame((Image)this.pictures.get(i), 50L);
/*    */   }
/*    */ 
/*    */   public void update(int time)
/*    */   {
/* 24 */     this.timeElapsed += time;
/* 25 */     if (this.timeElapsed >= this.duration) {
/* 26 */       this.elapsed = true;
/* 27 */       Core.effects.add(new Explosion(400, this.x - 26, this.y - 26));
/* 28 */       return;
/*    */     }
/* 30 */     this.anim.update(time);
/*    */   }
/*    */ 
/*    */   public Image getImage() {
/* 34 */     return this.anim.getImage();
/*    */   }
/*    */ 
/*    */   public void setImage(Image image) {
/* 38 */     this.image = image;
/*    */   }
/*    */ 
/*    */   public void loadPictures() {
/* 42 */     for (int i = 0; i < 9; i++)
/*    */       try {
/* 44 */         this.pictures.add(this.tileset.getTileImage(i, 0));
/*    */       } catch (Exception e) {
/* 46 */         e.printStackTrace();
/*    */       }
/*    */   }
/*    */ }