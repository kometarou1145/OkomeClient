package com.Kometarou.OkomeClient.manager;

import com.Kometarou.OkomeClient.module.combat.*;
import com.Kometarou.OkomeClient.module.exploit.*;
import com.Kometarou.OkomeClient.module.misc.*;
import com.Kometarou.OkomeClient.module.movement.*;
import com.Kometarou.OkomeClient.module.render.*;
import com.Kometarou.OkomeClient.ui.hud.component.ModuleList;
import com.Kometarou.OkomeClient.ui.hud.component.PingHud;
import com.Kometarou.OkomeClient.ui.hud.component.WaterMark2;
import com.Kometarou.OkomeClient.module.Category;
import com.Kometarou.OkomeClient.module.Module;
import com.kamiskidder.shgr.module.combat.*;
import com.kamiskidder.shgr.module.exploit.*;
import com.kamiskidder.shgr.module.misc.*;
import com.kamiskidder.shgr.module.movement.*;
import com.kamiskidder.shgr.module.render.*;
import com.Kometarou.OkomeClient.ui.hud.Hud;
import com.Kometarou.OkomeClient.util.Util;
import com.Kometarou.OkomeClient.util.client.EventUtil;
import com.Kometarou.OkomeClient.util.render.RenderUtil;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ModuleManager implements Util {
    public static ModuleManager INSTANCE;

    public List<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        INSTANCE = this;
        //combat
        register(new KillAura());
        register(new Velocity());
        register(new AutoCrystal());
        register(new AutoGaiji());
        register(new BurrowBreaker());
        register(new Blocker());
        register(new AutoTotem());
        register(new MiddleClickFlyFag());
        register(new Critical());
        register(new FacePlace());
        register(new SoundAura());
        register(new TestCritical());
        //exploit
        register(new AnvilTrapBurrow());
        register(new AwaPacketFly());
        register(new XCarry());
        register(new PacketFly());
        register(new ObsTrapBurrow());
        register(new InstantMine());
        register(new LavaWaterBurrow());
        register(new EcTrapBurrow());
        register(new SelfWeb());
        register(new TestPacketFly());
        register(new TestTrapBurrow());
        register(new TestTrapBurrow2());
        //movement
        register(new NoRotate());
        register(new NoFall());
        register(new AutoWalk());
        register(new BoatFly());
        register(new Flight());
        register(new NoSlow());
        register(new Sprint());
        register(new NoPush());
        register(new EntitySpeed());
        register(new Step());
        register(new Scaffold());
        register(new Omg2b2tjpFastFly());
        register(new PhaseWalk());
        register(new TntJump());
        register(new TestNoFall());
        register(new TestReverseStep());
        register(new TestTp());
        register(new Tp());
        register(new Tp2());
        //misc
        register(new AutoAutoDupe());
        register(new AutoDupe());
        register(new LagbackLogger());
        register(new TunnelAssist());
        register(new FakePlayer());
        register(new FakePlayer2());
        register(new Breaker());
        register(new BoatAura());
        register(new SlowUse());
        register(new TimeChanger());
        register(new ChatSuffix());
        register(new FastUse());
        register(new Timer());
        register(new DiscordRPC());
        register(new PacketLogger());
        register(new NoSwing());
        //render
        register(new ClickGui());
        register(new FullBright());
        register(new CameraClip());
        register(new Notification());
        register(new WaterMark());
        register(new ESP());
        register(new GuardAnimations());
        register(new Waypoints());
        register(new Nametags());
        register(new StorageESP());
        register(new BlockHighlight());
        register(new Freecam());
        register(new AntiCollision());
        register(new CustomFov());
        register(new SwordRotator());
        //hud
        register(new com.Kometarou.OkomeClient.ui.hud.component.WaterMark());
        register(new WaterMark2());
        register(new ModuleList());
        register(new PingHud());

        EventUtil.register(this);
    }

    public static ModuleManager getInstance() {
        return INSTANCE;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        execute(Module::onTick);
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        execute(Module::onUpdate);
    }

    @SubscribeEvent
    public void onKeyTyped(InputEvent.KeyInputEvent event) {
        if (!Keyboard.getEventKeyState()) return;
        int key = Keyboard.getEventKey();
        modules.forEach(m -> {
            if (m.getBind() == key) m.toggle();
        });
    }

    @SubscribeEvent
    public void onRender2D(RenderGameOverlayEvent.Text event) {
        if (event.getType().equals(RenderGameOverlayEvent.ElementType.TEXT)) {
            execute(Module::onRender2D);
        }
    }

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event) {
        if (event.isCanceled()) return;
        if (mc.player == null || mc.world == null) return;

        mc.profiler.startSection("candy-plus");
        mc.profiler.startSection("setup");
        RenderUtil.prepare();
        mc.profiler.endSection();

        execute(m -> {
            mc.profiler.startSection(m.getName());
            m.onRender3D();
            mc.profiler.endSection();
        });

        mc.profiler.startSection("release");
        RenderUtil.release();
        mc.profiler.endSection();
        mc.profiler.endSection();
    }

    public List<Module> getModule() {
        return modules;
    }

    public Module getModuleByName(String name) {
        Module r = null;
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) r = module;
        }
        return r;
    }

    public Module getModuleByClass(Class clazz) {
        Module r = null;
        for (Module module : modules) {
            if (module.getClass().equals(clazz)) r = module;
        }
        return r;
    }

    public List<Module> getModulesByCategory(Category category) {
        return modules.stream().filter(m -> m.getCategory() == category).collect(Collectors.toList());
    }

    public List<Hud> getHudModules() {
        List<Hud> modules = new ArrayList<>();
        for (Module module : this.modules) {
            if (module instanceof Hud) {
                modules.add((Hud) module);
            }
        }
        return modules;
    }

    private void register(Module module) {
        modules.add(module);
    }

    private void execute(Consumer<? super Module> action) {
        modules.stream().filter(Module::isToggled).forEach(action);
    }
}
