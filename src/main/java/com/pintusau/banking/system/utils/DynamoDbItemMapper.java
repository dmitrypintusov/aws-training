package com.pintusau.banking.system.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pintusau.banking.system.entities.AbstractEntity;
import com.pintusau.banking.system.entities.Account;
import com.pintusau.banking.system.entities.Address;
import com.pintusau.banking.system.entities.Payment;
import com.pintusau.banking.system.entities.User;
import com.pintusau.banking.system.entities.enums.Currency;
import com.pintusau.banking.system.entities.enums.Role;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbResponse;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

@Component
public class DynamoDbItemMapper<T extends AbstractEntity> {

  public T mapToObject(DynamoDbResponse response, Class<T> type) {
    if (response instanceof GetItemResponse) {
      Map<String, AttributeValue> item = ((GetItemResponse) response).item();
      return itemToObject(item, type);
    } else if (response instanceof QueryResponse) {
      List<Map<String, AttributeValue>> items = ((QueryResponse) response).items();
      return items.stream().map(item -> itemToObject(item, type)).collect(Collectors.toList()).get(0);
    }
    return null;
  }

  public Map<String, AttributeValue> objectToMap(T t) {
    if (t instanceof User) {
      return userToMap((User) t);
    } else if (t instanceof Account) {
      return accountToMap((Account) t);
    } else if (t instanceof Address) {
      return addressToMap((Address) t);
    } else if (t instanceof Payment) {
      return paymentToMap((Payment) t);
    } else {
      throw new IllegalArgumentException("Not class found for provided value");
    }
  }

  private T itemToObject(Map<String, AttributeValue> responseItem, Class<T> type) {
    if (type == User.class) {
      return (T) mapToUser(responseItem);
    } else if (type == Account.class) {
      return (T) mapToAccount(responseItem);
    } else if (type == Address.class) {
      return (T) mapToAddress(responseItem);
    } else if (type == Payment.class) {
      return (T) mapToPayment(responseItem);
    } else {
      throw new IllegalArgumentException("Not class found for provided response");
    }
  }

  private Address mapToAddress(Map<String, AttributeValue> responseItem) {
    if (responseItem != null) {
      Address address = new Address();
      address.setId(Long.parseLong(safeGetNumberParam(responseItem, "id")));
      address.setStreet(safeGetStringParam(responseItem, "street"));
      address.setHouse(Integer.parseInt(safeGetNumberParam(responseItem, "house")));
      address.setFlat(Integer.parseInt(safeGetNumberParam(responseItem, "flat")));
      return address;
    }

    return null;
  }

  private Map<String, AttributeValue> addressToMap(Address address) {
    if (address != null) {
      Map<String, AttributeValue> attributeValueMap = new HashMap<>();
      safePutNumberValue(attributeValueMap, "id", String.valueOf(address.getId()));
      safePutStringValue(attributeValueMap, "street", address.getStreet());
      safePutNumberValue(attributeValueMap, "house", String.valueOf(address.getHouse()));
      safePutNumberValue(attributeValueMap, "flat", String.valueOf(address.getFlat()));
      return attributeValueMap;
    }

    return null;
  }

  private Account mapToAccount(Map<String, AttributeValue> responseItem) {
    if (responseItem != null && responseItem.size() > 0) {
      Account account = new Account();
      account.setId(Long.parseLong(safeGetNumberParam(responseItem, "id")));
      account.setValue(Double.valueOf(safeGetNumberParam(responseItem, "value")));
      account.setCurrency(getCurrency(safeGetStringParam(responseItem, "currency")));
      return account;
    }

    return null;
  }

  private Map<String, AttributeValue> accountToMap(Account account) {
    if (account != null) {
      Map<String, AttributeValue> attributeValueMap = new HashMap<>();
      safePutNumberValue(attributeValueMap, "id", String.valueOf(account.getId()));
      safePutNumberValue(attributeValueMap, "value", String.valueOf(account.getValue()));
      safePutStringValue(attributeValueMap, "currency", account.getCurrency().name());
      return attributeValueMap;
    }

    return null;
  }

  private User mapToUser(Map<String, AttributeValue> responseItem) {
    if (responseItem != null && responseItem.size() > 0) {
      User user = new User();
      user.setId(Long.parseLong(safeGetNumberParam(responseItem, "id")));
      user.setEmail(safeGetStringParam(responseItem, "email"));
      user.setFirstName(safeGetStringParam(responseItem, "firstName"));
      user.setLastName(safeGetStringParam(responseItem, "lastName"));
      user.setActive(safeGetBooleanParam(responseItem, "active"));
      user.setRole(getRole(safeGetStringParam(responseItem, "role")));
      user.setAccount(mapToAccount(safeGetObjectParam(responseItem, "account")));
      user.setAddress(mapToAddress(safeGetObjectParam(responseItem, "address")));
      return user;
    }

    return null;
  }

  private Map<String, AttributeValue> userToMap(User user) {
    if (user != null) {
      Map<String, AttributeValue> attributeValueMap = new HashMap<>();
      safePutNumberValue(attributeValueMap, "id", String.valueOf(user.getId()));
      safePutStringValue(attributeValueMap, "email", user.getEmail());
      safePutStringValue(attributeValueMap, "firstName", user.getFirstName());
      safePutStringValue(attributeValueMap, "lastName", user.getLastName());
      safePutStringValue(attributeValueMap, "role", user.getRole().name());
      safePutBooleanValue(attributeValueMap, "active", user.isActive());
      safePutObjectValue(attributeValueMap, "account", accountToMap(user.getAccount()));
      safePutObjectValue(attributeValueMap, "address", addressToMap(user.getAddress()));
      return attributeValueMap;
    }

    return null;
  }

  private Payment mapToPayment(Map<String, AttributeValue> responseItem) {
    if (responseItem != null && responseItem.size() > 0) {
      Payment payment = new Payment();
      payment.setId(Long.parseLong(safeGetNumberParam(responseItem, "id")));
      payment.setSum(Double.valueOf(safeGetNumberParam(responseItem, "sum")));
      payment.setCurrency(getCurrency(safeGetStringParam(responseItem, "currency")));
      payment.setDescription(safeGetStringParam(responseItem, "description"));
      return payment;
    }

    return null;
  }

  private Map<String, AttributeValue> paymentToMap(Payment payment) {
    if (payment != null) {
      Map<String, AttributeValue> attributeValueMap = new HashMap<>();
      safePutNumberValue(attributeValueMap, "id", String.valueOf(payment.getId()));
      safePutNumberValue(attributeValueMap, "sum", String.valueOf(payment.getSum()));
      safePutStringValue(attributeValueMap, "currency", payment.getCurrency().name());
      safePutStringValue(attributeValueMap, "description", payment.getDescription());
      return attributeValueMap;
    }

    return null;
  }

  private String safeGetStringParam(Map<String, AttributeValue> responseItem, String value) {
    if (responseItem == null || responseItem.get(value) == null) {
      return null;
    }

    return responseItem.get(value).s();
  }

  private String safeGetNumberParam(Map<String, AttributeValue> responseItem, String value) {
    if (responseItem == null || responseItem.get(value) == null) {
      return null;
    }
    return Optional.ofNullable(responseItem.get(value).n()).orElse("0");
  }

  private boolean safeGetBooleanParam(Map<String, AttributeValue> responseItem, String value) {
    if (responseItem == null || responseItem.get(value) == null) {
      return false;
    }
    return responseItem.get(value).bool();
  }

  private Map<String, AttributeValue> safeGetObjectParam(Map<String, AttributeValue> responseItem, String value) {
    if (responseItem == null || responseItem.get(value) == null) {
      return null;
    }
    return responseItem.get(value).m();
  }

  private Currency getCurrency(String value) {
    return value != null ? Currency.valueOf(value) : null;
  }

  private Role getRole(String value) {
    return value != null ? Role.valueOf(value) : null;
  }

  private void safePutStringValue(Map<String, AttributeValue> attributeValueMap, String key, String value) {
    if (attributeValueMap != null && key != null && value != null) {
      attributeValueMap.put(key, AttributeValue.builder().s(value).build());
    }
  }

  private void safePutNumberValue(Map<String, AttributeValue> attributeValueMap, String key, String value) {
    if (attributeValueMap != null && key != null && value != null) {
      attributeValueMap.put(key, AttributeValue.builder().n(value).build());
    }
  }

  private void safePutBooleanValue(Map<String, AttributeValue> attributeValueMap, String key, boolean value) {
    if (attributeValueMap != null && key != null) {
      attributeValueMap.put(key, AttributeValue.builder().bool(value).build());
    }
  }

  private void safePutObjectValue(Map<String, AttributeValue> attributeValueMap, String key, Map<String, AttributeValue> object) {
    if (attributeValueMap != null && key != null && object != null && object.size() > 0) {
      attributeValueMap.put(key, AttributeValue.builder().m(object).build());
    }
  }
}