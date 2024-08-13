package com.whitepeoples.wooso;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class notificationRequests {
    private String token;
    private String title;
    private String body;


}
