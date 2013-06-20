/*    */ import java.awt.Rectangle;
/*    */ 
/*    */ public class Barricade
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private Rectangle box;
/*    */ 
/*    */   public Barricade(int x, int y)
/*    */   {
/*  9 */     this.x = x;
/* 10 */     this.y = y;
/* 11 */     this.box = new Rectangle(x, y, 64, 40);
/*    */   }
/*    */ 
/*    */   public int getX() {
/* 15 */     return this.x;
/*    */   }
/*    */ 
/*    */   public int getY() {
/* 19 */     return this.y;
/*    */   }
/*    */ 
/*    */   public void setX(int x) {
/* 23 */     this.x = x;
/*    */   }
/*    */ 
/*    */   public void setY(int y) {
/* 27 */     this.y = y;
/*    */   }
/*    */ 
/*    */   public Rectangle getBox() {
/* 31 */     return this.box;
/*    */   }
/*    */ }