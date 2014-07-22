package netty.discard;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

       final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                //ctx.close();
                System.out.println("Server operationComplete");
            }
        }); // (4)
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	 ByteBuf m = (ByteBuf) msg; // (1)
         try {
             long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
             System.out.println("Server : " + new Date(currentTimeMillis));
             //ctx.close();
         } finally {
             m.release();
         }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}