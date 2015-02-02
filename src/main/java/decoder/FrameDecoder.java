package decoder;

import data.DataObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by roshans on 1/27/2015.
 */
public class FrameDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf buffer, List<Object> outList) throws Exception {
        if (buffer.readableBytes() < 2) {
            return;
        }
        DataObject dataObject = new DataObject();
        //lets assume length can be divided by 2
        dataObject.setFirstName("your message 1");
        dataObject.setSecondName("your message 2");
        StringBuilder sb = new StringBuilder();

        while (buffer.readableBytes()>0){
            sb.append((char)buffer.readByte());
        }
        dataObject.setMessage(sb.toString());
        outList.add(dataObject);
    }

}
