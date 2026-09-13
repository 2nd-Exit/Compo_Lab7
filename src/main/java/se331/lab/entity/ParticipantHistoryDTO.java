package se331.lab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantHistoryDTO {
    Long id;
    String name;
    String telNo;

    @Builder.Default
    List<ParticipantEventDTO> eventHistories = new ArrayList<>();
}