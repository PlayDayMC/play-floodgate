/*
 * Copyright (c) 2019-2023 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Floodgate
 */

package org.geysermc.floodgate.velocity.inject;

import static org.geysermc.floodgate.core.util.ReflectionUtils.getCastedValue;
import static org.geysermc.floodgate.core.util.ReflectionUtils.getMethod;
import static org.geysermc.floodgate.core.util.ReflectionUtils.invoke;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.proxy.network.ConnectionManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.lang.reflect.Method;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.geysermc.floodgate.core.inject.Netty4PlatformInjector;

@Singleton
public final class VelocityInjector extends Netty4PlatformInjector {
    @Inject ProxyServer server;
    @Getter private boolean injected;

    @Override
    @SuppressWarnings({"DataFlowIssue", "deprecation"})
    public void inject() {
        if (isInjected()) {
            return;
        }

        ConnectionManager connectionManager = getCastedValue(server, "cm");

        // Client <-> Proxy

        var serverChannelInitializer = connectionManager.serverChannelInitializer;
        serverChannelInitializer.set(new VelocityChannelInitializer(this, serverChannelInitializer.get(), false));

        // Proxy <-> Server

        var backendChannelInitializer = connectionManager.backendChannelInitializer;
        backendChannelInitializer.set(new VelocityChannelInitializer(this, backendChannelInitializer.get(), true));

        injected = true;
    }

    @Override
    public boolean canRemoveInjection() {
        return false;
    }

    @Override
    public void removeInjection() {
        throw new IllegalStateException(
                "Floodgate cannot remove itself from Velocity without a reboot");
    }

    @RequiredArgsConstructor
    @SuppressWarnings("rawtypes")
    private static final class VelocityChannelInitializer extends ChannelInitializer<Channel> {
        private static final Method initChannel;

        static {
            initChannel = getMethod(ChannelInitializer.class, "initChannel", Channel.class);
        }

        private final VelocityInjector injector;
        private final ChannelInitializer original;
        private final boolean proxyToServer;

        @Override
        protected void initChannel(Channel channel) {
            invoke(original, initChannel, channel);

            injector.injectAddonsCall(channel, proxyToServer);
            injector.addInjectedClient(channel);
        }
    }
}
