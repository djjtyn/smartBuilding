// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: elevatorService.proto

package grpc.elevatorService;

public interface OccupantDatabaseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:elevatorService.OccupantDatabase)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .elevatorService.Occupant occupant = 1;</code>
   */
  java.util.List<grpc.elevatorService.Occupant> 
      getOccupantList();
  /**
   * <code>repeated .elevatorService.Occupant occupant = 1;</code>
   */
  grpc.elevatorService.Occupant getOccupant(int index);
  /**
   * <code>repeated .elevatorService.Occupant occupant = 1;</code>
   */
  int getOccupantCount();
  /**
   * <code>repeated .elevatorService.Occupant occupant = 1;</code>
   */
  java.util.List<? extends grpc.elevatorService.OccupantOrBuilder> 
      getOccupantOrBuilderList();
  /**
   * <code>repeated .elevatorService.Occupant occupant = 1;</code>
   */
  grpc.elevatorService.OccupantOrBuilder getOccupantOrBuilder(
      int index);
}
