
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class EnemyRow
/*    */ {
/*    */   public int y;
/*    */   public int config;
/*  7 */   public boolean[] randomList = new boolean[25];
/*  8 */   public Random r = new Random();
/*    */ 
/*    */   public EnemyRow(int y, int config)
/*    */   {
/* 12 */     this.y = y;
/* 13 */     this.config = config;
/* 14 */     fillRandomList();
/*    */ 
/* 16 */     randomizeUnits();
/*    */   }
/*    */ 
/*    */   public void fillRandomList() {
/* 20 */     for (int i = 0; i < this.randomList.length; i++)
/* 21 */       this.randomList[i] = false;
/*    */   }
/*    */ 
/*    */   public void randomizeUnits()
/*    */   {
/* 26 */     int b = this.r.nextInt(2) + 1;
/* 27 */     for (int i = 0; i < b; i++) {
/* 28 */       int x = this.r.nextInt(20) + 1;
/* 29 */       while ((x % 2 == 0) || (this.randomList[x]) || (this.randomList[(x + 1)]|| (this.randomList[(x + 2)]|| (this.randomList[(x + 3)])))) {
/* 30 */         x = this.r.nextInt(20) + 1;
/*    */       }
/* 32 */       if (this.config == 0) {
/* 33 */         Core.bunkers.add(new Bunker(672, this.y));
/* 34 */         this.randomList[21] = true;
/* 35 */         this.randomList[22] = true;
/* 36 */         this.randomList[23] = true;
/* 37 */         this.randomList[24] = true;
/*    */       } else {
/* 39 */         Core.bunkers.add(new Bunker(x * 32, this.y));
/* 40 */         for (int j = 0; j < 4; j++) {
/* 41 */           this.randomList[(x + j)] = true;
/*    */         }
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 47 */     b = this.r.nextInt(1) + 6;
/* 48 */     for (int i = 0; i < b; i++) {
/* 49 */       int x = this.r.nextInt(24);
/* 50 */       while ((x % 2 != 0) || (this.randomList[x]) || (this.randomList[(x + 1)])) {
/* 51 */         x = this.r.nextInt(24);
/*    */       }
/* 53 */       Core.barbedWire.add(new BarbedWire(x * 32, this.y));
/* 54 */       for (int j = 0; j < 2; j++) {
/* 55 */         this.randomList[(x + j)] = true;
/*    */       }
/*    */     }
/* 58 */     for (int i = 0; i < this.randomList.length; i++)
/* 59 */       if (this.randomList[i]) {
/* 60 */         Core.enemies.add(new EnemyRifle(i * 32, this.y - 16));
/* 61 */         Core.sandbags.add(new Sandbag(i * 32, this.y + 16));
/*    */       }
/*    */   }
/*    */ 
/*    */   public void enemyRow1(int y)
/*    */   {
/* 67 */     Core.barbedWire.add(new BarbedWire(0, y));
/* 68 */     Core.barbedWire.add(new BarbedWire(32, y));
/* 69 */     Core.enemies.add(new EnemyRifle(96, y));
/* 70 */     Core.enemies.add(new EnemyRifle(128, y));
/* 71 */     Core.enemies.add(new EnemyRifle(160, y));
/* 72 */     Core.barbedWire.add(new BarbedWire(192, y));
/* 73 */     Core.bunkers.add(new Bunker(256, y));
/* 74 */     Core.sandbags.add(new Sandbag(384, y));
/* 75 */     Core.sandbags.add(new Sandbag(416, y));
/* 76 */     Core.sandbags.add(new Sandbag(448, y));
/* 77 */     Core.sandbags.add(new Sandbag(480, y));
/*    */ 
/* 79 */     Core.enemies.add(new EnemyRifle(416, y - 32));
/* 80 */     Core.enemies.add(new EnemyRifle(448, y - 32));
/* 81 */     Core.enemies.add(new EnemyRifle(512, y));
/* 82 */     Core.barbedWire.add(new BarbedWire(544, y));
/* 83 */     Core.barbedWire.add(new BarbedWire(608, y));
/* 84 */     Core.barbedWire.add(new BarbedWire(672, y));
/* 85 */     Core.barbedWire.add(new BarbedWire(736, y));
/*    */   }
/*    */ }
