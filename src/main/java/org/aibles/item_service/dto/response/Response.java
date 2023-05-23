package org.aibles.item_service.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class Response {

  private int status;
  private String timestamp;
  private Object data;

  public static Response of(int status, Object data) {
    return Response.of(status, String.valueOf(LocalDateTime.now()), data);
  }

  public static Response of(int status) {
    return Response.of(status, String.valueOf(LocalDateTime.now()), null);
  }
}
