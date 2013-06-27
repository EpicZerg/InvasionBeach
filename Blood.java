
/*    */ import java.awt.Image;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class Blood extends Effect
/*    */ {
/*    */   public Image image;
/*  8 */   public Random r = new Random();
/*    */ 
/*    */   public Blood(int duration, int x, int y) {
/* 11 */     super(duration, x, y);
/*    */     try {
/* 13 */       this.image = Core.bloodImage.getTileImage(this.r.nextInt(4));
/*    */     } catch (Exception e) {
/* 15 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public Image getImage()
/*    */   {
/* 22 */     return this.image;
/*    */   }
/*    */ }