import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

public class Server {
        public static void main(String[] args) throws Exception {
                var port = new InetSocketAddress(8000);

                var server = HttpServer.create(port, 0);

                server.createContext("/randomNumber", new RandomNumberHandler());

                server.setExecutor(null);

                server.start();
        }

        static class RandomNumberHandler implements HttpHandler {
                @Override
                public void handle(HttpExchange request) throws IOException {
                        var randomNumber = (int) (Math.random() * 100);

                        var response = Integer.toString(randomNumber);

                        var responseLength = response.getBytes().length;

                        request.sendResponseHeaders(200, responseLength);

                        var body = request.getResponseBody();

                        body.write(response.getBytes());

                        body.close();
                }
        }
}
