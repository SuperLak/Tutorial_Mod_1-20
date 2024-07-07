package net.kal.tutorial_mod.entity.projectile;

import net.kal.tutorial_mod.item.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class YellowSnowball extends ThrowableItemProjectile {

    public YellowSnowball(Level pLevel, LivingEntity pShooter) {
        super(EntityType.SNOWBALL, pShooter, pLevel);
    }

    public YellowSnowball(Level pLevel, double pX, double pY, double pZ) {
        super(EntityType.SNOWBALL, pX, pY, pZ, pLevel);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.YELLOW_SNOWBALL.get();
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItem();
        return (!itemstack.isEmpty() && !itemstack.is(this.getDefaultItem())
            ? new ItemParticleOption(ParticleTypes.ITEM, itemstack)
            : ParticleTypes.ITEM_SNOWBALL);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for (int i = 0; i < 8; i++) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int i = entity instanceof Blaze ? 3 : 0;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)i);
        System.out.println("Within the intended function, which should spell good news!");
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 160));
        }
    }

    @Override
    protected void onHit(@NotNull HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
}