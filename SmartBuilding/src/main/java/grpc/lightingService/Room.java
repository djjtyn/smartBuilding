// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: lightingService.proto

package grpc.lightingService;

/**
 * <pre>
 *Create the messages for the RPC
 * </pre>
 *
 * Protobuf type {@code lightingService.Room}
 */
public  final class Room extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:lightingService.Room)
    RoomOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Room.newBuilder() to construct.
  private Room(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Room() {
    id_ = 0;
    roomName_ = "";
    brightness_ = 0;
    blindDrawnAmount_ = 0;
    temperature_ = 0D;
    intAdjust_ = 0;
    doubleAdjust_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Room(
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
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            roomName_ = s;
            break;
          }
          case 24: {

            brightness_ = input.readInt32();
            break;
          }
          case 32: {

            blindDrawnAmount_ = input.readInt32();
            break;
          }
          case 41: {

            temperature_ = input.readDouble();
            break;
          }
          case 48: {

            intAdjust_ = input.readInt32();
            break;
          }
          case 57: {

            doubleAdjust_ = input.readDouble();
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
    return grpc.lightingService.LightingServiceImpl.internal_static_lightingService_Room_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return grpc.lightingService.LightingServiceImpl.internal_static_lightingService_Room_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            grpc.lightingService.Room.class, grpc.lightingService.Room.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <code>int32 id = 1;</code>
   */
  public int getId() {
    return id_;
  }

  public static final int ROOMNAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object roomName_;
  /**
   * <code>string roomName = 2;</code>
   */
  public java.lang.String getRoomName() {
    java.lang.Object ref = roomName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      roomName_ = s;
      return s;
    }
  }
  /**
   * <code>string roomName = 2;</code>
   */
  public com.google.protobuf.ByteString
      getRoomNameBytes() {
    java.lang.Object ref = roomName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      roomName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BRIGHTNESS_FIELD_NUMBER = 3;
  private int brightness_;
  /**
   * <code>int32 brightness = 3;</code>
   */
  public int getBrightness() {
    return brightness_;
  }

  public static final int BLINDDRAWNAMOUNT_FIELD_NUMBER = 4;
  private int blindDrawnAmount_;
  /**
   * <code>int32 blindDrawnAmount = 4;</code>
   */
  public int getBlindDrawnAmount() {
    return blindDrawnAmount_;
  }

  public static final int TEMPERATURE_FIELD_NUMBER = 5;
  private double temperature_;
  /**
   * <code>double temperature = 5;</code>
   */
  public double getTemperature() {
    return temperature_;
  }

  public static final int INTADJUST_FIELD_NUMBER = 6;
  private int intAdjust_;
  /**
   * <code>int32 intAdjust = 6;</code>
   */
  public int getIntAdjust() {
    return intAdjust_;
  }

  public static final int DOUBLEADJUST_FIELD_NUMBER = 7;
  private double doubleAdjust_;
  /**
   * <code>double doubleAdjust = 7;</code>
   */
  public double getDoubleAdjust() {
    return doubleAdjust_;
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
    if (!getRoomNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, roomName_);
    }
    if (brightness_ != 0) {
      output.writeInt32(3, brightness_);
    }
    if (blindDrawnAmount_ != 0) {
      output.writeInt32(4, blindDrawnAmount_);
    }
    if (temperature_ != 0D) {
      output.writeDouble(5, temperature_);
    }
    if (intAdjust_ != 0) {
      output.writeInt32(6, intAdjust_);
    }
    if (doubleAdjust_ != 0D) {
      output.writeDouble(7, doubleAdjust_);
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
    if (!getRoomNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, roomName_);
    }
    if (brightness_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, brightness_);
    }
    if (blindDrawnAmount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, blindDrawnAmount_);
    }
    if (temperature_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(5, temperature_);
    }
    if (intAdjust_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, intAdjust_);
    }
    if (doubleAdjust_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(7, doubleAdjust_);
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
    if (!(obj instanceof grpc.lightingService.Room)) {
      return super.equals(obj);
    }
    grpc.lightingService.Room other = (grpc.lightingService.Room) obj;

    boolean result = true;
    result = result && (getId()
        == other.getId());
    result = result && getRoomName()
        .equals(other.getRoomName());
    result = result && (getBrightness()
        == other.getBrightness());
    result = result && (getBlindDrawnAmount()
        == other.getBlindDrawnAmount());
    result = result && (
        java.lang.Double.doubleToLongBits(getTemperature())
        == java.lang.Double.doubleToLongBits(
            other.getTemperature()));
    result = result && (getIntAdjust()
        == other.getIntAdjust());
    result = result && (
        java.lang.Double.doubleToLongBits(getDoubleAdjust())
        == java.lang.Double.doubleToLongBits(
            other.getDoubleAdjust()));
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
    hash = (37 * hash) + ROOMNAME_FIELD_NUMBER;
    hash = (53 * hash) + getRoomName().hashCode();
    hash = (37 * hash) + BRIGHTNESS_FIELD_NUMBER;
    hash = (53 * hash) + getBrightness();
    hash = (37 * hash) + BLINDDRAWNAMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + getBlindDrawnAmount();
    hash = (37 * hash) + TEMPERATURE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getTemperature()));
    hash = (37 * hash) + INTADJUST_FIELD_NUMBER;
    hash = (53 * hash) + getIntAdjust();
    hash = (37 * hash) + DOUBLEADJUST_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getDoubleAdjust()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static grpc.lightingService.Room parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.lightingService.Room parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.lightingService.Room parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.lightingService.Room parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.lightingService.Room parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.lightingService.Room parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.lightingService.Room parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.lightingService.Room parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.lightingService.Room parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static grpc.lightingService.Room parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.lightingService.Room parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.lightingService.Room parseFrom(
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
  public static Builder newBuilder(grpc.lightingService.Room prototype) {
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
   *Create the messages for the RPC
   * </pre>
   *
   * Protobuf type {@code lightingService.Room}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:lightingService.Room)
      grpc.lightingService.RoomOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return grpc.lightingService.LightingServiceImpl.internal_static_lightingService_Room_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return grpc.lightingService.LightingServiceImpl.internal_static_lightingService_Room_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              grpc.lightingService.Room.class, grpc.lightingService.Room.Builder.class);
    }

    // Construct using grpc.lightingService.Room.newBuilder()
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

      roomName_ = "";

      brightness_ = 0;

      blindDrawnAmount_ = 0;

      temperature_ = 0D;

      intAdjust_ = 0;

      doubleAdjust_ = 0D;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return grpc.lightingService.LightingServiceImpl.internal_static_lightingService_Room_descriptor;
    }

    @java.lang.Override
    public grpc.lightingService.Room getDefaultInstanceForType() {
      return grpc.lightingService.Room.getDefaultInstance();
    }

    @java.lang.Override
    public grpc.lightingService.Room build() {
      grpc.lightingService.Room result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public grpc.lightingService.Room buildPartial() {
      grpc.lightingService.Room result = new grpc.lightingService.Room(this);
      result.id_ = id_;
      result.roomName_ = roomName_;
      result.brightness_ = brightness_;
      result.blindDrawnAmount_ = blindDrawnAmount_;
      result.temperature_ = temperature_;
      result.intAdjust_ = intAdjust_;
      result.doubleAdjust_ = doubleAdjust_;
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
      if (other instanceof grpc.lightingService.Room) {
        return mergeFrom((grpc.lightingService.Room)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(grpc.lightingService.Room other) {
      if (other == grpc.lightingService.Room.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
      }
      if (!other.getRoomName().isEmpty()) {
        roomName_ = other.roomName_;
        onChanged();
      }
      if (other.getBrightness() != 0) {
        setBrightness(other.getBrightness());
      }
      if (other.getBlindDrawnAmount() != 0) {
        setBlindDrawnAmount(other.getBlindDrawnAmount());
      }
      if (other.getTemperature() != 0D) {
        setTemperature(other.getTemperature());
      }
      if (other.getIntAdjust() != 0) {
        setIntAdjust(other.getIntAdjust());
      }
      if (other.getDoubleAdjust() != 0D) {
        setDoubleAdjust(other.getDoubleAdjust());
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
      grpc.lightingService.Room parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (grpc.lightingService.Room) e.getUnfinishedMessage();
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

    private java.lang.Object roomName_ = "";
    /**
     * <code>string roomName = 2;</code>
     */
    public java.lang.String getRoomName() {
      java.lang.Object ref = roomName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        roomName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string roomName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getRoomNameBytes() {
      java.lang.Object ref = roomName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        roomName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string roomName = 2;</code>
     */
    public Builder setRoomName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      roomName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string roomName = 2;</code>
     */
    public Builder clearRoomName() {
      
      roomName_ = getDefaultInstance().getRoomName();
      onChanged();
      return this;
    }
    /**
     * <code>string roomName = 2;</code>
     */
    public Builder setRoomNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      roomName_ = value;
      onChanged();
      return this;
    }

    private int brightness_ ;
    /**
     * <code>int32 brightness = 3;</code>
     */
    public int getBrightness() {
      return brightness_;
    }
    /**
     * <code>int32 brightness = 3;</code>
     */
    public Builder setBrightness(int value) {
      
      brightness_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 brightness = 3;</code>
     */
    public Builder clearBrightness() {
      
      brightness_ = 0;
      onChanged();
      return this;
    }

    private int blindDrawnAmount_ ;
    /**
     * <code>int32 blindDrawnAmount = 4;</code>
     */
    public int getBlindDrawnAmount() {
      return blindDrawnAmount_;
    }
    /**
     * <code>int32 blindDrawnAmount = 4;</code>
     */
    public Builder setBlindDrawnAmount(int value) {
      
      blindDrawnAmount_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 blindDrawnAmount = 4;</code>
     */
    public Builder clearBlindDrawnAmount() {
      
      blindDrawnAmount_ = 0;
      onChanged();
      return this;
    }

    private double temperature_ ;
    /**
     * <code>double temperature = 5;</code>
     */
    public double getTemperature() {
      return temperature_;
    }
    /**
     * <code>double temperature = 5;</code>
     */
    public Builder setTemperature(double value) {
      
      temperature_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double temperature = 5;</code>
     */
    public Builder clearTemperature() {
      
      temperature_ = 0D;
      onChanged();
      return this;
    }

    private int intAdjust_ ;
    /**
     * <code>int32 intAdjust = 6;</code>
     */
    public int getIntAdjust() {
      return intAdjust_;
    }
    /**
     * <code>int32 intAdjust = 6;</code>
     */
    public Builder setIntAdjust(int value) {
      
      intAdjust_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 intAdjust = 6;</code>
     */
    public Builder clearIntAdjust() {
      
      intAdjust_ = 0;
      onChanged();
      return this;
    }

    private double doubleAdjust_ ;
    /**
     * <code>double doubleAdjust = 7;</code>
     */
    public double getDoubleAdjust() {
      return doubleAdjust_;
    }
    /**
     * <code>double doubleAdjust = 7;</code>
     */
    public Builder setDoubleAdjust(double value) {
      
      doubleAdjust_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double doubleAdjust = 7;</code>
     */
    public Builder clearDoubleAdjust() {
      
      doubleAdjust_ = 0D;
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


    // @@protoc_insertion_point(builder_scope:lightingService.Room)
  }

  // @@protoc_insertion_point(class_scope:lightingService.Room)
  private static final grpc.lightingService.Room DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new grpc.lightingService.Room();
  }

  public static grpc.lightingService.Room getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Room>
      PARSER = new com.google.protobuf.AbstractParser<Room>() {
    @java.lang.Override
    public Room parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Room(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Room> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Room> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public grpc.lightingService.Room getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

