/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class MachineGunner extends Unit
/*    */ {
/*  4 */   public String name = "Machine Gunner";
/*    */ 
/*    */   public MachineGunner(int x, int y) {
/*  7 */     super(x, y);
/*  8 */     this.name = "Machine Gunner";
/*    */   }
/*    */ 
/*    */   public MachineGunner(int x, int y, int speedX, int speedY)
/*    */   {
/* 13 */     super(x, y, speedX, speedY);
/* 14 */     this.name = "Machine Gunner";
/*    */   }
/*    */ 
/*    */   public void shoutClass()
/*    */   {
/* 20 */     System.out.println(this.name);
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 26 */     return this.name;
/*    */   }
/*    */ }