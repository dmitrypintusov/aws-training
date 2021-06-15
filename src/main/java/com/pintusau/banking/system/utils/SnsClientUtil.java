package com.pintusau.banking.system.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pintusau.banking.system.entities.enums.Action;
import com.pintusau.banking.system.entities.enums.AttributeType;

import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Component
public class SnsClientUtil {

  private static final String TOPIC_ARN = "arn:aws:sns:us-east-1:896442852226:banking-system-sns";
  private static final String ACTION_ATTR = "Action";

  public PublishRequest publishRequest(String message, Action action) {
    return PublishRequest.builder().topicArn(TOPIC_ARN).message(message).messageAttributes(
        getMessageAttributes(action)).build();
  }

  private Map<String, MessageAttributeValue> getMessageAttributes(Action action) {
    Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
    messageAttributes.put(ACTION_ATTR, getStringAttribute(action.name()));
    return messageAttributes;
  }

  private MessageAttributeValue getStringAttribute(String value) {
    return MessageAttributeValue.builder().dataType(AttributeType.STRING.getType()).stringValue(value).build();
  }
}
