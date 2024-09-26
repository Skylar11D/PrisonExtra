package xyz.sk1.bukkit.prisonextra.entity.minion.pathfinder;

import net.minecraft.server.v1_8_R3.*;
import xyz.sk1.bukkit.prisonextra.entity.minion.Minion;

public class PathfinderGoalFollowOwner extends PathfinderGoal {

    //Entity
    private Minion d;
    //Owner or Summoner
    private EntityLiving e;
    World a;
    private double f;
    private NavigationAbstract g;
    private int h;
    float b;
    float c;
    private boolean i;

    /*
    * Navig
    * */

    public PathfinderGoalFollowOwner(Minion var1, double var2, float var4, float var5) {
        this.d = var1;
        this.a = var1.world;
        this.f = var2;
        this.g = var1.getNavigation();
        this.c = var4;
        this.b = var5;
        this.a(3);
        if (!(var1.getNavigation() instanceof Navigation)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }

    //shouldExecute();
    @Override
    public boolean a() {
        EntityLiving var1 = this.d.getOwner();
        if (var1 == null) {
            return false;
        } else if (var1 instanceof EntityHuman && ((EntityHuman)var1).isSpectator()) {
            return false;
        }/* else if (this.d.isSitting()) { //not tamable
            return false;
        }*/ else if (this.d.h(var1) < (double)(this.c * this.c)) {
            return false;
        } else {
            this.e = var1;
            return true;
        }
    }

    //canContinue();
    @Override
    public boolean b() {
        return !this.g.m() && this.d.h(this.e) > (double)(this.b * this.b) /*&& !this.d.isSitting() //not tamable*/;
    }

    //start()
    public void c() {
        this.h = 0;
        this.i = ((Navigation)this.d.getNavigation()).e();
        ((Navigation)this.d.getNavigation()).a(false);
    }

    //stop();
    @Override
    public void d() {
        this.e = null;
        this.g.n();
        ((Navigation)this.d.getNavigation()).a(true);
    }

    private boolean a(BlockPosition var1) {
        IBlockData var2 = this.a.getType(var1);
        Block var3 = var2.getBlock();
        if (var3 == Blocks.AIR) {
            return true;
        } else {
            return !var3.d();
        }
    }

    //tick();
    @Override
    public void e() {
        this.d.getControllerLook().a(this.e, 10.0F, (float)this.d.bQ());
        //not actually tamable
        /*if(this.d.isSitting())
            return;*/

        if (--this.h > 0)
            return;

        this.h = 10;

        if (this.g.a(this.e, this.f))
            return;

        if (this.d.cc())
            return;

        if ((this.d.h(this.e) < 144.0))
            return;

        int var1 = MathHelper.floor(this.e.locX) - 2;
        int var2 = MathHelper.floor(this.e.locZ) - 2;
        int var3 = MathHelper.floor(this.e.getBoundingBox().b);
        for(int var4 = 0; var4 <= 4; ++var4) {
            for(int var5 = 0; var5 <= 4; ++var5) {
                if ((var4 < 1 || var5 < 1 || var4 > 3 || var5 > 3) && World.a(this.a, new BlockPosition(var1 + var4, var3 - 1, var2 + var5)) && this.a(new BlockPosition(var1 + var4, var3, var2 + var5)) && this.a(new BlockPosition(var1 + var4, var3 + 1, var2 + var5))) {
                    this.d.setPositionRotation((double)((float)(var1 + var4) + 0.5F), (double)var3, (double)((float)(var2 + var5) + 0.5F), this.d.yaw, this.d.pitch);
                    this.g.n();
                    return;
                }
            }
        }

    }

}
