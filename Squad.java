
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class Squad
/*    */ {
/*  6 */   public static ArrayList<Unit> squadUnits = new ArrayList();
/*    */ 
/*    */   public Squad()
/*    */   {
/* 10 */     for (int i = 0; i < Core.getUnits().size(); i++)
/* 11 */       squadUnits.add((Unit)Core.getUnits().get(i));
/*    */   }
/*    */ 
/*    */   public static void deployment()
/*    */   {
/* 16 */     Random r = new Random();
			if (squadUnits.size() > 0) {
/* 17 */     Unit leader = (Unit)squadUnits.get(0);
/* 18 */     for (int i = 1; i < squadUnits.size(); i++)
/* 19 */       switch (i) {
/*    */       case 1:
/* 21 */         ((Unit)squadUnits.get(1)).setBestPosX(leader.getX() + 28 + r.nextInt(9));
/* 22 */         ((Unit)squadUnits.get(1)).setBestPosY(leader.getY());
/* 23 */         ((Unit)squadUnits.get(1)).setMoveTo(true);
/* 24 */         break;
/*    */       case 2:
/* 26 */         ((Unit)squadUnits.get(2)).setBestPosX(leader.getX());
/* 27 */         ((Unit)squadUnits.get(2)).setBestPosY(leader.getY() + 28 + r.nextInt(9));
/* 28 */         ((Unit)squadUnits.get(2)).setMoveTo(true);
/* 29 */         break;
/*    */       case 3:
/* 31 */         ((Unit)squadUnits.get(3)).setBestPosX(leader.getX() + 28 + r.nextInt(9));
/* 32 */         ((Unit)squadUnits.get(3)).setBestPosY(leader.getY() + 28 + r.nextInt(9));
/* 33 */         ((Unit)squadUnits.get(3)).setMoveTo(true);
/*    */       }
			}
/*    */   }
/*    */ }
