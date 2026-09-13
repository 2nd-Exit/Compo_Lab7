package se331.lab.dao;

import se331.lab.entity.Participant;
import java.util.List;

public interface ParticipantDao {
    List<Participant> getAllParticipant();
}