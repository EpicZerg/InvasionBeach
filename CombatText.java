
/*    */ public class CombatText
/*    */ {
/*  4 */   public int timer = 0; public int duration = 1000;
/*    */   public int x;
/*    */   public int y;
/*    */   public boolean visible;
/*    */   public String sDamage;
/*    */ 
/*    */   public CombatText(int damage, int x, int y)
/*    */   {
/* 11 */     this.x = x;
/* 12 */     this.y = y;
/* 13 */     this.visible = true;
/* 14 */     this.sDamage = Integer.toString(damage);
/*    */   }
/*    */ 
/*    */   public void update(int time) {
/* 18 */     if (this.timer > this.duration) {
/* 19 */       this.visible = false;
/*    */     } else {
/* 21 */       this.timer += time;
/*    */ 
/* 23 */       this.y -= 1;
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean isVisible() {
/* 28 */     return this.visible;
/*    */   }
/*    */ 
/*    */   public void setVisible(boolean visible) {
/* 32 */     this.visible = visible;
/*    */   }
/*    */ 
/*    */   public int getX() {
/* 36 */     return this.x;
/*    */   }
/*    */ 
/*    */   public int getY() {
/* 40 */     return this.y;
/*    */   }
/*    */ 
/*    */   public String getsDamage() {
/* 44 */     return this.sDamage;
/*    */   }
/*    */ 
/*    */   public void setX(int x) {
/* 48 */     this.x = x;
/*    */   }
/*    */ 
/*    */   public void setY(int y) {
/* 52 */     this.y = y;
/*    */   }
/*    */ 
/*    */   public void setsDamage(String sDamage) {
/* 56 */     this.sDamage = sDamage;
/*    */   }
/*    */ }