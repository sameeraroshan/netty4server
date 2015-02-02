package initializer;

import ServerHandler.TcpServerDownStreamHandler;
import ServerHandler.TcpServerUpstreamHandler;
import decoder.FrameDecoder;
import encoder.FrameEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.EventExecutor;

import java.util.ArrayList;


/**
 * Created by roshans on 1/27/2015.
 */
public class TcpInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new FrameDecoder());
        pipeline.addLast(new TcpServerUpstreamHandler());
        pipeline.addLast(new FrameEncoder());
        pipeline.addLast(new TcpServerDownStreamHandler());
    }
}
