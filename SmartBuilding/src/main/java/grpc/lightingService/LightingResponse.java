// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: lightingService.proto

package grpc.lightingService;

/**
 * Protobuf type {@code lightingService.LightingResponse}
 */
public  final class LightingResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:lightingService.LightingResponse)
    LightingResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use LightingResponse.newBuilder() to construct.
  private LightingResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private LightingResponse() {
    brightnessValue_ = 0;
    lightingMessage_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private LightingResponse(
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

            brightnessValue_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            lightingMessage_ = s;
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
    return grpc.lightingService.LightingServiceImpl.internal_static_lightingService_LightingResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return grpc.lightingService.LightingServiceImpl.internal_static_lightingService_LightingResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            grpc.lightingService.LightingResponse.class, grpc.lightingService.LightingResponse.Builder.class);
  }

  public static final int BRIGHTNESSVALUE_FIELD_NUMBER = 1;
  private int brightnessValue_;
  /**
   * <code>int32 brightnessValue = 1;</code>
   */
  public int getBrightnessValue() {
    return brightnessValue_;
  }

  public static final int LIGHTINGMESSAGE_FIELD_NUMBER = 2;
  private volatile java.lang.Object lightingMessage_;
  /**
   * <code>string lightingMessage = 2;</code>
   */
  public java.lang.String getLightingMessage() {
    java.lang.Object ref = lightingMessage_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      lightingMessage_ = s;
      return s;
    }
  }
  /**
   * <code>string lightingMessage = 2;</code>
   */
  public com.google.protobuf.ByteString
      getLightingMessageBytes() {
    java.lang.Object ref = lightingMessage_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      lightingMessage_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (brightnessValue_ != 0) {
      output.writeInt32(1, brightnessValue_);
    }
    if (!getLightingMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, lightingMessage_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (brightnessValue_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, brightnessValue_);
    }
    if (!getLightingMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, lightingMessage_);
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
    if (!(obj instanceof grpc.lightingService.LightingResponse)) {
      return super.equals(obj);
    }
    grpc.lightingService.LightingResponse other = (grpc.lightingService.LightingResponse) obj;

    boolean result = true;
    result = result && (getBrightnessValue()
        == other.getBrightnessValue());
    result = result && getLightingMessage()
        .equals(other.getLightingMessage());
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
    hash = (37 * hash) + BRIGHTNESSVALUE_FIELD_NUMBER;
    hash = (53 * hash) + getBrightnessValue();
    hash = (37 * hash) + LIGHTINGMESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getLightingMessage().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static grpc.lightingService.LightingResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.lightingService.LightingResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.lightingService.LightingResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.lightingService.LightingResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.lightingService.LightingResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.lightingService.LightingResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.lightingService.LightingResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.lightingService.LightingResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.lightingService.LightingResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static grpc.lightingService.LightingResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.lightingService.LightingResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.lightingService.LightingResponse parseFrom(
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
  public static Builder newBuilder(grpc.lightingService.LightingResponse prototype) {
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
   * Protobuf type {@code lightingService.LightingResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:lightingService.LightingResponse)
      grpc.lightingService.LightingResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return grpc.lightingService.LightingServiceImpl.internal_static_lightingService_LightingResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return grpc.lightingService.LightingServiceImpl.internal_static_lightingService_LightingResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              grpc.lightingService.LightingResponse.class, grpc.lightingService.LightingResponse.Builder.class);
    }

    // Construct using grpc.lightingService.LightingResponse.newBuilder()
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
      brightnessValue_ = 0;

      lightingMessage_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return grpc.lightingService.LightingServiceImpl.internal_static_lightingService_LightingResponse_descriptor;
    }

    @java.lang.Override
    public grpc.lightingService.LightingResponse getDefaultInstanceForType() {
      return grpc.lightingService.LightingResponse.getDefaultInstance();
    }

    @java.lang.Override
    public grpc.lightingService.LightingResponse build() {
      grpc.lightingService.LightingResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public grpc.lightingService.LightingResponse buildPartial() {
      grpc.lightingService.LightingResponse result = new grpc.lightingService.LightingResponse(this);
      result.brightnessValue_ = brightnessValue_;
      result.lightingMessage_ = lightingMessage_;
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
      if (other instanceof grpc.lightingService.LightingResponse) {
        return mergeFrom((grpc.lightingService.LightingResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(grpc.lightingService.LightingResponse other) {
      if (other == grpc.lightingService.LightingResponse.getDefaultInstance()) return this;
      if (other.getBrightnessValue() != 0) {
        setBrightnessValue(other.getBrightnessValue());
      }
      if (!other.getLightingMessage().isEmpty()) {
        lightingMessage_ = other.lightingMessage_;
        onChanged();
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
      grpc.lightingService.LightingResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (grpc.lightingService.LightingResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int brightnessValue_ ;
    /**
     * <code>int32 brightnessValue = 1;</code>
     */
    public int getBrightnessValue() {
      return brightnessValue_;
    }
    /**
     * <code>int32 brightnessValue = 1;</code>
     */
    public Builder setBrightnessValue(int value) {
      
      brightnessValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 brightnessValue = 1;</code>
     */
    public Builder clearBrightnessValue() {
      
      brightnessValue_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object lightingMessage_ = "";
    /**
     * <code>string lightingMessage = 2;</code>
     */
    public java.lang.String getLightingMessage() {
      java.lang.Object ref = lightingMessage_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        lightingMessage_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string lightingMessage = 2;</code>
     */
    public com.google.protobuf.ByteString
        getLightingMessageBytes() {
      java.lang.Object ref = lightingMessage_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        lightingMessage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string lightingMessage = 2;</code>
     */
    public Builder setLightingMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      lightingMessage_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string lightingMessage = 2;</code>
     */
    public Builder clearLightingMessage() {
      
      lightingMessage_ = getDefaultInstance().getLightingMessage();
      onChanged();
      return this;
    }
    /**
     * <code>string lightingMessage = 2;</code>
     */
    public Builder setLightingMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      lightingMessage_ = value;
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


    // @@protoc_insertion_point(builder_scope:lightingService.LightingResponse)
  }

  // @@protoc_insertion_point(class_scope:lightingService.LightingResponse)
  private static final grpc.lightingService.LightingResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new grpc.lightingService.LightingResponse();
  }

  public static grpc.lightingService.LightingResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<LightingResponse>
      PARSER = new com.google.protobuf.AbstractParser<LightingResponse>() {
    @java.lang.Override
    public LightingResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new LightingResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<LightingResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<LightingResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public grpc.lightingService.LightingResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

