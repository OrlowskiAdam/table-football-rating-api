package pl.adamorlowski.tablefootballratingapi.utils;

import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;
import pl.adamorlowski.tablefootballratingapi.entity.QuarterType;

@UtilityClass
public class QuarterUtils {

  public static QuarterType getQuarter() {
    return QuarterType.Q4;
  }

  public static int getYear() {
    return LocalDateTime.now().getYear();
  }
}
