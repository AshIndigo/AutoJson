package com.ashindigo.autojson.mixin;

import com.ashindigo.autojson.AutoJsonResourcePack;
import com.google.common.collect.Lists;
import net.fabricmc.fabric.impl.resources.ModResourcePackUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.DefaultClientResourcePack;
import net.minecraft.resource.ReloadableResourceManager;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mixin(MinecraftClient.class)
public class AutoJsonMinecraftGameMixin {
	@Shadow
	private ReloadableResourceManager resourceManager;

	private void modifyResourcePackList(List<ResourcePack> list) {
		List<ResourcePack> oldList = Lists.newArrayList(list);
		list.clear();

		for (int i = 0; i < oldList.size(); i++) {
			ResourcePack pack = oldList.get(i);
			list.add(pack);

			if (pack instanceof DefaultClientResourcePack) {
				//ModResourcePackUtil.appendModResourcePacks(list, ResourceType.CLIENT_RESOURCES);
			}
		}
		list.add(new AutoJsonResourcePack());
	}

	@Inject(method = "init", at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;", ordinal = 0, shift = At.Shift.BY, by = -2), locals = LocalCapture.CAPTURE_FAILHARD)
	public void initResources(CallbackInfo info, List<ResourcePack> list) {
		modifyResourcePackList(list);
	}

	@Inject(method = "reloadResources", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ReloadableResourceManager;beginMonitoredReload(Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Ljava/util/concurrent/CompletableFuture;Ljava/util/List;)Lnet/minecraft/resource/ResourceReloadMonitor;", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
	public void reloadResources(CallbackInfoReturnable<CompletableFuture> info, CompletableFuture<java.lang.Void> cf, List<ResourcePack> list) {
		modifyResourcePackList(list);
	}
}
