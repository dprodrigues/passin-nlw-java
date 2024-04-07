package dprodrigues.com.passin.services;

import dprodrigues.com.passin.domain.attendee.Attendee;
import dprodrigues.com.passin.domain.checkin.CheckIn;
import dprodrigues.com.passin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import dprodrigues.com.passin.repositories.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckInRepository checkInRepository;

    public void registerCheckIn(Attendee attendee) {
        this.verifyCheckInExists(attendee.getId());

        CheckIn checkIn = new CheckIn();
        checkIn.setAttendee(attendee);
        checkIn.setCreatedAt(LocalDateTime.now());

        this.checkInRepository.save(checkIn);
    }

    public Optional<CheckIn> getCheckIn(String attendeeId) {
        return this.checkInRepository.findByAttendeeId(attendeeId);
    }

    private void verifyCheckInExists(String attendeeId) {
        Optional<CheckIn> isCheckedIn = this.getCheckIn(attendeeId);

        if (isCheckedIn.isPresent()) {
            throw new CheckInAlreadyExistsException("Attendee already checked in");
        }
    }
}
