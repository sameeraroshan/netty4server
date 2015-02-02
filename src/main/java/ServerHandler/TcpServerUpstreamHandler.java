package ServerHandler;

import data.DataObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by roshans on 1/26/2015.
 */
public class TcpServerUpstreamHandler extends ChannelInboundHandlerAdapter {
    private static final ChannelGroup channelGroup = new DefaultChannelGroup("test", GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

        Channel channel = channelHandlerContext.channel();
        System.out.println("new handler added:"+channel.remoteAddress());
        channelGroup.add(channel);
        for(Channel client:channelGroup ){
            if(client != channel){
                client.write(channel.remoteAddress()+" connected");
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        //As explained, the channelActive() method will be invoked when a connection is established and ready to generate traffic
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        try {
            DataObject dataObject = (DataObject) msg;
            Channel channel = channelHandlerContext.channel();
            for(Channel clientChannel:channelGroup ){
                if(clientChannel.remoteAddress() != channel.remoteAddress()){
                    clientChannel.write("message received-------"+ dataObject.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        throwable.printStackTrace();
        channelGroup.remove(channelHandlerContext.channel());
        channelHandlerContext.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.channelRegistered(channelHandlerContext);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.channelUnregistered(channelHandlerContext);
    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
    }
}
