package com.proptechos.model.message.exception;

import java.time.ZonedDateTime;

public class RecException {

  private ExceptionOrigin origin;
  private String id;
  private String exception;
  private ZonedDateTime exceptionTime;
  private Integer retry;

}
