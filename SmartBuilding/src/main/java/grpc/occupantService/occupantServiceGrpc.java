package grpc.occupantService;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: occupantService.proto")
public final class occupantServiceGrpc {

  private occupantServiceGrpc() {}

  public static final String SERVICE_NAME = "occupantService.occupantService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.occupantService.Empty,
      grpc.occupantService.GymTrainer> getViewGymTrainersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "viewGymTrainers",
      requestType = grpc.occupantService.Empty.class,
      responseType = grpc.occupantService.GymTrainer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.occupantService.Empty,
      grpc.occupantService.GymTrainer> getViewGymTrainersMethod() {
    io.grpc.MethodDescriptor<grpc.occupantService.Empty, grpc.occupantService.GymTrainer> getViewGymTrainersMethod;
    if ((getViewGymTrainersMethod = occupantServiceGrpc.getViewGymTrainersMethod) == null) {
      synchronized (occupantServiceGrpc.class) {
        if ((getViewGymTrainersMethod = occupantServiceGrpc.getViewGymTrainersMethod) == null) {
          occupantServiceGrpc.getViewGymTrainersMethod = getViewGymTrainersMethod = 
              io.grpc.MethodDescriptor.<grpc.occupantService.Empty, grpc.occupantService.GymTrainer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "occupantService.occupantService", "viewGymTrainers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.occupantService.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.occupantService.GymTrainer.getDefaultInstance()))
                  .setSchemaDescriptor(new occupantServiceMethodDescriptorSupplier("viewGymTrainers"))
                  .build();
          }
        }
     }
     return getViewGymTrainersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static occupantServiceStub newStub(io.grpc.Channel channel) {
    return new occupantServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static occupantServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new occupantServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static occupantServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new occupantServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class occupantServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void viewGymTrainers(grpc.occupantService.Empty request,
        io.grpc.stub.StreamObserver<grpc.occupantService.GymTrainer> responseObserver) {
      asyncUnimplementedUnaryCall(getViewGymTrainersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getViewGymTrainersMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.occupantService.Empty,
                grpc.occupantService.GymTrainer>(
                  this, METHODID_VIEW_GYM_TRAINERS)))
          .build();
    }
  }

  /**
   */
  public static final class occupantServiceStub extends io.grpc.stub.AbstractStub<occupantServiceStub> {
    private occupantServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private occupantServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected occupantServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new occupantServiceStub(channel, callOptions);
    }

    /**
     */
    public void viewGymTrainers(grpc.occupantService.Empty request,
        io.grpc.stub.StreamObserver<grpc.occupantService.GymTrainer> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getViewGymTrainersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class occupantServiceBlockingStub extends io.grpc.stub.AbstractStub<occupantServiceBlockingStub> {
    private occupantServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private occupantServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected occupantServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new occupantServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<grpc.occupantService.GymTrainer> viewGymTrainers(
        grpc.occupantService.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getViewGymTrainersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class occupantServiceFutureStub extends io.grpc.stub.AbstractStub<occupantServiceFutureStub> {
    private occupantServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private occupantServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected occupantServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new occupantServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_VIEW_GYM_TRAINERS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final occupantServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(occupantServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_VIEW_GYM_TRAINERS:
          serviceImpl.viewGymTrainers((grpc.occupantService.Empty) request,
              (io.grpc.stub.StreamObserver<grpc.occupantService.GymTrainer>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class occupantServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    occupantServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.occupantService.occupantServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("occupantService");
    }
  }

  private static final class occupantServiceFileDescriptorSupplier
      extends occupantServiceBaseDescriptorSupplier {
    occupantServiceFileDescriptorSupplier() {}
  }

  private static final class occupantServiceMethodDescriptorSupplier
      extends occupantServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    occupantServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (occupantServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new occupantServiceFileDescriptorSupplier())
              .addMethod(getViewGymTrainersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
