package com.web3.notification.utility;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Utility {

  public static String errorObject(String errorMessage) {
    JsonObject gsonError = new JsonObject();
    gsonError.addProperty("errorMessage", errorMessage);
    return errorMessage.toString();
  }
}
