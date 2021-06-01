package ru.vsu.cs.checklist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserSummaryDto {
    private Date date;
    private int count;
}
