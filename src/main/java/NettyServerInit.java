import server.TcpServer;

/**
 * Created by roshans on 1/27/2015.
 */
public class NettyServerInit {
    public static int PORT = 5000;
    public static void main(String[] args){
        TcpServer nettyTcpServer = new TcpServer(PORT);
        nettyTcpServer.start();
    }
}
