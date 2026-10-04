package se331.lab.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se331.lab.entity.*;
import java.util.List;
import org.mapstruct.Mapping;

@Mapper
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);
    EventDTO getEventDto(Event event);
    List<EventDTO> getEventDto(List<Event> events);

    OrganizerDTO getOrganizerDTO(Organizer organizer);
    List<OrganizerDTO> getOrganizerDTO(List<Organizer> organizers);

    @Mapping(target = "roles", source = "user.roles")
    OrganizerAuthDTO getOrganizerAuthDTO(Organizer organizer);

    ParticipantHistoryDTO getParticipantHistoryDto(Participant participant);
    List<ParticipantHistoryDTO> getParticipantHistoryDto(List<Participant> participants);
}