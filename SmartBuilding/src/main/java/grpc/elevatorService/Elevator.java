// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: elevatorService.proto

package grpc.elevatorService;

/**
 * <pre>
 *Elevator Message to be used in ElevatorRequest message
 * </pre>
 *
 * Protobuf type {@code elevatorService.Elevator}
 */
public  final class Elevator extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:elevatorService.Elevator)
    ElevatorOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Elevator.newBuilder() to construct.
  private Elevator(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Elevator() {
    id_ = 0;
    currentFloor_ = 0;
    destinationFLoor_ = 0;
    lowestFloor_ = 0;
    highestFloor_ = 0;
    capacityLimit_ = 0;
    currentCapacity_ = 0;
    isMoving_ = false;
    tDirection_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Elevator(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            id_ = input.readInt32();
            break;
          }
          case 16: {

            currentFloor_ = input.readInt32();
            break;
          }
          case 24: {

            destinationFLoor_ = input.readInt32();
            break;
          }
          case 32: {

            lowestFloor_ = input.readInt32();
            break;
          }
          case 40: {

            highestFloor_ = input.readInt32();
            break;
          }
          case 48: {

            capacityLimit_ = input.readInt32();
            break;
          }
          case 56: {

            currentCapacity_ = input.readInt32();
            break;
          }
          case 64: {

            isMoving_ = input.readBool();
            break;
          }
          case 72: {
            int rawValue = input.readEnum();

            tDirection_ = rawValue;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return grpc.elevatorService.ElevatorServiceImpl.internal_static_elevatorService_Elevator_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return grpc.elevatorService.ElevatorServiceImpl.internal_static_elevatorService_Elevator_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            grpc.elevatorService.Elevator.class, grpc.elevatorService.Elevator.Builder.class);
  }

  /**
   * Protobuf enum {@code elevatorService.Elevator.travelDirection}
   */
  public enum travelDirection
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>UP = 0;</code>
     */
    UP(0),
    /**
     * <code>DOWN = 1;</code>
     */
    DOWN(1),
    /**
     * <code>STATIONARY = 2;</code>
     */
    STATIONARY(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>UP = 0;</code>
     */
    public static final int UP_VALUE = 0;
    /**
     * <code>DOWN = 1;</code>
     */
    public static final int DOWN_VALUE = 1;
    /**
     * <code>STATIONARY = 2;</code>
     */
    public static final int STATIONARY_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static travelDirection valueOf(int value) {
      return forNumber(value);
    }

    public static travelDirection forNumber(int value) {
      switch (value) {
        case 0: return UP;
        case 1: return DOWN;
        case 2: return STATIONARY;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<travelDirection>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        travelDirection> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<travelDirection>() {
            public travelDirection findValueByNumber(int number) {
              return travelDirection.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return grpc.elevatorService.Elevator.getDescriptor().getEnumTypes().get(0);
    }

    private static final travelDirection[] VALUES = values();

    public static travelDirection valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private travelDirection(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:elevatorService.Elevator.travelDirection)
  }

  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <code>int32 id = 1;</code>
   */
  public int getId() {
    return id_;
  }

  public static final int CURRENTFLOOR_FIELD_NUMBER = 2;
  private int currentFloor_;
  /**
   * <code>int32 currentFloor = 2;</code>
   */
  public int getCurrentFloor() {
    return currentFloor_;
  }

  public static final int DESTINATIONFLOOR_FIELD_NUMBER = 3;
  private int destinationFLoor_;
  /**
   * <code>int32 destinationFLoor = 3;</code>
   */
  public int getDestinationFLoor() {
    return destinationFLoor_;
  }

  public static final int LOWESTFLOOR_FIELD_NUMBER = 4;
  private int lowestFloor_;
  /**
   * <code>int32 lowestFloor = 4;</code>
   */
  public int getLowestFloor() {
    return lowestFloor_;
  }

  public static final int HIGHESTFLOOR_FIELD_NUMBER = 5;
  private int highestFloor_;
  /**
   * <code>int32 highestFloor = 5;</code>
   */
  public int getHighestFloor() {
    return highestFloor_;
  }

  public static final int CAPACITYLIMIT_FIELD_NUMBER = 6;
  private int capacityLimit_;
  /**
   * <code>int32 capacityLimit = 6;</code>
   */
  public int getCapacityLimit() {
    return capacityLimit_;
  }

  public static final int CURRENTCAPACITY_FIELD_NUMBER = 7;
  private int currentCapacity_;
  /**
   * <code>int32 currentCapacity = 7;</code>
   */
  public int getCurrentCapacity() {
    return currentCapacity_;
  }

  public static final int ISMOVING_FIELD_NUMBER = 8;
  private boolean isMoving_;
  /**
   * <code>bool isMoving = 8;</code>
   */
  public boolean getIsMoving() {
    return isMoving_;
  }

  public static final int TDIRECTION_FIELD_NUMBER = 9;
  private int tDirection_;
  /**
   * <code>.elevatorService.Elevator.travelDirection tDirection = 9;</code>
   */
  public int getTDirectionValue() {
    return tDirection_;
  }
  /**
   * <code>.elevatorService.Elevator.travelDirection tDirection = 9;</code>
   */
  public grpc.elevatorService.Elevator.travelDirection getTDirection() {
    @SuppressWarnings("deprecation")
    grpc.elevatorService.Elevator.travelDirection result = grpc.elevatorService.Elevator.travelDirection.valueOf(tDirection_);
    return result == null ? grpc.elevatorService.Elevator.travelDirection.UNRECOGNIZED : result;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (id_ != 0) {
      output.writeInt32(1, id_);
    }
    if (currentFloor_ != 0) {
      output.writeInt32(2, currentFloor_);
    }
    if (destinationFLoor_ != 0) {
      output.writeInt32(3, destinationFLoor_);
    }
    if (lowestFloor_ != 0) {
      output.writeInt32(4, lowestFloor_);
    }
    if (highestFloor_ != 0) {
      output.writeInt32(5, highestFloor_);
    }
    if (capacityLimit_ != 0) {
      output.writeInt32(6, capacityLimit_);
    }
    if (currentCapacity_ != 0) {
      output.writeInt32(7, currentCapacity_);
    }
    if (isMoving_ != false) {
      output.writeBool(8, isMoving_);
    }
    if (tDirection_ != grpc.elevatorService.Elevator.travelDirection.UP.getNumber()) {
      output.writeEnum(9, tDirection_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    if (currentFloor_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, currentFloor_);
    }
    if (destinationFLoor_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, destinationFLoor_);
    }
    if (lowestFloor_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, lowestFloor_);
    }
    if (highestFloor_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, highestFloor_);
    }
    if (capacityLimit_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, capacityLimit_);
    }
    if (currentCapacity_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, currentCapacity_);
    }
    if (isMoving_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(8, isMoving_);
    }
    if (tDirection_ != grpc.elevatorService.Elevator.travelDirection.UP.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(9, tDirection_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof grpc.elevatorService.Elevator)) {
      return super.equals(obj);
    }
    grpc.elevatorService.Elevator other = (grpc.elevatorService.Elevator) obj;

    boolean result = true;
    result = result && (getId()
        == other.getId());
    result = result && (getCurrentFloor()
        == other.getCurrentFloor());
    result = result && (getDestinationFLoor()
        == other.getDestinationFLoor());
    result = result && (getLowestFloor()
        == other.getLowestFloor());
    result = result && (getHighestFloor()
        == other.getHighestFloor());
    result = result && (getCapacityLimit()
        == other.getCapacityLimit());
    result = result && (getCurrentCapacity()
        == other.getCurrentCapacity());
    result = result && (getIsMoving()
        == other.getIsMoving());
    result = result && tDirection_ == other.tDirection_;
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId();
    hash = (37 * hash) + CURRENTFLOOR_FIELD_NUMBER;
    hash = (53 * hash) + getCurrentFloor();
    hash = (37 * hash) + DESTINATIONFLOOR_FIELD_NUMBER;
    hash = (53 * hash) + getDestinationFLoor();
    hash = (37 * hash) + LOWESTFLOOR_FIELD_NUMBER;
    hash = (53 * hash) + getLowestFloor();
    hash = (37 * hash) + HIGHESTFLOOR_FIELD_NUMBER;
    hash = (53 * hash) + getHighestFloor();
    hash = (37 * hash) + CAPACITYLIMIT_FIELD_NUMBER;
    hash = (53 * hash) + getCapacityLimit();
    hash = (37 * hash) + CURRENTCAPACITY_FIELD_NUMBER;
    hash = (53 * hash) + getCurrentCapacity();
    hash = (37 * hash) + ISMOVING_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getIsMoving());
    hash = (37 * hash) + TDIRECTION_FIELD_NUMBER;
    hash = (53 * hash) + tDirection_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static grpc.elevatorService.Elevator parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.elevatorService.Elevator parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.elevatorService.Elevator parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.elevatorService.Elevator parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.elevatorService.Elevator parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.elevatorService.Elevator parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.elevatorService.Elevator parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.elevatorService.Elevator parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.elevatorService.Elevator parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static grpc.elevatorService.Elevator parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.elevatorService.Elevator parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.elevatorService.Elevator parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(grpc.elevatorService.Elevator prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   *Elevator Message to be used in ElevatorRequest message
   * </pre>
   *
   * Protobuf type {@code elevatorService.Elevator}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:elevatorService.Elevator)
      grpc.elevatorService.ElevatorOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return grpc.elevatorService.ElevatorServiceImpl.internal_static_elevatorService_Elevator_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return grpc.elevatorService.ElevatorServiceImpl.internal_static_elevatorService_Elevator_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              grpc.elevatorService.Elevator.class, grpc.elevatorService.Elevator.Builder.class);
    }

    // Construct using grpc.elevatorService.Elevator.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = 0;

      currentFloor_ = 0;

      destinationFLoor_ = 0;

      lowestFloor_ = 0;

      highestFloor_ = 0;

      capacityLimit_ = 0;

      currentCapacity_ = 0;

      isMoving_ = false;

      tDirection_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return grpc.elevatorService.ElevatorServiceImpl.internal_static_elevatorService_Elevator_descriptor;
    }

    @java.lang.Override
    public grpc.elevatorService.Elevator getDefaultInstanceForType() {
      return grpc.elevatorService.Elevator.getDefaultInstance();
    }

    @java.lang.Override
    public grpc.elevatorService.Elevator build() {
      grpc.elevatorService.Elevator result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public grpc.elevatorService.Elevator buildPartial() {
      grpc.elevatorService.Elevator result = new grpc.elevatorService.Elevator(this);
      result.id_ = id_;
      result.currentFloor_ = currentFloor_;
      result.destinationFLoor_ = destinationFLoor_;
      result.lowestFloor_ = lowestFloor_;
      result.highestFloor_ = highestFloor_;
      result.capacityLimit_ = capacityLimit_;
      result.currentCapacity_ = currentCapacity_;
      result.isMoving_ = isMoving_;
      result.tDirection_ = tDirection_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof grpc.elevatorService.Elevator) {
        return mergeFrom((grpc.elevatorService.Elevator)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(grpc.elevatorService.Elevator other) {
      if (other == grpc.elevatorService.Elevator.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
      }
      if (other.getCurrentFloor() != 0) {
        setCurrentFloor(other.getCurrentFloor());
      }
      if (other.getDestinationFLoor() != 0) {
        setDestinationFLoor(other.getDestinationFLoor());
      }
      if (other.getLowestFloor() != 0) {
        setLowestFloor(other.getLowestFloor());
      }
      if (other.getHighestFloor() != 0) {
        setHighestFloor(other.getHighestFloor());
      }
      if (other.getCapacityLimit() != 0) {
        setCapacityLimit(other.getCapacityLimit());
      }
      if (other.getCurrentCapacity() != 0) {
        setCurrentCapacity(other.getCurrentCapacity());
      }
      if (other.getIsMoving() != false) {
        setIsMoving(other.getIsMoving());
      }
      if (other.tDirection_ != 0) {
        setTDirectionValue(other.getTDirectionValue());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      grpc.elevatorService.Elevator parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (grpc.elevatorService.Elevator) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int id_ ;
    /**
     * <code>int32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }
    /**
     * <code>int32 id = 1;</code>
     */
    public Builder setId(int value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 id = 1;</code>
     */
    public Builder clearId() {
      
      id_ = 0;
      onChanged();
      return this;
    }

    private int currentFloor_ ;
    /**
     * <code>int32 currentFloor = 2;</code>
     */
    public int getCurrentFloor() {
      return currentFloor_;
    }
    /**
     * <code>int32 currentFloor = 2;</code>
     */
    public Builder setCurrentFloor(int value) {
      
      currentFloor_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 currentFloor = 2;</code>
     */
    public Builder clearCurrentFloor() {
      
      currentFloor_ = 0;
      onChanged();
      return this;
    }

    private int destinationFLoor_ ;
    /**
     * <code>int32 destinationFLoor = 3;</code>
     */
    public int getDestinationFLoor() {
      return destinationFLoor_;
    }
    /**
     * <code>int32 destinationFLoor = 3;</code>
     */
    public Builder setDestinationFLoor(int value) {
      
      destinationFLoor_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 destinationFLoor = 3;</code>
     */
    public Builder clearDestinationFLoor() {
      
      destinationFLoor_ = 0;
      onChanged();
      return this;
    }

    private int lowestFloor_ ;
    /**
     * <code>int32 lowestFloor = 4;</code>
     */
    public int getLowestFloor() {
      return lowestFloor_;
    }
    /**
     * <code>int32 lowestFloor = 4;</code>
     */
    public Builder setLowestFloor(int value) {
      
      lowestFloor_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 lowestFloor = 4;</code>
     */
    public Builder clearLowestFloor() {
      
      lowestFloor_ = 0;
      onChanged();
      return this;
    }

    private int highestFloor_ ;
    /**
     * <code>int32 highestFloor = 5;</code>
     */
    public int getHighestFloor() {
      return highestFloor_;
    }
    /**
     * <code>int32 highestFloor = 5;</code>
     */
    public Builder setHighestFloor(int value) {
      
      highestFloor_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 highestFloor = 5;</code>
     */
    public Builder clearHighestFloor() {
      
      highestFloor_ = 0;
      onChanged();
      return this;
    }

    private int capacityLimit_ ;
    /**
     * <code>int32 capacityLimit = 6;</code>
     */
    public int getCapacityLimit() {
      return capacityLimit_;
    }
    /**
     * <code>int32 capacityLimit = 6;</code>
     */
    public Builder setCapacityLimit(int value) {
      
      capacityLimit_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 capacityLimit = 6;</code>
     */
    public Builder clearCapacityLimit() {
      
      capacityLimit_ = 0;
      onChanged();
      return this;
    }

    private int currentCapacity_ ;
    /**
     * <code>int32 currentCapacity = 7;</code>
     */
    public int getCurrentCapacity() {
      return currentCapacity_;
    }
    /**
     * <code>int32 currentCapacity = 7;</code>
     */
    public Builder setCurrentCapacity(int value) {
      
      currentCapacity_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 currentCapacity = 7;</code>
     */
    public Builder clearCurrentCapacity() {
      
      currentCapacity_ = 0;
      onChanged();
      return this;
    }

    private boolean isMoving_ ;
    /**
     * <code>bool isMoving = 8;</code>
     */
    public boolean getIsMoving() {
      return isMoving_;
    }
    /**
     * <code>bool isMoving = 8;</code>
     */
    public Builder setIsMoving(boolean value) {
      
      isMoving_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool isMoving = 8;</code>
     */
    public Builder clearIsMoving() {
      
      isMoving_ = false;
      onChanged();
      return this;
    }

    private int tDirection_ = 0;
    /**
     * <code>.elevatorService.Elevator.travelDirection tDirection = 9;</code>
     */
    public int getTDirectionValue() {
      return tDirection_;
    }
    /**
     * <code>.elevatorService.Elevator.travelDirection tDirection = 9;</code>
     */
    public Builder setTDirectionValue(int value) {
      tDirection_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.elevatorService.Elevator.travelDirection tDirection = 9;</code>
     */
    public grpc.elevatorService.Elevator.travelDirection getTDirection() {
      @SuppressWarnings("deprecation")
      grpc.elevatorService.Elevator.travelDirection result = grpc.elevatorService.Elevator.travelDirection.valueOf(tDirection_);
      return result == null ? grpc.elevatorService.Elevator.travelDirection.UNRECOGNIZED : result;
    }
    /**
     * <code>.elevatorService.Elevator.travelDirection tDirection = 9;</code>
     */
    public Builder setTDirection(grpc.elevatorService.Elevator.travelDirection value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      tDirection_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.elevatorService.Elevator.travelDirection tDirection = 9;</code>
     */
    public Builder clearTDirection() {
      
      tDirection_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:elevatorService.Elevator)
  }

  // @@protoc_insertion_point(class_scope:elevatorService.Elevator)
  private static final grpc.elevatorService.Elevator DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new grpc.elevatorService.Elevator();
  }

  public static grpc.elevatorService.Elevator getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Elevator>
      PARSER = new com.google.protobuf.AbstractParser<Elevator>() {
    @java.lang.Override
    public Elevator parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Elevator(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Elevator> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Elevator> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public grpc.elevatorService.Elevator getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

