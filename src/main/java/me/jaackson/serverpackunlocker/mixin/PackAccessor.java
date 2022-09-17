package me.jaackson.serverpackunlocker.mixin;

import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.repository.Pack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(Pack.class)
public interface PackAccessor {

    @Accessor
    Supplier<PackResources> getSupplier();
}
