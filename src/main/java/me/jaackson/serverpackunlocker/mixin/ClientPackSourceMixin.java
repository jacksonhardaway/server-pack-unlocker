package me.jaackson.serverpackunlocker.mixin;

import net.minecraft.client.resources.ClientPackSource;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.File;
import java.util.concurrent.CompletableFuture;

@Mixin(ClientPackSource.class)
public class ClientPackSourceMixin {

    @Shadow
    @Nullable
    private Pack serverPack;

    @Inject(at = @At("TAIL"), method = "setServerPack", locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void unlockServerPack(File file, PackSource packSource, CallbackInfoReturnable<CompletableFuture<Void>> cir, PackMetadataSection section) {
        if (this.serverPack == null)
            throw new IllegalStateException("Server pack was not created");

        this.serverPack = new Pack(
                this.serverPack.getId(),
                this.serverPack.isRequired(),
                ((PackAccessor) this.serverPack).getSupplier(),
                this.serverPack.getTitle(),
                this.serverPack.getDescription(),
                this.serverPack.getCompatibility(),
                this.serverPack.getDefaultPosition(),
                false, // Unlocks the pack and allows it to be moved
                this.serverPack.getPackSource());
    }
}