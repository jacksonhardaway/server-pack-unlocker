package me.jaackson.spu.mixin;

import net.minecraft.client.resources.DownloadingPackFinder;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.IResourcePack;
import net.minecraft.resources.PackCompatibility;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

@Mixin(DownloadingPackFinder.class)
public class DownloadingPackFinderMixin {

    @Redirect(at = @At("NEW"), method = "setServerPack")
    private ResourcePackInfo unlockServerPack(String name, boolean alwaysEnabled, Supplier<IResourcePack> packFactory, ITextComponent displayName, ITextComponent description, PackCompatibility compatibility, ResourcePackInfo.Priority direction, boolean pinned, IPackNameDecorator source) {
        return new ResourcePackInfo(name, alwaysEnabled, packFactory, displayName, description, compatibility, direction, false, source);
    }

}
