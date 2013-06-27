
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class MachineGunner extends PlayerUnit
/*    */ {
/*  4 */   public String name = "Machine Gunner";
/*    */ 
/*    */   public MachineGunner(int x, int y) {
/*  7 */     super(x, y, "Machine Gunner");
/*    */   }
/*    */ 
/*    */   public MachineGunner(int x, int y, int speedX, int speedY)
/*    */   {
/* 13 */     super(x, y, speedX, speedY, "Machine Gunner");
/*    */   }
/*    */ 
/*    */ 
/*    */ }