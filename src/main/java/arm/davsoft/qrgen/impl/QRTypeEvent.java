package arm.davsoft.qrgen.impl;

import arm.davsoft.qrgen.api.QRType;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * @author David Shahbazyan
 * @since Mar 04, 2017
 */
public class QRTypeEvent implements QRType {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String location;
    private String description;
    private boolean allDayEvent;

    public QRTypeEvent setTitle(String title) {
        this.title = title;
        return this;
    }

    public QRTypeEvent setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public QRTypeEvent setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public QRTypeEvent setLocation(String location) {
        this.location = location;
        return this;
    }

    public QRTypeEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public QRTypeEvent setAllDayEvent(boolean allDayEvent) {
        this.allDayEvent = allDayEvent;
        return this;
    }

    @Override
    public String getData() {
        StringBuilder builder = new StringBuilder("BEGIN:VEVENT");
        builder.append("SUMMARY:").append(title);
        if (allDayEvent) {
            builder.append("DTSTART;VALUE=DATE:").append(new SimpleDateFormat("yyyymmdd").format(startDate));
            builder.append("DTEND;VALUE=DATE:").append(new SimpleDateFormat("yyyymmdd").format(endDate));
        } else {
            builder.append("DTSTART:").append(startDate);
            builder.append("DTEND:").append(endDate);
        }
        builder.append("LOCATION:").append(location);
        builder.append("DESCRIPTION:").append(description);
        builder.append("END:VEVENT");
        return builder.toString();
    }
}
