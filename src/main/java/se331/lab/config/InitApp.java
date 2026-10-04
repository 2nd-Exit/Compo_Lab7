package se331.lab.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.entity.Participant;
import se331.lab.repository.EventRepository;
import se331.lab.repository.OrganizerRepository;
import se331.lab.repository.ParticipantRepository;
import se331.lab.security.user.Role;
import se331.lab.security.user.User;
import se331.lab.security.user.UserRepository;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;
    final UserRepository userRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Participant p1 = participantRepository.save(Participant.builder().name("Rapeepat").telNo("081111111").build());
        Participant p2 = participantRepository.save(Participant.builder().name("Cheevanon").telNo("082222222").build());
        Participant p3 = participantRepository.save(Participant.builder().name("Donlachok").telNo("083333333").build());
        Participant p4 = participantRepository.save(Participant.builder().name("Nipitpon").telNo("084444444").build());
        Participant p5 = participantRepository.save(Participant.builder().name("Chanin").telNo("085555555").build());

        Organizer org1 = organizerRepository.save(Organizer.builder().name("CAMT").build());
        Organizer org2 = organizerRepository.save(Organizer.builder().name("CMU").build());
        Organizer org3 = organizerRepository.save(Organizer.builder().name("ChiangMai").build());

        Event tempEvent;

        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petsAllowed(false)
                .build());
        tempEvent.setOrganizer(org1);
        org1.getOwnEvents().add(tempEvent);
        tempEvent.getParticipants().add(p1); p1.getEventHistories().add(tempEvent);
        tempEvent.getParticipants().add(p2); p2.getEventHistories().add(tempEvent);
        tempEvent.getParticipants().add(p3); p3.getEventHistories().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("8.00am-4.00 pm.")
                .petsAllowed(false)
                .build());
        tempEvent.setOrganizer(org2);
        org2.getOwnEvents().add(tempEvent);
        tempEvent.getParticipants().add(p1); p1.getEventHistories().add(tempEvent);
        tempEvent.getParticipants().add(p2); p2.getEventHistories().add(tempEvent);
        tempEvent.getParticipants().add(p4); p4.getEventHistories().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petsAllowed(false)
                .build());
        tempEvent.setOrganizer(org3);
        org3.getOwnEvents().add(tempEvent);
        tempEvent.getParticipants().add(p1); p1.getEventHistories().add(tempEvent);
        tempEvent.getParticipants().add(p3); p3.getEventHistories().add(tempEvent);
        tempEvent.getParticipants().add(p5); p5.getEventHistories().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petsAllowed(true)
                .build());
        tempEvent.setOrganizer(org3);
        org3.getOwnEvents().add(tempEvent);
        tempEvent.getParticipants().add(p2); p2.getEventHistories().add(tempEvent);
        tempEvent.getParticipants().add(p3); p3.getEventHistories().add(tempEvent);
        tempEvent.getParticipants().add(p4); p4.getEventHistories().add(tempEvent);
        tempEvent.getParticipants().add(p5); p5.getEventHistories().add(tempEvent);

        addUser();
        org1.setUser(user1);
        user1.setOrganizer(org1);
        org2.setUser(user2);
        user2.setOrganizer(org2);
        org3.setUser(user3);
        user3.setOrganizer(org3);
    }

    User user1, user2, user3;
    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .build();

        user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("enabled@user.com")
                .enabled(true)
                .build();

        user3 = User.builder()
                .username("disableUser")
                .password(encoder.encode("disableUser"))
                .firstname("disableUser")
                .lastname("disableUser")
                .email("disableUser@user.com")
                .enabled(false)
                .build();

        user1.getRoles().add(Role.ROLE_USER);
        user1.getRoles().add(Role.ROLE_ADMIN);

        user2.getRoles().add(Role.ROLE_USER);
        user3.getRoles().add(Role.ROLE_USER);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}