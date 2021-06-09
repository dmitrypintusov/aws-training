package com.pintusau.banking.system.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pintusau.banking.system.entities.CompositeKey;
import com.pintusau.banking.system.entities.Key;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ComparisonOperator;
import software.amazon.awssdk.services.dynamodb.model.Condition;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;

@Component
public class DynamoDbClientUtil {

  public GetItemRequest getItemRequest(String tableName, CompositeKey compositeKey) {
    return GetItemRequest.builder().tableName(tableName).key(keyToAttributeMap(compositeKey)).build();
  }

  public PutItemRequest putItemRequest(String tableName, Map<String, AttributeValue> values) {
    return PutItemRequest.builder().tableName(tableName).item(values).build();
  }

  public DeleteItemRequest deleteItemRequest(String tableName, CompositeKey compositeKey) {
    return DeleteItemRequest.builder().key(keyToAttributeMap(compositeKey)).tableName(tableName).build();
  }

  public QueryRequest queryRequest(String tableName, String indexName, CompositeKey compositeKey) {
    return QueryRequest.builder().tableName(tableName).indexName(indexName).keyConditions(
        keyToConditionMap(compositeKey)).build();
  }

  private Map<String, AttributeValue> keyToAttributeMap(CompositeKey compositeKey) {
    Map<String, AttributeValue> key = new HashMap<>();
    key.put(compositeKey.getPrimaryKey().getName(), AttributeValue.builder().n(String.valueOf(compositeKey.getPrimaryKey().getValue())).build());
    key.put(compositeKey.getSortKey().getName(), AttributeValue.builder().s(compositeKey.getSortKey().getValue()).build());
    return key;
  }

  private Map<String, Condition> keyToConditionMap(CompositeKey compositeKey) {
    Map<String, Condition> conditions = new HashMap<>();
    conditions.put(compositeKey.getPrimaryKey().getName(), getCondition(compositeKey.getPrimaryKey()));
    conditions.put(compositeKey.getSortKey().getName(), getCondition(compositeKey.getSortKey()));
    return conditions;
  }

  private Condition getCondition(Key key) {
    switch (key.getType()) {
      case NUMBER:
        return getNumberCondition(key.getValue());
      case STRING:
        return getStringCondition(key.getValue());
      default:
        throw new IllegalArgumentException("No key type is provided");
    }
  }

  private Condition getNumberCondition(String value) {
    return Condition.builder().comparisonOperator(ComparisonOperator.EQ).attributeValueList(
        AttributeValue.builder().n(value).build()).build();
  }

  private Condition getStringCondition(String value) {
    return Condition.builder().comparisonOperator(ComparisonOperator.EQ).attributeValueList(
        AttributeValue.builder().s(value).build()).build();
  }
}