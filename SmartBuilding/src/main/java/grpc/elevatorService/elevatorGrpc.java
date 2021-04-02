package grpc.elevatorService;

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
    comments = "Source: elevatorService.proto")
public final class elevatorGrpc {

  private elevatorGrpc() {}

  public static final String SERVICE_NAME = "elevatorService.elevator";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.elevatorService.ElevatorRequest,
      grpc.elevatorService.ElevatorResponse> getMoveElevatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "moveElevator",
      requestType = grpc.elevatorService.ElevatorRequest.class,
      responseType = grpc.elevatorService.ElevatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.elevatorService.ElevatorRequest,
      grpc.elevatorService.ElevatorResponse> getMoveElevatorMethod() {
    io.grpc.MethodDescriptor<grpc.elevatorService.ElevatorRequest, grpc.elevatorService.ElevatorResponse> getMoveElevatorMethod;
    if ((getMoveElevatorMethod = elevatorGrpc.getMoveElevatorMethod) == null) {
      synchronized (elevatorGrpc.class) {
        if ((getMoveElevatorMethod = elevatorGrpc.getMoveElevatorMethod) == null) {
          elevatorGrpc.getMoveElevatorMethod = getMoveElevatorMethod = 
              io.grpc.MethodDescriptor.<grpc.elevatorService.ElevatorRequest, grpc.elevatorService.ElevatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "elevatorService.elevator", "moveElevator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.elevatorService.ElevatorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.elevatorService.ElevatorResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new elevatorMethodDescriptorSupplier("moveElevator"))
                  .build();
          }
        }
     }
     return getMoveElevatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.elevatorService.Elevator,
      grpc.elevatorService.ElevatorResponse> getReturnToGroundFloorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "returnToGroundFloor",
      requestType = grpc.elevatorService.Elevator.class,
      responseType = grpc.elevatorService.ElevatorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.elevatorService.Elevator,
      grpc.elevatorService.ElevatorResponse> getReturnToGroundFloorMethod() {
    io.grpc.MethodDescriptor<grpc.elevatorService.Elevator, grpc.elevatorService.ElevatorResponse> getReturnToGroundFloorMethod;
    if ((getReturnToGroundFloorMethod = elevatorGrpc.getReturnToGroundFloorMethod) == null) {
      synchronized (elevatorGrpc.class) {
        if ((getReturnToGroundFloorMethod = elevatorGrpc.getReturnToGroundFloorMethod) == null) {
          elevatorGrpc.getReturnToGroundFloorMethod = getReturnToGroundFloorMethod = 
              io.grpc.MethodDescriptor.<grpc.elevatorService.Elevator, grpc.elevatorService.ElevatorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "elevatorService.elevator", "returnToGroundFloor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.elevatorService.Elevator.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.elevatorService.ElevatorResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new elevatorMethodDescriptorSupplier("returnToGroundFloor"))
                  .build();
          }
        }
     }
     return getReturnToGroundFloorMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static elevatorStub newStub(io.grpc.Channel channel) {
    return new elevatorStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static elevatorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new elevatorBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static elevatorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new elevatorFutureStub(channel);
  }

  /**
   * <pre>
   *Create the rpc methods
   * </pre>
   */
  public static abstract class elevatorImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *Bidirectional streaming rpc
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.elevatorService.ElevatorRequest> moveElevator(
        io.grpc.stub.StreamObserver<grpc.elevatorService.ElevatorResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getMoveElevatorMethod(), responseObserver);
    }

    /**
     * <pre>
     *unary streaming rpc
     * </pre>
     */
    public void returnToGroundFloor(grpc.elevatorService.Elevator request,
        io.grpc.stub.StreamObserver<grpc.elevatorService.ElevatorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReturnToGroundFloorMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMoveElevatorMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.elevatorService.ElevatorRequest,
                grpc.elevatorService.ElevatorResponse>(
                  this, METHODID_MOVE_ELEVATOR)))
          .addMethod(
            getReturnToGroundFloorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.elevatorService.Elevator,
                grpc.elevatorService.ElevatorResponse>(
                  this, METHODID_RETURN_TO_GROUND_FLOOR)))
          .build();
    }
  }

  /**
   * <pre>
   *Create the rpc methods
   * </pre>
   */
  public static final class elevatorStub extends io.grpc.stub.AbstractStub<elevatorStub> {
    private elevatorStub(io.grpc.Channel channel) {
      super(channel);
    }

    private elevatorStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected elevatorStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new elevatorStub(channel, callOptions);
    }

    /**
     * <pre>
     *Bidirectional streaming rpc
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.elevatorService.ElevatorRequest> moveElevator(
        io.grpc.stub.StreamObserver<grpc.elevatorService.ElevatorResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getMoveElevatorMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *unary streaming rpc
     * </pre>
     */
    public void returnToGroundFloor(grpc.elevatorService.Elevator request,
        io.grpc.stub.StreamObserver<grpc.elevatorService.ElevatorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReturnToGroundFloorMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *Create the rpc methods
   * </pre>
   */
  public static final class elevatorBlockingStub extends io.grpc.stub.AbstractStub<elevatorBlockingStub> {
    private elevatorBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private elevatorBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected elevatorBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new elevatorBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *unary streaming rpc
     * </pre>
     */
    public grpc.elevatorService.ElevatorResponse returnToGroundFloor(grpc.elevatorService.Elevator request) {
      return blockingUnaryCall(
          getChannel(), getReturnToGroundFloorMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *Create the rpc methods
   * </pre>
   */
  public static final class elevatorFutureStub extends io.grpc.stub.AbstractStub<elevatorFutureStub> {
    private elevatorFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private elevatorFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected elevatorFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new elevatorFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *unary streaming rpc
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.elevatorService.ElevatorResponse> returnToGroundFloor(
        grpc.elevatorService.Elevator request) {
      return futureUnaryCall(
          getChannel().newCall(getReturnToGroundFloorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RETURN_TO_GROUND_FLOOR = 0;
  private static final int METHODID_MOVE_ELEVATOR = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final elevatorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(elevatorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RETURN_TO_GROUND_FLOOR:
          serviceImpl.returnToGroundFloor((grpc.elevatorService.Elevator) request,
              (io.grpc.stub.StreamObserver<grpc.elevatorService.ElevatorResponse>) responseObserver);
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
        case METHODID_MOVE_ELEVATOR:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.moveElevator(
              (io.grpc.stub.StreamObserver<grpc.elevatorService.ElevatorResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class elevatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    elevatorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.elevatorService.ElevatorServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("elevator");
    }
  }

  private static final class elevatorFileDescriptorSupplier
      extends elevatorBaseDescriptorSupplier {
    elevatorFileDescriptorSupplier() {}
  }

  private static final class elevatorMethodDescriptorSupplier
      extends elevatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    elevatorMethodDescriptorSupplier(String methodName) {
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
      synchronized (elevatorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new elevatorFileDescriptorSupplier())
              .addMethod(getMoveElevatorMethod())
              .addMethod(getReturnToGroundFloorMethod())
              .build();
        }
      }
    }
    return result;
  }
}
