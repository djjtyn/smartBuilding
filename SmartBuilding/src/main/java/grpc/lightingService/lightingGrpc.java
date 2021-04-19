package grpc.lightingService;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *Create the rpc methods
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: lightingService.proto")
public final class lightingGrpc {

  private lightingGrpc() {}

  public static final String SERVICE_NAME = "lightingService.lighting";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.lightingService.Room,
      grpc.lightingService.LightingResponse> getAdjustLightingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "adjustLighting",
      requestType = grpc.lightingService.Room.class,
      responseType = grpc.lightingService.LightingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.lightingService.Room,
      grpc.lightingService.LightingResponse> getAdjustLightingMethod() {
    io.grpc.MethodDescriptor<grpc.lightingService.Room, grpc.lightingService.LightingResponse> getAdjustLightingMethod;
    if ((getAdjustLightingMethod = lightingGrpc.getAdjustLightingMethod) == null) {
      synchronized (lightingGrpc.class) {
        if ((getAdjustLightingMethod = lightingGrpc.getAdjustLightingMethod) == null) {
          lightingGrpc.getAdjustLightingMethod = getAdjustLightingMethod = 
              io.grpc.MethodDescriptor.<grpc.lightingService.Room, grpc.lightingService.LightingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "lightingService.lighting", "adjustLighting"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.lightingService.Room.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.lightingService.LightingResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new lightingMethodDescriptorSupplier("adjustLighting"))
                  .build();
          }
        }
     }
     return getAdjustLightingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.lightingService.Room,
      grpc.lightingService.LightingResponse> getAdjustLightingMultiRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "adjustLightingMultiRoom",
      requestType = grpc.lightingService.Room.class,
      responseType = grpc.lightingService.LightingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.lightingService.Room,
      grpc.lightingService.LightingResponse> getAdjustLightingMultiRoomMethod() {
    io.grpc.MethodDescriptor<grpc.lightingService.Room, grpc.lightingService.LightingResponse> getAdjustLightingMultiRoomMethod;
    if ((getAdjustLightingMultiRoomMethod = lightingGrpc.getAdjustLightingMultiRoomMethod) == null) {
      synchronized (lightingGrpc.class) {
        if ((getAdjustLightingMultiRoomMethod = lightingGrpc.getAdjustLightingMultiRoomMethod) == null) {
          lightingGrpc.getAdjustLightingMultiRoomMethod = getAdjustLightingMultiRoomMethod = 
              io.grpc.MethodDescriptor.<grpc.lightingService.Room, grpc.lightingService.LightingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "lightingService.lighting", "adjustLightingMultiRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.lightingService.Room.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.lightingService.LightingResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new lightingMethodDescriptorSupplier("adjustLightingMultiRoom"))
                  .build();
          }
        }
     }
     return getAdjustLightingMultiRoomMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static lightingStub newStub(io.grpc.Channel channel) {
    return new lightingStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static lightingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new lightingBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static lightingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new lightingFutureStub(channel);
  }

  /**
   * <pre>
   *Create the rpc methods
   * </pre>
   */
  public static abstract class lightingImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *Unary Streaming RPC
     * </pre>
     */
    public void adjustLighting(grpc.lightingService.Room request,
        io.grpc.stub.StreamObserver<grpc.lightingService.LightingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAdjustLightingMethod(), responseObserver);
    }

    /**
     * <pre>
     *Client streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.lightingService.Room> adjustLightingMultiRoom(
        io.grpc.stub.StreamObserver<grpc.lightingService.LightingResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getAdjustLightingMultiRoomMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAdjustLightingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.lightingService.Room,
                grpc.lightingService.LightingResponse>(
                  this, METHODID_ADJUST_LIGHTING)))
          .addMethod(
            getAdjustLightingMultiRoomMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                grpc.lightingService.Room,
                grpc.lightingService.LightingResponse>(
                  this, METHODID_ADJUST_LIGHTING_MULTI_ROOM)))
          .build();
    }
  }

  /**
   * <pre>
   *Create the rpc methods
   * </pre>
   */
  public static final class lightingStub extends io.grpc.stub.AbstractStub<lightingStub> {
    private lightingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private lightingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected lightingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new lightingStub(channel, callOptions);
    }

    /**
     * <pre>
     *Unary Streaming RPC
     * </pre>
     */
    public void adjustLighting(grpc.lightingService.Room request,
        io.grpc.stub.StreamObserver<grpc.lightingService.LightingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAdjustLightingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Client streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.lightingService.Room> adjustLightingMultiRoom(
        io.grpc.stub.StreamObserver<grpc.lightingService.LightingResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getAdjustLightingMultiRoomMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   *Create the rpc methods
   * </pre>
   */
  public static final class lightingBlockingStub extends io.grpc.stub.AbstractStub<lightingBlockingStub> {
    private lightingBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private lightingBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected lightingBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new lightingBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *Unary Streaming RPC
     * </pre>
     */
    public grpc.lightingService.LightingResponse adjustLighting(grpc.lightingService.Room request) {
      return blockingUnaryCall(
          getChannel(), getAdjustLightingMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *Create the rpc methods
   * </pre>
   */
  public static final class lightingFutureStub extends io.grpc.stub.AbstractStub<lightingFutureStub> {
    private lightingFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private lightingFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected lightingFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new lightingFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *Unary Streaming RPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.lightingService.LightingResponse> adjustLighting(
        grpc.lightingService.Room request) {
      return futureUnaryCall(
          getChannel().newCall(getAdjustLightingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADJUST_LIGHTING = 0;
  private static final int METHODID_ADJUST_LIGHTING_MULTI_ROOM = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final lightingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(lightingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADJUST_LIGHTING:
          serviceImpl.adjustLighting((grpc.lightingService.Room) request,
              (io.grpc.stub.StreamObserver<grpc.lightingService.LightingResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADJUST_LIGHTING_MULTI_ROOM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.adjustLightingMultiRoom(
              (io.grpc.stub.StreamObserver<grpc.lightingService.LightingResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class lightingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    lightingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.lightingService.LightingServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("lighting");
    }
  }

  private static final class lightingFileDescriptorSupplier
      extends lightingBaseDescriptorSupplier {
    lightingFileDescriptorSupplier() {}
  }

  private static final class lightingMethodDescriptorSupplier
      extends lightingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    lightingMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (lightingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new lightingFileDescriptorSupplier())
              .addMethod(getAdjustLightingMethod())
              .addMethod(getAdjustLightingMultiRoomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
