package io.grpc;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.examples.helloworld.HelloResponse;
import io.grpc.netty.NettyServerBuilder;

import io.grpc.stub.StreamObserver;

public class HelloWorldServer {
    /* The port on which the server should run */
    private int port = 50051;
    private Server server;

    private void start() throws Exception {
        server = NettyServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                HelloWorldServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException{
        if(server != null){
            server.awaitTermination();
        }
    }

    public void init(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    start();
                    blockUntilShutdown();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws Exception {
        final HelloWorldServer server = new HelloWorldServer();
        server.init();
    }

    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloResponse> responseObserver) {
            HelloResponse reply = HelloResponse.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
