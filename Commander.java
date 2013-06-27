
/*    */ /*    */ 
/*    */ public class Commander extends PlayerUnit
/*    */ {
/*    */ 
/*    */   public Commander(int x, int y)
/*    */   {
/*  9 */     super(x, y, "Commander");
/* 10 */     this.leader = true;
/*    */   }
/*    */ 
/*    */   public Commander(int x, int y, int speedX, int speedY)
/*    */   {
/* 19 */     super(x, y, speedX, speedY, "Commander");
/* 20 */     this.leader = true;
/*    */   }
/*    */ 
/*    */   public void specialAbility()
/*    */   {
/* 27 */     Squad.deployment();
/*    */   }
/*    */ 
/*    */   public void sethpPerPixel()
/*    */   {
/* 45 */     super.sethpPerPixel();
/*    */   }
/*    */ }