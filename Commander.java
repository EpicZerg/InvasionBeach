/*    */ /*    */ 
/*    */ public class Commander extends Unit
/*    */ {
/*  5 */   public String name = "Commander";
/*    */ 
/*    */   public Commander(int x, int y)
/*    */   {
/*  9 */     super(x, y);
/* 10 */     this.leader = true;
/* 11 */     this.name = "Commander";
/*    */   }
/*    */ 
/*    */   public Commander(int x, int y, int speedX, int speedY)
/*    */   {
/* 19 */     super(x, y, speedX, speedY);
/* 20 */     this.leader = true;
/* 21 */     this.name = "Commander";
/*    */   }
/*    */ 
/*    */   public void specialAbility()
/*    */   {
/* 27 */     Squad.deployment();
/*    */   }
/*    */ 
/*    */   public void shoutClass()
/*    */   {
/* 33 */     System.out.println(this.name);
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 39 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void sethpPerPixel()
/*    */   {
/* 45 */     super.sethpPerPixel();
/*    */   }
/*    */ }