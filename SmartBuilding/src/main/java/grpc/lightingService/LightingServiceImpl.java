// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: lightingService.proto

package grpc.lightingService;

public final class LightingServiceImpl {
  private LightingServiceImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_lightingService_Room_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_lightingService_Room_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_lightingService_LightingResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_lightingService_LightingResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025lightingService.proto\022\017lightingService" +
      "\"\220\001\n\004Room\022\n\n\002id\030\001 \001(\005\022\020\n\010roomName\030\002 \001(\t\022" +
      "\022\n\nbrightness\030\003 \001(\005\022\030\n\020blindDrawnAmount\030" +
      "\004 \001(\005\022\023\n\013temperature\030\005 \001(\001\022\021\n\tintAdjust\030" +
      "\006 \001(\005\022\024\n\014doubleAdjust\030\007 \001(\001\"D\n\020LightingR" +
      "esponse\022\027\n\017brightnessValue\030\001 \001(\005\022\027\n\017ligh" +
      "tingMessage\030\002 \001(\t2\261\001\n\010lighting\022L\n\016adjust" +
      "Lighting\022\025.lightingService.Room\032!.lighti" +
      "ngService.LightingResponse\"\000\022W\n\027adjustLi" +
      "ghtingMultiRoom\022\025.lightingService.Room\032!" +
      ".lightingService.LightingResponse\"\000(\001B-\n" +
      "\024grpc.lightingServiceB\023LightingServiceIm" +
      "plP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_lightingService_Room_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_lightingService_Room_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_lightingService_Room_descriptor,
        new java.lang.String[] { "Id", "RoomName", "Brightness", "BlindDrawnAmount", "Temperature", "IntAdjust", "DoubleAdjust", });
    internal_static_lightingService_LightingResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_lightingService_LightingResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_lightingService_LightingResponse_descriptor,
        new java.lang.String[] { "BrightnessValue", "LightingMessage", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
